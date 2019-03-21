package com.ekkel.concurrency;

/**
 * Created by 16688641 on 14.03.2019.
 */

import java.util.concurrent.*;
import java.util.*;
//import static net.mindview.util.Print.*;

class Car {
    private final int id;
    private boolean
            engine = false, driveTrain = false, wheels = false;

    public Car(int idn) {
        id = idn;
    }

    public Car() {
        id = -1;
    }

    public synchronized int getld() {
        return id;
    }

    public synchronized void addEngine() {
        engine = true;
    }

    public synchronized void addDriveTrain() {
        driveTrain = true;
    }

    public synchronized void addWheels() {
        wheels = true;
    }

    public synchronized String toString() {
        return "Car " + id + " [" + " engine: " + engine + " driveTrain: " + driveTrain + " wheels: " + wheels + " ]";
    }
}

class CarQueue extends LinkedBlockingQueue<Car> {
}

class ChassisBuilder implements Runnable {
    private CarQueue carQueue;
    private int counter = 0;

    public ChassisBuilder(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                Car car = new Car(counter++);
                System.out.println("ChassisBuilder was created " + car);
                carQueue.put(car);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted ChassisBuilder");
        }
        System.out.println("ChassisBuilder off");
    }
}

class Assembler implements Runnable {

    private CarQueue chassisQueue, finishingQueue;
    private Car car;
    private CyclicBarrier barrier = new CyclicBarrier(4);
    private RobotPool robotPool;

    public Assembler(CarQueue cq, CarQueue fq, RobotPool rp) { chassisQueue = cq; finishingQueue = fq; robotPool = rp; }
    public Car car() { return car; }
    public CyclicBarrier barrier() { return barrier; }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                car = chassisQueue.take();
                robotPool.hire(EngineRobot.class, this);
                robotPool.hire(DriveTrainRobot.class, this);
                robotPool.hire(WheelRobot.class, this);
                barrier.await(); //
                finishingQueue.put(car);
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting Assembler via interrupt");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Assembler off");
    }
}

class Reporter implements Runnable {
    private CarQueue carQueue;
    public Reporter(CarQueue cq) { carQueue = cq; }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(carQueue.take());
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting Reporter via interrupt");
        }
        System.out.println("Reporter off");
    }
}

abstract class Robot implements Runnable {
    private RobotPool pool;
    public Robot(RobotPool p) { pool = p; }
    protected Assembler assembler;

    public Robot assignAssembler(Assembler assembler) { this.assembler = assembler; return this; }
    private boolean engage = false;
    public synchronized void engage() { engage = true; notifyAll(); }
    // Часть run(), отличная для каждого робота:
    abstract protected void performService();

    public void run() {
        try {
            powerDown(); // Ожидать, пока не понадобится
            while (!Thread.interrupted()) {
                performService();
                assembler.barrier().await(); // Синхронизация
                powerDown(); // Задание выполнено...
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting " + this + " via interrupt");
        } catch (BrokenBarrierException e) {
            // Исключение, о котором нужно знать
            throw new RuntimeException(e);
        }
        System.out.println(this + " off");
    }
    private synchronized void powerDown() throws InterruptedException {
        engage = false;
        assembler = null; // Отключение от Assembler
        System.out.println(" + " + this + " перемещается в пул");
        pool.release(this); // Возвращение в пул:
        while (engage == false){ // Выключение питания
            wait();
        }
    }
    public String toString() { return getClass().getName(); }
}

class EngineRobot extends Robot {
    public EngineRobot(RobotPool pool) { super(pool); }
    protected void performService() {
        System.out.println(this + " installing engine");
        assembler.car().addEngine();
    }
}
class DriveTrainRobot extends Robot {
    public DriveTrainRobot(RobotPool pool) { super(pool); }
    protected void performService() {
        System.out.println(this + " installing DriveTrain");
        assembler.car().addDriveTrain();
    }
}
class WheelRobot extends Robot {
    public WheelRobot(RobotPool pool) { super(pool); }
    protected void performService() {
        System.out.println(this + " installing Wheels");
        assembler.car().addWheels();
    }
}

class RobotPool {
    // Незаметно предотвращает использование идентичных элементов
    private Set<Robot> pool = new HashSet<Robot>();
    public synchronized void add(Robot r){
        pool.add(r);
        notifyAll();
    }

    public synchronized void hire(Class<? extends Robot> robotType, Assembler d) throws InterruptedException {
        System.out.println("Поиск в пуле: " + robotType + "(Доступные роботы: " + getPool() + ")");
        for (Robot r : pool) {
            if (r.getClass().equals(robotType)) {
                pool.remove(r);
                r.assignAssembler(d);
                r.engage(); // Включение для выполнения задания
                System.out.println(" - " + r + " назначен на " + d);
                return;
            }
        }
        System.out.println("\t" + robotType + " не нашли в пуле, ждем...");
        wait(); // Нет доступных кандидатов
        hire(robotType, d); // Повторная попытка с рекурсией
    }
    public synchronized void release(Robot r) { add(r); }

    public String getPool(){
        StringBuilder sb = new StringBuilder();
        pool.forEach(x -> sb.append(x).append(", "));
        return sb.toString();
    }
}

public class CarBuilder {
    public static void main(String[] args) throws Exception {
        CarQueue chassisQueue = new CarQueue(), finishingQueue = new CarQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        RobotPool robotPool = new RobotPool();
        exec.execute(new EngineRobot(robotPool));
        exec.execute(new DriveTrainRobot(robotPool));
        exec.execute(new WheelRobot(robotPool));
        exec.execute(new Assembler(chassisQueue, finishingQueue, robotPool));
        exec.execute(new Reporter(finishingQueue));
        // Создание рам приводит конвейер в движение:
        exec.execute(new ChassisBuilder(chassisQueue));
        TimeUnit.SECONDS.sleep(7);
        exec.shutdownNow();
    }
}