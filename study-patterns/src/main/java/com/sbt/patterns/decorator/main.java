package com.sbt.patterns.decorator;

/**
 * Created by SBT-Klyshov-DA on 27.06.2018.
 */
public class main {

    public static void main(String[] args) {
        ConcComponent concComponent = new ConcComponent();
        ConcDecorator concDecorator1 = new ConcDecorator(concComponent);
        ConcDecorator concDecorator2 = new ConcDecorator(concDecorator1);
        //concComponent.execute();
        concDecorator2.execute();
    }

}
