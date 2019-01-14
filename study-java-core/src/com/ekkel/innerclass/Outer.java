package com.ekkel.innerclass;

public class Outer {

    Outer(){
        System.out.println("Outer create");
    }
    {
        System.out.println("Outer dynamic");
    }
    Inner inner = new Inner();
    static {
        System.out.println("Outer static");
    }
    class Inner{
        Inner(){
            System.out.println("Outer.Inner create");
        }
        void m(){
            System.out.println("Outer.Inner.m()");
        }
    }

}

class OuterChild extends Outer{

    OuterChild(){
        System.out.println("OuterChild create");
    }
    Inner inner = new Inner();
    {
        System.out.println("OuterChild dynamic");
    }
    static {
        System.out.println("OuterChild static");
    }
    class Inner{
        Inner(){
            System.out.println("OuterChild.Inner create");
        }
        void m(){
            System.out.println("OuterChild.Inner.m()");
        }

    }

    static class Gen<T>{
        Class<T> clazz;
        T obj;
        Gen(T obj){
            this.obj = obj;
            this.clazz = (Class<T>) obj.getClass();
        }

        public void copy() throws IllegalAccessException, InstantiationException {
            System.out.println("run copy");
            T str = clazz.newInstance();
            System.out.println("copy created");
            System.out.println(str.getClass());
        }

        public void getObjClass() {
            System.out.println(this.clazz);
        }
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        //OuterChild outerChild = new OuterChild();
        //outerChild.inner.m();
        Gen gen = new Gen<String>("vvv");
        gen.getObjClass();
        gen.copy();
    }
}