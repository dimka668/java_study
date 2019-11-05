package com.ekkel.concurrency;

/**
 * Created by 16688641 on 28.03.2019.
 */

import java.util.concurrent.*;
import java.util.*;

public class ActiveObjectDemo {

    private ExecutorService ex = Executors.newSingleThreadExecutor();
    private Random rand = new Random(47);

    private void pause(int factor) {
        try {
            TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(factor));
        } catch (InterruptedException e) {
            System.out.println("sleep() interrupted");
        }
    }

    public Future<Integer> calculatelnt(final int x, final int y) {
        return ex.submit(new Callable<Integer>() {
            public Integer call() {
                System.out.println("starting " + x + " + " + y);
                pause(500);
                return x + y;
            }
        });
    }

    public Future<Float> calculateFloat(final float x, final float y) {
        return ex.submit(new Callable<Float>() {
            public Float call() {
                System.out.println("starting " + x + " + " + y);
                pause(2000);
                return x + y;
            }
        });
    }

    public void shutdown() {
        ex.shutdown();
    }

    public static void main(String[] args) {
        ActiveObjectDemo dl = new ActiveObjectDemo();
        List<Future<?>> results = new CopyOnWriteArrayList<Future<?>>();
        for (float f = 0.0f; f < 1.0f; f += 0.2f)
            results.add(dl.calculateFloat(f, f));
        for (int i = 0; i < 5; i++)
            results.add(dl.calculatelnt(i, i));
        System.out.println("All asynch calls made");
        while (results.size() > 0) {
            for (Future<?> f : results)
                if (f.isDone()) {
                    try {
                        System.out.println(f.get());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    results.remove(f);
                }
        }
        dl.shutdown();
    }
}
