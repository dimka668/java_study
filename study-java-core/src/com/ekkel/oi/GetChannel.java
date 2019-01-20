package com.ekkel.oi;

import com.sun.corba.se.impl.ior.ByteBuffer;

import java.nio.*;
import java.nio.channels.*;
import java.io.*;

public class GetChannel {
    private static final int BSIZE = 2048;

    public static void main(String[] args) throws IOException {
        FileChannel fc = new FileOutputStream("GetChannel.txt").getChannel();
        fc.write(java.nio.ByteBuffer.wrap("some text".getBytes()));
        fc.close();
        fc = new RandomAccessFile("GetChannel.txt", "rw").getChannel();
        fc.position(fc.size());
        fc.write(java.nio.ByteBuffer.wrap("some more".getBytes()));
        fc.close();
        fc = new FileInputStream("GetChannel.txt").getChannel();
        java.nio.ByteBuffer buffer = java.nio.ByteBuffer.allocate(BSIZE);
        fc.read(buffer);
        buffer.flip();
        while (buffer.hasRemaining()){
            System.out.println((char)buffer.get());
        }
    }
}
