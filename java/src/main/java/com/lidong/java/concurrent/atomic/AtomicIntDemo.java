package com.lidong.java.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ls J
 * @date 2019/7/19 3:11 PM
 */
public class AtomicIntDemo {

    AtomicInteger count = new AtomicInteger(0);

    int dCount = 0;

    public void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count.getAndIncrement();
            dCount++;
        }
    }

    public static void main(String[] args) {
        AtomicIntDemo al = new AtomicIntDemo();
        for (int i = 0; i < 5; ++i) {
            new Thread(al::add10K).start();
        }

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // =50000
        System.out.println(al.count);
        // <=50000
        System.out.println(al.dCount);
    }
}
