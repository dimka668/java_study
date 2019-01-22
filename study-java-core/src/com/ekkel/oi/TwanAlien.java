package com.ekkel.oi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by 16688641 on 22.01.2019.
 */
public class TwanAlien {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("alien.dat"));
        Object o = ois.readObject();
        System.out.println(o);
    }
}
