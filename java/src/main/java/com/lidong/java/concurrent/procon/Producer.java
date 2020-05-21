package com.lidong.java.concurrent.procon;

/**
 * 生产者
 *
 * @author Ls J
 * @date 2019/6/28 8:55 PM
 */
public class Producer implements Runnable {

    private Middleman middleman;

    public Producer(Middleman middleman) {
        this.middleman = middleman;
    }

    @Override
    public void run() {
        while (true) {
            middleman.put();
        }
    }
}
