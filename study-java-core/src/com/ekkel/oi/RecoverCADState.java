package com.ekkel.oi;

import java.io.*;
import java.util.List;

public class RecoverCADState {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("CAD.dat"));
        Line.deserializeStaticParam(ois);
        List<Shape> shapes = (List<Shape>)ois.readObject();
        System.out.println(shapes);
    }
}
