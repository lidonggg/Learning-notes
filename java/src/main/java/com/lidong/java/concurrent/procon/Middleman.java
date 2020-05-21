package com.lidong.java.concurrent.procon;

/**
 * 中间商
 *
 * @author Ls J
 * @date 2019/6/28 8:56 PM
 */
public class Middleman {

    private int num = 0;

    private static int TOTAL = 10;

    /**
     * 接收生产数据
     */
    public synchronized void put() {
        if (num < TOTAL) {
            System.out.println("新增库存 ------> 当前库存：" + ++num);
            // 唤醒所以有线程，包括生产者和消费者
            notifyAll();
        } else {
            // num = TOTAL，停止生产
            System.out.println("新增库存 ------> 库存已满，停止消费：" + num);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取消费数据
     */
    public synchronized void take() {
        if (num > 0) {
            System.out.println("消费库存 ------> 剩余库存：" + --num);
            notifyAll();
        } else {
            // num = 0，库存不足，停止消费
            System.out.println("消费库存 ------> 库存不足：" + num);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Middleman middleman = new Middleman();
        new Thread(new Consumer(middleman)).start();
        new Thread(new Consumer(middleman)).start();
        new Thread(new Consumer(middleman)).start();

        new Thread(new Producer(middleman)).start();
        new Thread(new Producer(middleman)).start();
        new Thread(new Producer(middleman)).start();
    }

}
