package com.ekkel.oi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelCopy {
    private static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
        //if (args.length != 2){
//            System.exit(1);
  //      }
        FileChannel
                in = new FileInputStream("study-java-core.iml").getChannel(),
                out = new FileOutputStream("ChannelCopy.txt").getChannel();
        java.nio.ByteBuffer buffer = java.nio.ByteBuffer.allocate(BSIZE);
        while (in.read(buffer) != -1){
            buffer.flip();
            out.write(buffer);
            buffer.clear();
        }
        System.out.println(BufferedInputFile.read("ChannelCopy.txt"));
    }
}
