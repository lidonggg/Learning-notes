package com.lidong.java.concurrent.proconlock;

/**
 * @author ls J
 * @date 2019/7/3 11:03 AM
 */
public class Producer implements Runnable {

    private Middleman middleman;

    public Producer(Middleman middleman) {
        this.middleman = middleman;
    }

    @Override
    public void run() {
        while (true) {
            middleman.put(Thread.currentThread());
        }
    }
}
