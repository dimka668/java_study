package com.ekkel.generators;

public class GeneratorTest {
    public static int size = 10;
    public static void test(Class<?> surroundingClass) throws IllegalAccessException, InstantiationException {
        for (Class<?> type: surroundingClass.getClasses()){
            Generator<?> generator = (Generator<?>)type.newInstance();
            for(int i=0; i<size; i++){
                System.out.print(generator.next() + " ");
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        GeneratorTest.test(CountingGenerator.class);
    }
}
