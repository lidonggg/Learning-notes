package com.lidong.java.concurrent.semaphore;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @author ls J
 * @date 2019/7/4 9:23 AM
 * 一个简易的限流器demo
 */
public class CurrentLimiterDemo<T, K> {

    private final List<T> pool;

    private final Semaphore sp;

    public CurrentLimiterDemo(int size, T t) {
        pool = new Vector<T>() {
        };
        for (int i = 0; i < size; ++i) {
            pool.add(t);
        }
        sp = new Semaphore(size);
    }

    public K exec(Function<T, K> func) {
        T t = null;
        try {
            sp.acquire();
            t = pool.remove(0);
            Thread.sleep(1000);
            System.out.println("pool remaining:" + pool.size());
            return func.apply(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            pool.add(t);
            sp.release();
        }
    }

    public static void main(String[] args) {
        CurrentLimiterDemo cld = new CurrentLimiterDemo<Long, String>(10, 2L);
        for (int i = 0; i < 100; ++i) {
            new Thread(() ->
                    cld.exec(t -> {
                        System.out.println(Thread.currentThread().getName() + ":" + t);
                        return t.toString();
                    })
            ).start();
        }
    }
}
