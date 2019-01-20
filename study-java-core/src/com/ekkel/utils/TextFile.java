package com.ekkel.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class TextFile extends ArrayList<String>{
    public static String read(String filename){
        StringBuilder sb = new StringBuilder();
        try{
            try(BufferedReader in = new BufferedReader(new FileReader( new File(filename).getAbsoluteFile()))){
                String s;
                while ((s = in.readLine()) != null){
                    sb.append(s).append("\n");
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
    public static void write(String filename, String text){
        try {
            PrintWriter out = new PrintWriter(new File(filename).getCanonicalFile());
            try {
                out.write(text);
            }finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public TextFile(String filename, String splitter){
        super(Arrays.asList(read(filename).split(splitter)));
        if (get(0).equals("")) remove(0);
    }
    public TextFile(String filename){
        this(filename, "\n");
    }

    public void write(String filename){
        try{
            PrintWriter out = new PrintWriter(new File(filename).getCanonicalFile());
            //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
            try{
                for (String s : this) {
                    out.println(s);
                }
            }finally {
                out.close();
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        TreeSet<String> ts = new TreeSet<>(new TextFile("study-java-core.iml"));
        //ArrayList<String> ts = new ArrayList<>(new TextFile("study-java-core.iml"));
        System.out.println(ts);
    }
}
