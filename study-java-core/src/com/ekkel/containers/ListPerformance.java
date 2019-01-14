package com.ekkel.containers;

import com.ekkel.generators.CountingGenerator;

import com.ekkel.utils.*;

import javax.annotation.Generated;
import java.util.*;

public class ListPerformance {
    static Random rand = new Random();
    static int reps = 1000;
    static List<Test<List<Integer>>> tests =
            new ArrayList<Test<List<Integer>>>();
    static List<Test<LinkedList<Integer>>> qTests =
            new ArrayList<Test<LinkedList<Integer>>>();
    static {
        tests.add(
                new Test<List<Integer>>("add"){
                    @Override
                    int test(List<Integer> container, TestParam ts) {
                        int loops = ts.loops;
                        int listSize = ts.size;
                        for (int i=0;i<loops;i++){
                            container.clear();
                            for (int j=0;j<listSize;j++){
                                container.add(j);
                            }
                        }
                        return loops * listSize;
                    }
                }
        );
    }

    static class ListTester extends Tester<List<Integer>> {
        public ListTester(List<Integer> container, List<Test<List<Integer>>> tests) {
            super(container, tests);
        }
        protected List<Integer> initialize(int size){
            container.clear();
            container.addAll(new CountingIntegerList(size));
            return container;
        }
        public static void run(List<Integer> list, List<Test<List<Integer>>> tests){
            new ListTester(list, tests).timedTest();
        }

    }

    public static void main(String[] args) {
        if (args.length > 0)
            Tester.defaultParam = TestParam.array(args);
        Tester<List<Integer>> arrayTest =
                new Tester<List<Integer>>(null, tests.subList(1,1)){
                    protected List<Integer> initialize(int size){
                        Integer[] ia = com.ekkel.utils.Generated.array(Integer.class, new CountingGenerator.Integer(), size);
                        return Arrays.asList(ia);
                    }
                };
        arrayTest.setHeadline("Array as List");
        arrayTest.timedTest();
        Tester.defaultParam = TestParam.array(10,5000, 100,5000, 1000,1000, 10000, 200);
        if (args.length > 0)
            Tester.defaultParam = TestParam.array(args);
        ListTester.run(new ArrayList<Integer>(), tests);
    }


}
