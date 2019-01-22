package com.ekkel.oi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by 16688641 on 22.01.2019.
 */
public class LockingMappedFiles {
    static final int LENGTH = 0X8FFFFFF;
    static FileChannel fc;

    public static void main(String[] args) throws IOException {
        fc = new RandomAccessFile("test.dat", "rw").getChannel();
        MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
        for(int i = 0; i < LENGTH; i++)
            out.put((byte)'x');
        new LockAndModify(out, 0, 0 + LENGTH/3);
        //new LockAndModify(out, LENGTH/2, LENGTH/2 + LENGTH/4);
//        mbb.flip();
//        while(mbb.hasRemaining())
//            mbb.get();
    }
    private static class LockAndModify extends Thread{
        private ByteBuffer bb;
        private int start, end;
        LockAndModify(ByteBuffer mbb, int start, int end){
            this.start = start;
            this.end = end;
            mbb.limit(end);
            mbb.position(start);
            bb = mbb.slice();
            start();
        }

        @Override
        public void run() {
            try {
                FileLock fl = fc.tryLock(start, end, false);
                System.out.println("Locked: " + start + " to " + end);
                while(bb.position() < bb.limit() - 1 )
                    bb.put((byte)(bb.get() + 1));
                fl.release();
                System.out.println("Released: " + start + " to " + end);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
