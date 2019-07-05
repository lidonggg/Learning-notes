package com.lidong.java.concurrent.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author ls J
 * @date 2019/7/4 9:09 AM
 * 信号量demo
 */
public class SemaphoreDemo {

    private static int count;

    private static final Semaphore SP = new Semaphore(1);

    public static void addOne() {
        try {
            SP.acquire();
            Thread.sleep(100);
            count += 1;
            System.out.println("当前值为：" + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            SP.release();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; ++i) {
            new Thread(SemaphoreDemo::addOne).start();
        }
    }
}
