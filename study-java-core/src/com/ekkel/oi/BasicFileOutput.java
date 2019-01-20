package com.ekkel.oi;

import java.io.*;

public class BasicFileOutput {
    public static void main(String[] args) throws IOException {
        //BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read("study-java-core.iml")));
        BufferedReader in = new BufferedReader(new FileReader("study-java-core.iml"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("BasicFileOutput.txt")));
        String s;
        int lineNumber=0;
        while ((s = in.readLine()) != null){
            out.println(lineNumber++ + ":" + s);
        }
        out.close();
        System.out.println(BufferedInputFile.read("BasicFileOutput.txt"));
    }
}
