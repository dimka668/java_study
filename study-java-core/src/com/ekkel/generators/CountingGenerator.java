package com.ekkel.generators;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CountingGenerator {
    public static class Boolean implements Generator<java.lang.Boolean>{
        private boolean value = false;
        public java.lang.Boolean next(){
            value = !value;
            return value;
        }
    }

    public static class Integer implements Generator<java.lang.Integer>{
        private int value = 0;
        public java.lang.Integer next(){
            return value++;
        }
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(CountingGenerator.class.getClasses()));
    }
}
