package com.ekkel.oi;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Created by 16688641 on 21.01.2019.
 */
public class IntBufferDemo {
    private static final int BSIZE = 1024;
    public static void main(String[] args){
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        IntBuffer ib = bb.asIntBuffer();
        ib.put(new int[]{1,2,3,4,5,6,7,8,9});
        System.out.println(ib.get(3));
        ib.put(3,33);
        ib.flip();
        while (ib.hasRemaining()){
            int i = ib.get();
            System.out.println(i);
        }
    }
}
