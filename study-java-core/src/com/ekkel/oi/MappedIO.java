package com.ekkel.oi;

import com.sun.deploy.security.ruleset.RuleParseException;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by 16688641 on 22.01.2019.
 */
public class MappedIO {
    public static int numOfInts = 4000000;
    public static int numofbufInts = 200000;
    private abstract static class Tester{
        private String name;
        public Tester(String name){ this.name = name; }
        public void runTest(){
            System.out.println(name + ": ");
            try{
                long start = System.nanoTime();
                test();
                long duration = System.nanoTime() - start;
                System.out.format("%.2f\n", duration/1.0e9);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        public abstract void test() throws IOException;
    }
    private static Tester[] tests = {
            new Tester("Stream write") {
                @Override
                public void test() throws IOException {
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("temp.tmp")));
                    for (int i=0; i < numOfInts; i++){
                        out.writeInt(i);
                    }
                    out.close();
                }
            },
            new Tester("MAP write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile("temp.tmp", "rw").getChannel();
                    IntBuffer buffer = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
                    for (int i=0; i < numOfInts-1; i++){
                        buffer.put(i);
                    }
                    fc.close();
                }
            },
            new Tester("Stream Read") {
                @Override
                public void test() throws IOException {
                    DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("temp.tmp")));
                    for (int i=0; i < numOfInts; i++)
                        in.readInt();
                    in.close();
                }
            },
            new Tester("Mapped read") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile("temp.tmp", "r").getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).asIntBuffer();
                    while(ib.hasRemaining())
                        ib.get();
                    fc.close();
                }
            }
    };

    public static void main(String[] args) {
        for (Tester test : MappedIO.tests) {
            test.runTest();
        }
    }
}

