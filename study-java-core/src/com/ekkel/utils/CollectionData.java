package com.ekkel.utils;

import com.ekkel.generators.CountingGenerator;
import com.ekkel.generators.Generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionData<T> extends ArrayList<T> {
    CollectionData(Generator gen, int size){
        for (int i = 0; i<size; i++)
            this.add((T) gen.next());
    }

    public static void main(String[] args) {
        CollectionData<Integer> cd = new CollectionData<>(new CountingGenerator.Integer(), 10);
        System.out.println(cd);
    }
}
