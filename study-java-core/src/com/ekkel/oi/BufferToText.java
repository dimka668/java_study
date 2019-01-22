package com.ekkel.oi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by 16688641 on 21.01.2019.
 */
public class BufferToText {
    private static final int BSIZE = 1024;
    public static void main(String[] args) throws IOException {

        FileChannel fc = new FileOutputStream("BufferToText.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes()));
        fc.close();

        fc = new FileInputStream("BufferToText.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
        buffer.rewind();
        String encoding = System.getProperty("file.encoding");
        System.out.println(Charset.forName(encoding).decode(buffer));

        // -- getBytes("UTF-16BE")

        fc = new FileOutputStream("BufferToText2.txt").getChannel();
        fc.write(ByteBuffer.wrap("some more".getBytes("UTF-16BE")));
        fc.close();

        fc = new FileInputStream("BufferToText2.txt").getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
        fc.close();


        // -- write asCharBuffer --

        fc = new FileOutputStream("BufferToText2.txt").getChannel();
        //buffer = ByteBuffer.allocate(24);
        buffer.clear();
        buffer.asCharBuffer().put("Some text");
        //buffer.put("Some text".getBytes());
        fc.write(buffer);
        fc.close();

        fc = new FileInputStream("BufferToText2.txt").getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
    }
}
