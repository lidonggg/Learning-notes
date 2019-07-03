package com.lidong.java.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ls J
 * @date 2019/7/2 1:58 PM
 */
public class ReentrantLockUsingTryLockDemo {

    private final Lock lock = new ReentrantLock();

    private int value;

    private void increment(Thread thread) {
        if(lock.tryLock()){
            try {
                System.out.println("thread-" + thread.getName() + "获取锁成功");
                value++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("thread-" + thread.getName() + "释放锁");
                lock.unlock();
            }
        }else{
            System.out.println("thread-" + thread.getName() + "获取锁失败");
        }
    }

    public static void main(String[] args) {
        ReentrantLockUsingTryLockDemo rtd = new ReentrantLockUsingTryLockDemo();
        Runnable runnable = () -> rtd.increment(Thread.currentThread());
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
