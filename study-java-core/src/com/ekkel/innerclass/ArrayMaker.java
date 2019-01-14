package com.ekkel.innerclass;

import java.lang.reflect.*;
import java.util.*;

public class ArrayMaker<T> {
    private Class<T> kind;
    public ArrayMaker(Class<T> kind){
        this.kind = (Class<T>) kind;
    }
    T[] create (int size){
        return (T[])Array.newInstance(kind, size);
    }

    public static void main(String[] args) {
        String str = "sss";
        ArrayMaker<String> stringMaker = new ArrayMaker<>(String.class);
        String[] stringArray = stringMaker.create(10);
        System.out.println(Arrays.toString(stringArray));
        //System.out.println(stringArray[1].getClass());
        String[] strArr = (String[]) Array.newInstance(String.class, 10);
        System.out.println(Arrays.toString(strArr));
        System.out.println(strArr);
        System.out.println(stringArray);

    }
}
