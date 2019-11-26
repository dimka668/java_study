package com.sbt.test;

/**
 * Created by 16688641 on 27.12.2018.
 */
interface TestInterface {

    String a = "a";

    default String m(){
        return a;
    }
    static String m1(){
        return a;
    }
}
