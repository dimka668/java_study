package com.ekkel.oi;

import java.io.*;
import java.lang.reflect.Method;

public class BufferedInputFile {
    public static String read(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = br.readLine()) != null){
            sb.append(s + "\n");
        }
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        //System.out.println(read("study-java-core.iml"));
        //System.out.println(BufferedInputFile.read("study-java-core.iml"));
        for (Method method : DataOutputStream.class.getDeclaredMethods()) {
            if (method.getName().matches("write.*"))
                System.out.println(method.getName());
        }
    }
}
