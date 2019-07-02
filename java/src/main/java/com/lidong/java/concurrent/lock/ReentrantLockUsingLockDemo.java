package com.lidong.java.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ls J
 * @date 2019/7/2 13:43
 */
public class ReentrantLockUsingLockDemo {

    private final Lock lock = new ReentrantLock();

    private int value = 0;

    public int getValue() {
        lock.lock();
        try {
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void increment() {
        lock.lock();
        try {
            value = getValue() + 1;
            System.out.println("+1后的值为: " + value);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockUsingLockDemo rld = new ReentrantLockUsingLockDemo();
        new Thread(rld::increment).start();
        new Thread(rld::increment).start();
        new Thread(rld::increment).start();
        new Thread(rld::increment).start();
        new Thread(rld::increment).start();
    }
}
