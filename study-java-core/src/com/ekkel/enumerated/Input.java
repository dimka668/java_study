package com.ekkel.enumerated;

import java.util.Random;

/**
 * Created by 16688641 on 24.01.2019.
 */
public enum  Input {
    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
    TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
    ABORT_TRANSACTION{
        public int amount () {
            throw new RuntimeException("ABORT.amount()");
        }
    },
    STOP {
        public int amount() {
            throw new RuntimeException("SHUT_DOWN.amount()");
        }
    };
    int value; // В центах
    Input(int value) {
        this.value = value;
    }
    Input(){}
    int amount(){
        return value;
    };
    static Random rand = new Random(47);
    public static Input randomSelection() {
        // He включая STOP:
        return values()[rand.nextInt(values().length - 1)];
    }
}
