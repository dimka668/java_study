package com.ekkel.collections;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyStack<T> {
    //T data;
    Item<T> current;
    private class Item<E>{
        T data;
        Item<E> next;
        Item(T data, Item<E> next){
            this.data = data;
            this.next = next;
        }
    }
    Item<T> top = new Item<>(null, null);
    public boolean isEnd(){
        return (current.next == null && current.data == null);
    }
    MyStack(){
        current = top;
    }

    public void push(T data){
//        if (current.next == null){
            current = new Item<T>(data, current);
//        }
    }

    public T pop(){
        if (current.next != null ){
            T tmpData = current.data;
            current = current.next;
            return tmpData;
        }else{
            return null;
        }
    }

    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<>();
        for (int i=0;i<10;i++){
            System.out.print(new Integer(i).toString());
            stack.push(new Integer(i).toString());
        }
        System.out.println("");
        String c;
        while ((c = stack.pop()) != null){
            System.out.print(c);
        }
        System.out.println();
        List<String> list2 = Stream.of("a", "b", "c", "d")
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toMap(
                                        Function.identity(),
                                        s -> s + s
                                ),
                                map -> map.entrySet().stream()
                        )
                )
                .map(e -> e.toString())
                .collect(
                        Collectors.toList()
                        //Collectors.collectingAndThen(
//                                Collectors.toList(),
  //                              Collections::unmodifiableList
    //                    )
                );
        list2.forEach(System.out::println);
    }
}
