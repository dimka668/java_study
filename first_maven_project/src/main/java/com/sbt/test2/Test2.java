package com.sbt.test2;

import com.sbt.test.Test1;

/**
 * Created by 16688641 on 27.12.2018.
 */
public class Test2 {

    public static String a = "a";
    private static String b = "b";

    public void m(){
        System.out.println(a);
        System.out.println(b);
    }

    public static void main(String[] args) {
        MyEnum myEnum;
        for (MyEnum item : MyEnum.values()) {
            System.out.println(item.por);
        }
        System.out.println(MyEnum.one.por);

    }

}
