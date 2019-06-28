package com.lidong.java.concurrent.procon;

/**
 * @author Ls J
 * @date 2019/6/28 8:55 PM
 * 生产者
 */
public class Producer implements Runnable {

    private Middleman middleman;

    public Producer(Middleman middleman){
        this.middleman = middleman;
    }

    @Override
    public void run() {
        while (true){
            middleman.put();
        }
    }
}
