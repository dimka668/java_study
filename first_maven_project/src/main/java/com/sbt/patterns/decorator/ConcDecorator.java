package com.sbt.patterns.decorator;

/**
 * Created by SBT-Klyshov-DA on 27.06.2018.
 */
public class ConcDecorator extends BaseDecorator {

    ConcDecorator(Component component) {
        wrappee = component;
    }

    @Override
    public void execute() {
        wrappee.execute();
        System.out.println("ConcDecorator.execute()");
    }

}
