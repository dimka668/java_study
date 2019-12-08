package com.sbt.SpringBeans.Hello;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by SBT-Klyshov-DA on 16.05.2018.
 */
public class Hello {

    private String s;

    public Hello(String str){
        s = str;
    }

    public void sayHi(){
        System.out.println(s);
    }
    public static void main(String []args){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {"config.xml"});
        Hello h = (Hello) ac.getBean("hello");//указываем id нашего bean-а
        h.sayHi();
    }
}
