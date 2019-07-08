package com.lidong.java.concurrent.stampedlock;

import java.util.concurrent.locks.StampedLock;

/**
 * @author ls J
 * @date 2019/7/8 9:30 AM
 * 以下示例来自java8 sdk的官方示例
 */
public class StampedLockDemo {

    /**
     * 成员变量
     */
    private double x, y;

    /**
     * 实例化锁
     */
    private final StampedLock sl = new StampedLock();

    /**
     * 写锁
     *
     * @param deltaX x
     * @param deltaY y
     */
    void move(double deltaX, double deltaY) {
        long stamp = sl.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    /**
     * 乐观读
     *
     * @return double
     */
    double distanceFromOrigin() {

        // 尝试获取乐观读锁（1）
        long stamp = sl.tryOptimisticRead();
        // 读取变量
        double currentX = x, currentY = y;
        // 判断执行读操作期间，有没有被其他写线程抢占
        // 如果存在写操作，则升级为悲观读锁，重新读取数据
        if (!sl.validate(stamp)) {
            // 如果被抢占则获取一个悲观读锁
            stamp = sl.readLock();
            try {
                // 获取锁后重新读取变量
                currentX = x;
                currentY = y;
            } finally {
                // 释放悲观读锁
                sl.unlockRead(stamp);
            }
        }
        // 返回结果
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    /**
     * 锁升级
     *
     * @param newX x
     * @param newY y
     */
    void moveIfAtOrigin(double newX, double newY) {
        // 这里可以使用乐观读锁替换
        long stamp = sl.readLock();
        try {
            // 如果当前点在原点则移动
            while (x == 0.0 && y == 0.0) {
                // 尝试将获取的读锁升级为写锁
                long ws = sl.tryConvertToWriteLock(stamp);
                // 升级成功，则更新票据，并设置坐标值，然后退出循环
                if (ws != 0L) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    // 读锁升级写锁失败则释放读锁，显示获取独占写锁，然后循环重试
                    sl.unlockRead(stamp);
                    stamp = sl.writeLock();
                }
            }
        } finally {
            // 释放锁
            sl.unlock(stamp);
        }
    }
}
