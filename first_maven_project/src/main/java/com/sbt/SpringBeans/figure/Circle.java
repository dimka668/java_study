package com.sbt.SpringBeans.figure;

/**
 * Created by SBT-Klyshov-DA on 16.05.2018.
 */
public class Circle extends Figure {
    private int radius;
    public static double PI = 3.1415;
    private String name;

    public Circle(String name, int radius) {
        super(name);
        this.radius = radius;
    }

    public double square() {
        return PI*this.radius*this.radius;
    }
}
