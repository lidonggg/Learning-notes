package com.lidong.algorithm.leetcode.medium.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * 打印零与奇偶数（中等-1116）
 * 问题描述：
 * 交替打印01020304
 *
 * @author ls J
 * @date 2019/7/27 3:19 PM
 */
public class ZeroEvenOdd1116 {

    private Lock lock = new ReentrantLock();

    private int n;

    private Condition zero, even, odd;

    private volatile int flag = 0;

    public ZeroEvenOdd1116(int n) {
        this.n = n;
        zero = lock.newCondition();
        even = lock.newCondition();
        odd = lock.newCondition();
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        try {

            lock.lock();
            for (int i = 1; i <= n; i++) {
                while (flag != 0) {
                    zero.await();
                }
                printNumber.accept(0);
                if ((i & 1) == 0) {
                    flag = 2;
                    even.signalAll();
                } else {
                    flag = 1;
                    odd.signalAll();
                }

            }
        } finally {
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        try {
            lock.lock();
            for (int i = 2; i <= n; i += 2) {
                while (flag != 2) {
                    even.await();
                }
                printNumber.accept(i);
                flag = 0;
                zero.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        try {
            lock.lock();
            for (int i = 1; i <= n; i += 2) {
                while (flag != 1) {
                    odd.await();
                }
                printNumber.accept(i);
                flag = 0;
                zero.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}
