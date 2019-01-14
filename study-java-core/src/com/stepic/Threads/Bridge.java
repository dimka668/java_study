package com.stepic.Threads;

import static java.lang.Thread.sleep;

public class Bridge{

    int maxConsumersCount;
    int currentConsumersCount;
    int maxWeight;
    int currentWeight;
    String name;

    Bridge(String name, int maxConsumersCount, int maxWeight){
        this.name = name;
        this.maxConsumersCount = maxConsumersCount;
        this.maxWeight = maxWeight;
    }

    public synchronized boolean tryPut(Car car){
        if (currentWeight+car.weight <= maxWeight
                && currentConsumersCount < maxConsumersCount
                ){
            currentWeight += car.weight;
            currentConsumersCount++;
            System.out.println(car + " заехал на " + this);
            return true;
        }
        return false;
    }

    public synchronized void release(Car car) {
        currentWeight -= car.weight;
        currentConsumersCount--;
        System.out.println(car + " cъехал с " + this);
    }

    @Override
    public String toString() {
        return name + "(" + currentConsumersCount + "/" + maxConsumersCount + ", " + currentWeight + "/" + maxWeight + ")";
    }

}
