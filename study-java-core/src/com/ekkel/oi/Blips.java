package com.ekkel.oi;

import java.io.*;

/**
 * Created by 16688641 on 22.01.2019.
 */
public class Blips {
    static class Blip3 implements Externalizable{
        int i;
        String s;
        public Blip3(){
            System.out.println("Create Blip1");
        }

        public Blip3(int i, String s){
            this.i = i;
            this.s = s;
        }

        @Override
        public void writeExternal(ObjectOutput objectOutput) throws IOException {
            System.out.println("writeExternal");
            objectOutput.writeObject(i);
            objectOutput.writeObject(s);
        }

        @Override
        public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
            System.out.println("readExternal");
            i = (int) objectInput.readObject();
            s = (String) objectInput.readObject();
        }

        @Override
        public String toString() {
            return super.toString() + "i="+i+",s="+s;
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("blip.dat"));
        oos.writeObject(new Blip3(3, "f"));
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("blip.dat"));
        Blip3 blip = (Blip3)ois.readObject();
        System.out.println(blip);
    }
}
