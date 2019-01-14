package com.stepic.Threads;

public class Car extends Thread{

    public String name;
    public int weight;
    private Bridge target = null;
    
    Car(String name, int weight){
        this.name = name;
        this.weight = weight;
    }

    public void setTarget(Bridge target){
        this.target = target;
    }

    public void targetComplite() {
        target.release(this);
        target = null;
    }

    public boolean isTargetComplite(){
        return target == null;
    }
    
    @Override
    public void run() {
        try {
            if (target != null) {
                passTarget();
                //target.pass(this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void passTarget() throws InterruptedException {
        while (!isTargetComplite()) {
            if (target.tryPut(this)) {
                sleep(this.weight*10);
                targetComplite();
            }
        }
    }

    @Override
    public String toString() {
        return name + "\t(" + weight + ")";
    }

}
