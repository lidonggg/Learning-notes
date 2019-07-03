package com.lidong.java.concurrent.proconlock;

/**
 * @author ls J
 * @date 2019/7/3 11:04 AM
 */
public class Consumer implements Runnable {

    private Middleman middleman;

    public Consumer(Middleman middleman) {
        this.middleman = middleman;
    }

    @Override
    public void run() {
        while (true) {
            middleman.take(Thread.currentThread());
        }
    }
}
