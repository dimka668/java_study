package com.sbt.SpringBeans.figure;

/**
 * Created by SBT-Klyshov-DA on 16.05.2018.
 */

public abstract class Figure {
    private String name;

    public Figure(String name) {
        this.name = name;
        System.out.println("Bean " + name + " has been created");
    }

    public String getName() {
        return this.name;
    }

    public abstract double square();
}
