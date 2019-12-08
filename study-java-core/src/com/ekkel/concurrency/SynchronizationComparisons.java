package com.ekkel.concurrency;

/**
 * Created by 16688641 on 21.03.2019.
 */

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;
import java.util.*;

abstract class Accumulator {
    public static long cycles = 50000L;
    // Количество объектов Modifier и Reader в каждом тесте:
    private static final int N = 4;
    public static ExecutorService exec = Executors.newFixedThreadPool(N*2);
    private static CyclicBarrier barrier = new CyclicBarrier(N*2 + 1);
    protected volatile int index = 0;
    protected volatile long value = 0;
    protected long duration = 0;
    protected String id = "error";
    protected final static int SIZE = 100000;
    protected static int[] preLoaded = new int[SIZE];
    static {
        // Загрузка массива случайных чисел:
        Random rand = new Random(47);
        for (int i = 0; i < SIZE; i++)
            preLoaded[i] = rand.nextInt();
    }
    public abstract void accumulate();
    public abstract long read();
    private class Modifier implements Runnable {
        public void run() {
            //System.out.println(this + " test запущен. barrier.getNumberWaiting="+barrier.getNumberWaiting());
            for (long i = 0; i < cycles; i++)
                accumulate();
            //System.out.println(this + " test завершен. barrier.getNumberWaiting="+barrier.getNumberWaiting());
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    private class Reader implements Runnable {
        private volatile long value;
        public void run() {
            //System.out.println(this + " test запущен. barrier.getNumberWaiting="+barrier.getNumberWaiting());
            for (long i = 0; i < cycles; i++)
                value = read();
            //System.out.println(this + " test завершен. barrier.getNumberWaiting="+barrier.getNumberWaiting());
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void timedTest() {
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
//            System.out.println(this + " test No." + i + " start Modifiers");
            exec.execute(new Modifier());
//            System.out.println(this + " test No." + i + " start Readers");
            exec.execute(new Reader());
        }
        try {
            barrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        duration = System.nanoTime() - start;
        System.out.printf("%-13s: %13d\n", id, duration);
//        System.out.println(this + " test stop");
    }
    public static void report(Accumulator accl, Accumulator acc2){
        System.out.printf("%-22s: %.2f\n", accl.id + "f" + acc2.id, (double)accl.duration/(double)acc2.duration);
    }
}

class BaseLine extends Accumulator {
    { id = "BaseLine"; }
    public void accumulate() {
        value += preLoaded[index++];
        if (index >= SIZE){
            //System.out.println(index + " set to 0");
            index = 0;
        }
    }
    public long read() { return value; }
}
class SynchronizedTest extends Accumulator {
    {         id = "synchronized";
    }

    public synchronized void accumulate() {
        value += preLoaded[index++];
        if (index >= SIZE) index = 0;
    }

    public synchronized long read() {
        return value;
    }
}
class LockTest extends Accumulator {
    {
        id = "Lock";
    }

    private Lock lock = new ReentrantLock();

    public void accumulate() {
        lock.lock();
        try {
            value += preLoaded[index++];
            if (index >= SIZE) index = 0;
        } finally {
            lock.unlock();
        }
    }

    public long read() {
        lock.lock();
        try {
            return value;
        } finally {
            lock.unlock();
        }
    }
}
class AtomicTest extends Accumulator {
    { id = "Atomic"; }
    private AtomicInteger index = new AtomicInteger(0);
    private AtomicLong value = new AtomicLong(0);
    public void accumulate() {
        int i = index.getAndIncrement();
        value.getAndAdd(preLoaded[i]);
        if (++i >= SIZE)
            index.set(0);
    }
    public long read() { return value.get(); }
}
public class SynchronizationComparisons {
    static BaseLine baseLine = new BaseLine();
    static SynchronizedTest synch = new SynchronizedTest();
    static LockTest lock = new LockTest();
    static AtomicTest atomic = new AtomicTest();

    static void test() {
        System.out.println("=================================");
        System.out.printf("%-12s : %13d\n", "Cycles", Accumulator.cycles);
        baseLine.timedTest();
        synch.timedTest();
        lock.timedTest();
        //atomic.timedTest();
        //Accumulator.report(synch, baseLine);
        //Accumulator.report(lock, baseLine);
        //Accumulator.report(atomic, baseLine);
        //Accumulator.report(synch, lock);
        //Accumulator.report(synch, atomic);
        //Accumulator.report(lock, atomic);
    }
    public static void main(String[] args) {
        int iterations = 5; // По умолчанию
        if(args.length > 0) // Возможность изменения iterations
            iterations = new Integer(args[0]);
        // При первом проходе заполняется пул потоков:
        System.out.println("Warmup");
        System.out.println("First Baseline");
        baseLine.timedTest();
        // Теперь исходный тест не включает затраты
        // на первый запуск потоков.
        // Создание множественных элементов данных:
        for(int i = 0; i < iterations; i++) {
            System.out.println("Run test No."+i);
            test();
            Accumulator.cycles *= 2;
        }
        Accumulator.exec.shutdown();
    }
}
