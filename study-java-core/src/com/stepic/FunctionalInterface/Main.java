package com.stepic.FunctionalInterface;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Predicate<Object> condition = Objects::isNull;
        Function<Object, Integer> ifTrue = obj -> 0;
        Function<CharSequence, Integer> ifFalse = CharSequence::length;
        Function<String, Integer> safeStringLength = ternaryOperator(condition, ifTrue, ifFalse);
        System.out.println(safeStringLength.apply("rrddr"));
    }

    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {
        //return x -> condition.test(x) ? (U) ifTrue.apply(x) : (U) ifFalse.apply(x);
        return new Function<T,U>() {
            @Override
            public U apply(T t) {
                return condition.test(t) ? (U) ifTrue.apply(t) : (U) ifFalse.apply(t);
            }
        };
                //(Function<T, U>) ifFalse; // your implementation here

    }
}
