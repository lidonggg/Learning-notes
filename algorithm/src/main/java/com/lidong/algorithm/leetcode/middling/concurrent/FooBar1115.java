package com.lidong.algorithm.leetcode.middling.concurrent;

/**
 * 交替打印 FooBar（中等-1115）
 * 问题描述：
 * 交替打印 FooBar
 *
 * @author ls J
 * @date 2019/7/27 2:23 PM
 */
public class FooBar1115 {
    private int n;
    private volatile int lock = 0;

    public FooBar1115(int n) {
        this.n = n;
        this.lock = 2 * n;
    }

    public void foo(Runnable printFoo) {

        for (int i = 0; i < n; i++) {
            while (this.lock % 2 == 1) {
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            this.lock--;
        }
    }

    public void bar(Runnable printBar) {

        for (int i = 0; i < n; i++) {
            while (this.lock % 2 == 0) {

            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            this.lock--;
        }
    }

    public static void main(String[] args) {
        FooBar1115 foobar = new FooBar1115(10);
    }
}

