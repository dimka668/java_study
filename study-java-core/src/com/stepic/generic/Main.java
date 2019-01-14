package com.stepic.generic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        //Set<Integer> set1 = null;
        Collections.addAll(set1, new Integer[]{1,2,3});
        Set<Integer> set2 = new HashSet<>();
        Collections.addAll(set2, new Integer[]{0,1,2});
        System.out.println(symmetricDifference1(set1,set2));
        symmetricDifference(set1,set2).forEach( x -> System.out.println(x));
    }

    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> copySet1 = (set1 == null) ? new HashSet<>() : new HashSet<>(set1);
        Set<T> copySet2 = (set2 == null) ? new HashSet<>() : new HashSet<>(set2);
        Set<T> resultSet = new HashSet<>();
        resultSet.addAll(copySet1);
        resultSet.addAll(copySet2);
        copySet2.retainAll(copySet1);
        resultSet.removeAll(copySet2);
        return resultSet;
    }

    public static <T> Set<T> symmetricDifference1(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> answer = new HashSet<>(set1);
        Set<T> help = new HashSet<>(set2);
        answer.removeIf(help::remove);
        answer.addAll(help);
        return answer;
    }
}


