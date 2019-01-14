package com.ekkel.containers;

import java.util.List;

public class Tester<C> {

    public static int fieldWidth = 8;
    private static int sizeWidth = 5;
    private String headline = "";

    public static TestParam[] defaultParam = TestParam.array(10,5000, 100,5000, 1000,5000, 10000, 500);

    private List<Test<C>> tests;
    private TestParam[] paramList = defaultParam;
    protected C container;
    protected C initialize(int size){ return container; }

    private static String stringField(){ return "%" + fieldWidth + "s"; }
    private static String numberField(){ return "%" + fieldWidth + "s"; }
    private static String sizeField = "%" + sizeWidth + "s";

    public Tester(C container, List<Test<C>> tests){
        this.container = container;
        this.tests = tests;
        if (container != null)
            headline = container.getClass().getSimpleName();
    }

    public Tester(C container, List<Test<C>> tests, TestParam[] paramList){
        this(container,tests);
        this.paramList = paramList;
    }

    public void setHeadline(String newHeadLine){
        headline = newHeadLine;
    }

    public static <C> void run(C container, List<Test<C>> tests){
        new Tester<C>(container, tests).timedTest();
    }

    private void displayHeader(){
        int width = fieldWidth * tests.size() + sizeWidth;
        int dashLength = width - headline.length() - 1;
        StringBuilder head = new StringBuilder(width);
        for (int i=0; i< dashLength/2;i++){
            head.append("-");
        }
        head.append(" ")
                .append(headline)
                .append(" ");
        for (int i=0; i<dashLength/2;i++){
            head.append("-");
        }
        System.out.println(head);
        System.out.format(sizeField, "size");
        for (Test<C> test : tests)
            System.out.format(stringField(), test.name);
        System.out.println();
    }

    public void timedTest(){
        displayHeader();
        for (TestParam testParam : paramList) {
            System.out.format(sizeField, testParam.size);
            for (Test<C> test : tests) {
                C kontainer = initialize(testParam.size);
                long start = System.nanoTime();
                int reps = test.test(kontainer, testParam);
                long duration = System.nanoTime() - start;
                long timePerRep = duration / reps;
                System.out.format(numberField(), timePerRep);
            }
            System.out.println();
        }
    }
}
