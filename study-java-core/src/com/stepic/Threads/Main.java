package com.stepic.Threads;

public class Main {
    public static void main(String[] args) {
        Car[] cars = new Car[10];
        Bridge bridge = new Bridge("Мост 800-летия", 2,300);
        cars[0] = new Car("Первый", 100);
        cars[2] = new Car("Второй", 150);
        cars[1] = new Car("Третий", 120);
        cars[3] = new Car("Четвертый", 200);
        cars[4] = new Car("Пятый", 300);
        cars[5] = new Car("Шестой", 80);
        cars[6] = new Car("Седьмой", 30);
        cars[7] = new Car("Восьмой", 60);
        cars[8] = new Car("Девятый", 180);
        cars[9] = new Car("Десятый", 110);
        System.out.println(bridge);
        for (Car car : cars) {
            car.setTarget(bridge);
            car.start();
        }
    }
}
