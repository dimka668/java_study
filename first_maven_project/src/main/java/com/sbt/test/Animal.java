package com.sbt.test;

/**
 * Created by 16688641 on 27.12.2018.
 */
public class Animal {
    public void meow() {
        System.out.println("Meow!");
    }

    public static void main(String[] args) {



        final String[] a = {"a"};
        Animal anonTiger = new Animal() {
            @Override
            public void meow() {
                a[0] = a[0] + "d";
                System.out.println("Raaar!" + a[0]);
            }
        };

        Animal notAnonTiger = new Animal().new Tiger();

        anonTiger.meow(); // будет выведено Raaar!
        notAnonTiger.meow(); // будет выведено Raaar!


    }

    private class Tiger extends Animal {
        @Override
        public void meow() {
            System.out.println("Raaar!");
        }
    }
}
