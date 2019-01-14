package com.ekkel.utils;

import com.ekkel.generators.Generator;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Generated {

    public static <T> T[] array(Class<T> type, Generator gen, int size){
        T[] a = (T[])java.lang.reflect.Array.newInstance(type, size);
        return new CollectionData<T>(gen,size).toArray(a);
//        Integer[] ia = new Integer[size];
//        for(int i=0;i<size;i++){
//            ia[i] = (Integer) gen.next();
//        }
//        return ia;
    }
}
