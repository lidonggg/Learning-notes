package com.lidong.java.concurrent.readwritelock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ls J
 * @date 2019/7/5 9:31 AM
 * 读写锁demo
 */
public class ReadWriteLockDemo {

    private static int value;

    private final static ReadWriteLock RWL = new ReentrantReadWriteLock();

    private final static Lock RL = RWL.readLock();

    private final static Lock WL = RWL.writeLock();

    public static int writeValue(int v) {
        WL.lock();
        try {
            value = v;
            Thread.sleep(1000);
            System.out.println("写之后值为：" + value);
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        } finally {
            WL.unlock();
        }
    }

    public static int readValue() {
        RL.lock();
        try {
            Thread.sleep(1000);
            System.out.println("读到的值为：" + value);
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        } finally {
            RL.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> ReadWriteLockDemo.writeValue(1)).start();
        new Thread(() -> ReadWriteLockDemo.writeValue(2)).start();
        new Thread(() -> ReadWriteLockDemo.writeValue(3)).start();

        new Thread(ReadWriteLockDemo::readValue).start();
        new Thread(ReadWriteLockDemo::readValue).start();
        new Thread(ReadWriteLockDemo::readValue).start();
    }
}
