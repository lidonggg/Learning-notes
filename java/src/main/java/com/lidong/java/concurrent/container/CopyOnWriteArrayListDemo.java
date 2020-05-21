package com.lidong.java.concurrent.container;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList 示例
 *
 * @author ls J
 * @date 2019/7/11 11:08 AM
 */
public class CopyOnWriteArrayListDemo {

    private List<Integer> list;

    CopyOnWriteArrayListDemo() {
        list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 100; ++i) {
            list.add(i);
        }
    }

    /**
     * 读操作
     */
    public void read() {
        Iterator integer = list.iterator();
        while (integer.hasNext()) {
            System.out.print(integer.next() + " ");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    /**
     * 写操作
     */
    public void write() {
        for (int i = 0; i < 100; ++i) {
            list.add(i * 2);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayListDemo cwa = new CopyOnWriteArrayListDemo();
        // 如果使用ArrayList此时会报ConcurrentModificationException异常
        Thread t1 = new Thread(cwa::read);
        t1.start();
        Thread.sleep(10);
        Thread t2 = new Thread(cwa::write);
        t2.start();

        t1.join();
        t2.join();

        cwa.read();
    }
}
