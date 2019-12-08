package com.ekkel.enumerated.menu;

/**
 * Created by 16688641 on 12.03.2019.
 */
public class Meal {
    public static void main(String[] args) {
        for (int i=0;i<5;i++){
            for (Course course : Course.values()) {
                Food food = course.randomSelection();
                System.out.println(food);
            }
            System.out.println("---");
        }
    }
}
