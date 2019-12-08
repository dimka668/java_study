package com.ekkel.enumerated.menu;

/**
 * Created by 16688641 on 12.03.2019.
 */

import static com.ekkel.enumerated.menu.Food.*;

public class TypeOfFood {
    public static void main(String[] args) {
        Food food = Appetizer.SALAD; food = MainCourse.LASAGNE; food = Dessert.GELATO; food = Coffee.CAPPUCCINO;
    }
}
