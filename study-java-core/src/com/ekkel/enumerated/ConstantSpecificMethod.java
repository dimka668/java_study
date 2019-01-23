package com.ekkel.enumerated;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by 16688641 on 23.01.2019.
 */
public enum  ConstantSpecificMethod {
    DATE_INFO {
        String getInfo() {
            return DateFormat.getDateInstance().format(new Date());
        }
    },
    CLASSPATH {
        String getInfo() {
            return System.getenv("CLASSPATH");
        }
    },
    VERSION {
        String getInfo() {
            return System.getProperty("java.version");
        }
    };
    abstract String getInfo();

    public static void main(String[] args) {
        for (ConstantSpecificMethod m : ConstantSpecificMethod.values()) {
            System.out.println(m.getInfo());
        }
    }

}
