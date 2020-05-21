package com.lidong.java.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 利用 FutureTask 来实现烧水泡茶
 *
 * @author ls J
 * @date 2019/8/2 10:36 AM
 */
public class BoilTeaWithFutureTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Boolean> ft2 = new FutureTask<>(new Task2());
        FutureTask<Boolean> ft1 = new FutureTask<>(new Task1(ft2));
        new Thread(ft1).start();
        System.out.println(ft1.get());
    }
}

class Task1 implements Callable<Boolean> {

    /**
     * t1 在执行过程中需要等待 t2 执行完成，所以需要拥有 t2 变量
     */
    private FutureTask<Boolean> tf2;

    Task1(FutureTask<Boolean> tf2) {
        this.tf2 = tf2;
    }

    @Override
    public Boolean call() throws Exception {
        System.out.println("T1: 洗水壶");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("T1: 烧开水");
        // 烧开水过程中 t2 开始执行
        new Thread(tf2).start();
        TimeUnit.SECONDS.sleep(15);
        Boolean rtf2 = tf2.get();
        System.out.println("T1: 拿到茶叶: " + rtf2);
        System.out.println("T1: 泡茶");
        return true;
    }
}

class Task2 implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception {
        System.out.println("T2: 洗茶壶");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("T2: 洗茶杯");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("T2: 拿茶叶");
        return true;
    }
}
