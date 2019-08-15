package com.lidong.java.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author ls J
 * @date 2019/8/15 9:54 AM
 * 斐波那契数列实现
 */
public class FibonacciTask extends RecursiveTask<Integer> {

    final int n;

    public FibonacciTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }

        FibonacciTask f1 = new FibonacciTask(n - 1);
        // 创建子任务
        f1.fork();
        FibonacciTask f2 = new FibonacciTask(n - 2);
        // 等待子任务结果，并合并结果
        return f2.compute() + f1.join();
    }

    public static void main(String[] args) {
        // 创建分治任务线程池
        ForkJoinPool fjp = new ForkJoinPool(4);
        // 创建分治任务
        FibonacciTask ft = new FibonacciTask(30);
        // 启动分治任务
        Integer result = fjp.invoke(ft);
        // 输出结果
        System.out.println(result);
    }
}
