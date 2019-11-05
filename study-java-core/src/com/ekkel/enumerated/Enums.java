package com.ekkel.enumerated;

import java.util.Random;

/**
 * Created by 16688641 on 24.01.2019.
 */
public class Enums {
    private static Random rand = new Random(47);
    public static <T extends Enum<T>> T random(Class<T> ec){
        return random(ec.getEnumConstants());
    }
    public static <T> T random(T[] values){
        return values[rand.nextInt(values.length)];
    }
}
