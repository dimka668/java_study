package com.sbt.SpringBeans.figure;

/**
 * Created by SBT-Klyshov-DA on 16.05.2018.
 */
public class Rectangle extends Figure {
    private int length;
    private int width;
    private String name;

    public Rectangle(String name, int length, int width) {
        super(name);
        this.length = length;
        this.width = width;
    }

    public double square() {
        return this.length*this.width;
    }
}
