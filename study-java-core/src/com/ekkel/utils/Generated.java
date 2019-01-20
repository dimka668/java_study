package com.ekkel.utils;

import com.ekkel.generators.CountingGenerator;
import com.ekkel.generators.Generator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Generated {

    public static <T> T[] array(Class<T> type, Generator<T> gen, int size){
        T[] a = (T[])java.lang.reflect.Array.newInstance(type, size);
        return new CollectionData<T>(gen,size).toArray(a);
    }

    public static void main(String[] args) {
        Integer[] arr = array(Integer.class, new CountingGenerator.Integer(), 10);
        System.out.println(Arrays.toString(arr));
    }
}
