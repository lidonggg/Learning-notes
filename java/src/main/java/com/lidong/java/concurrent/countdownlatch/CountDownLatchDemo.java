package com.lidong.java.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ls J
 * @date 2019/7/9 9:15 AM
 * 此示例仅演示CountDownLatch的使用方法，实际执行效果甚至后者要优于前者，
 * 主要是因为前者在执行过程中增加了额外的运算操作，导致了额外的成本的增加
 */
public class CountDownLatchDemo {

    private final static ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(4, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

    public static void doSumWithCdl() {
        long startTime = System.nanoTime();
        CountDownLatch cdl = new CountDownLatch(4);
        int[] arr = new int[4];
        for (int i = 0; i < 4; ++i) {
            final int ii = i;
            EXECUTOR.execute(() -> {
                for (int j = 0; j < 100000000 / 4; ++j) {
                    arr[ii] += 25000000 * ii + j;
                }
                cdl.countDown();
            });
        }
        try {
            cdl.await();
            long endTime = System.nanoTime();
            EXECUTOR.shutdown();
            System.out.println(doMerge(arr));
            System.out.println("线程池用时：" + (endTime - startTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 假设不会超过int最大值
     *
     * @param arr arr
     * @return int
     */
    private static int doMerge(int[] arr) {
        int res = 0;
        for (int i1 : arr) {
            res += i1;
        }
        return res;
    }

    /**
     * 在直接创建线程的情况下可以通过以下方法来实现类似的操作
     */
    public static void doWork() {
        Thread threadA = new Thread(() -> {
            // TODO A
        });
        threadA.start();
        Thread threadB = new Thread(() -> {
            // TODO B
        });
        threadB.start();
        try {
            threadA.join();
            threadB.join();
            // TODO C
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void doSumWithNothing() {
        long startTime = System.nanoTime();
        int sum = 0;
        for (int i = 0; i < 100000000; ++i) {
            sum += i;
        }
        long endTime = System.nanoTime();
        System.out.println(sum);
        System.out.println("单一线程用时：" + (endTime - startTime));
    }

    public static void main(String[] args) {
        doSumWithCdl();
        doSumWithNothing();
    }
}
