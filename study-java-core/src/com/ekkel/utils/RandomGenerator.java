package com.ekkel.utils;

import com.ekkel.enumerated.Input;
import com.ekkel.generators.Generator;

import java.util.Random;

/**
 * Created by 16688641 on 22.03.2019.
 */
public class RandomGenerator {

    static Random rand = new Random(47);

    public static class Integer implements Generator<java.lang.Integer> {
        @Override
        public java.lang.Integer next() {
            return rand.nextInt();
        }
    }

    public static class String implements Generator<java.lang.String> {
        private int length;

        public String(int i) {
            this.length=i;
        }

        @Override
        public java.lang.String next() {
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<length; i++) {
                java.lang.Integer j = rand.nextInt();
                sb.append(j);
            }
            return sb.toString();
        }

    }
}
