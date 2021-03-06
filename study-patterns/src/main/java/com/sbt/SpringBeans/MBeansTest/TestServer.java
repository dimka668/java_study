package com.sbt.SpringBeans.MBeansTest;

//import com.sbt.SpringBeans.MBeansTest.*;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestServer {
    private ThreadPoolExecutor executor;
    private int rejected = 0;

    public TestServer(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    private static class Task implements Runnable {
        public void run() {
            Random random = new Random();
            int numLoops = Math.abs(random.nextInt()%5);
            for (int i = 0; i < numLoops; i++) {
                try {
                    System.out.println("Running in [" + Thread.currentThread().getName() + "]");
                    Thread.sleep(Math.abs(random.nextInt()%5000));
                } catch(Exception e) {
                    System.out.println("Interrupted");
                }
            }
        }
    }

    public void runServer()	{
        executor.prestartAllCoreThreads();
        while (true) {
            try {
                executor.execute(new Task());
                Thread.sleep(1000);
            } catch (Exception e) {
                rejected++;
            }
        }
    }


    public ThreadPoolExecutor getExecutor()	{
        return executor;
    }

    public int getRejected() {
        return rejected;
    }

    public void flushRejected() {
        rejected = 0;
    }

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3, 5, 10L,
                TimeUnit.SECONDS,
                new SynchronousQueue < Runnable > ()
        );
        executor.prestartAllCoreThreads();
        TestServer server = new TestServer(executor);
        server.runServer();
    }
}