package com.ekkel.innerclass;

abstract class GenericWithCreate<T> {
    final T element;
    GenericWithCreate(){
        element = create();
    }
    public void m(){
        T[] arr = (T[]) new Object[10];
        System.out.println(arr);
    }
    abstract T create();
}

class SomeClass {};

class Creator extends GenericWithCreate<SomeClass>{
    @Override
    SomeClass create() {
        return new SomeClass();
    }
    void f(){
        System.out.println(element.getClass().getSimpleName());
    }
}

public class CreatorGeneric{
    public static void main(String[] args) {
        Creator c = new Creator();
        c.f();
        c.m();
    }
}