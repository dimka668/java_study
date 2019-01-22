package com.ekkel.oi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by 16688641 on 22.01.2019.
 */
public class FreezeAlien {
    public static void main(String[] args) throws IOException {
        Alien alien = new Alien();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("alien.dat"));
        oos.writeObject(alien);
        oos.close();
    }
}
