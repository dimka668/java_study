package com.sbt.patterns.decorator;

/**
 * Created by SBT-Klyshov-DA on 27.06.2018.
 */
public class ConcComponent implements Component {
    @Override
    public void execute() {
        System.out.println("ConcComponent.execute()");
    }
}
