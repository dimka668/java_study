package com.ekkel.stream;

import java.util.stream.IntStream;

public class PseudoRandomStream {
    public static void main(String[] args) {
        pseudoRandomStream(2).map(i -> i*2).forEach(System.out::println);
    }

    public static IntStream pseudoRandomStream(int seed) {
        return IntStream.range(0,10); // your implementation here
    }
}
