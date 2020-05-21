package com.lidong.java.concurrent.procon;

/**
 * 消费者
 *
 * @author Ls J
 * @date 2019/6/28 8:56 PM
 */
public class Consumer implements Runnable {

    private Middleman middleman;

    public Consumer(Middleman middleman) {
        this.middleman = middleman;
    }

    @Override
    public void run() {
        while (true) {
            middleman.take();
        }
    }
}
