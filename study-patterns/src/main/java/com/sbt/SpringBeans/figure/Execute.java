package com.sbt.SpringBeans.figure;

/**
 * Created by SBT-Klyshov-DA on 16.05.2018.
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Execute {
    public static void main(String [] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Print print = (Print) context.getBean("print");
        print.showSquare();
    }
}
