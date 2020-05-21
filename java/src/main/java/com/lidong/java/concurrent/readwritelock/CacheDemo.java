package com.lidong.java.concurrent.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 缓存示例
 *
 * @author ls J
 * @date 2019/7/5 10:02 AM
 */
class CacheDemo<K, V> {
    private final Map<K, V> m =
            new HashMap<>();
    private final ReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    V get(K key) {
        V v;
        // 读缓存
        r.lock();
        try {
            v = m.get(key);
        } finally {
            r.unlock();
        }
        // 缓存中存在，返回
        if (v != null) {
            return v;
        }
        // 缓存中不存在，查询数据库
        w.lock();
        try {
            // 再次验证
            // 其他线程可能已经查询过数据库，也就是可能有其他线程先执行了w.lock()，此时则不用再去查数据库，直接取值就行，否则需要去查数据库
            v = m.get(key);
            if (v == null) {
                // 查询数据库
                // v = ...
                m.put(key, v);
            }
        } finally {
            w.unlock();
        }
        return v;
    }

    V put(K k, V v) {
        w.lock();
        try {
            return m.put(k, v);
        } finally {
            w.unlock();
        }
    }
}

