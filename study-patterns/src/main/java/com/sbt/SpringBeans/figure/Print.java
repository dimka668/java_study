package com.sbt.SpringBeans.figure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by SBT-Klyshov-DA on 16.05.2018.
 */
import com.sbt.SpringBeans.figure.Figure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Print {
    @Autowired
    @Qualifier("circle1")
    private Figure figure;

    public Print() {
        System.out.println("Bean print is being created");
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public void showSquare() {
        System.out.println("Square of " + this.figure.getName() + " is " + this.figure.square());
    }
}
