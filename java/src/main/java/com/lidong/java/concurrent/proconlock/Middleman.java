package com.lidong.java.concurrent.proconlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ls J
 * @date 2019/7/3 10:53 AM
 * 生产者-消费者模型用Lock + Condition来实现
 */
public class Middleman {

    private static final int TOTAL = 10;

    private int count = 0;

    private Lock lock = new ReentrantLock();

    private Condition notEmpty = lock.newCondition();

    private Condition notFull = lock.newCondition();

    /**
     * 消费
     */
    public void take(Thread thread) {
        lock.lock();
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            while (count == 0) {
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count--;
            System.out.println("线程" + thread.getName() + "消费，消费后库存：" + count);
            notFull.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 生产
     */
    public void put(Thread thread) {
        lock.lock();
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            while (count == TOTAL) {
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count++;
            System.out.println("线程" + thread.getName() + "生产，生产后库存：" + count);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        Middleman middleman = new Middleman();
        new Thread(new Producer(middleman)).start();
        new Thread(new Producer(middleman)).start();
        new Thread(new Producer(middleman)).start();

        new Thread(new Consumer(middleman)).start();
        new Thread(new Consumer(middleman)).start();
        new Thread(new Consumer(middleman)).start();
    }
}
