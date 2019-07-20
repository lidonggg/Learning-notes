package com.lidong.java.concurrent.atomic;

/**
 * @author ls J
 * @date 2019/7/19 4:40 PM
 */
public class SimulatedCAS {

    volatile int count;

    int newValue;

    /**
     * 加一操作
     */
    public void addOne() {
        do {
            newValue = count + 1;
        } while (count != cas(count, newValue));
    }

    /**
     * 模拟实现 CAS，仅用来帮助理解
     *
     * @param expect   期望值（加一之前的count）
     * @param newValue 新值
     * @return 更新之前的值
     */
    synchronized int cas(int expect, int newValue) {
        // 读目前 count 的值
        int curValue = count;
        // 比较目前 count 值是否 == 期望值
        if (curValue == expect) {
            // 如果是，则更新 count 的值
            count = newValue;
        }
        // 返回写入前的值
        return curValue;
    }
}
