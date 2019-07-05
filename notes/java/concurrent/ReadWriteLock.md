## [**ReadWriteLock**](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/readwritelock)
- [demo](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/readwritelock/ReadWriteLockDemo.java)
- [CacheDemo](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/readwritelock/CacheDemo.java)
### 三大原则
读写锁作为一个通用技术，遵循以下三大原则：

- 允许多个线程同时读共享变量；
- 同一时刻只允许一个线程写变量；
- 如果一个线程正在执行写操作，则禁止其他线程进行读操作。

从以上可以看出，读写锁允许多个线程同时读共享变量，但对于写写、读写操作是互斥的。

### 应用场景
由于 **ReadWriteLock** 允许多个线程同时读共享变量，因此它特别适用于读多写少的场景，比如缓存系统等。

### 读写锁的升级与降级
锁的升级意思是读锁升级为写锁，也就是增加了锁的互斥性(锁的最主要的目的就是保证互斥，所以互斥性增加了，直观上的感受就是锁被升级了)，但是 **ReadWriteLock** 不支持锁升级，如以下代码：
```java
// 读缓存
r.lock();
try {
    v = m.get(key);
    if (v == null) {
        w.lock();
        try {
            // 再次验证并更新缓存
            // 省略详细代码
        } finally{
            w.unlock();
        }
    }
} finally{
    r.unlock();
}
```
上述代码中，在读锁还没有释放的情况下就去获取写锁，这会导致写锁获取失败，使程序永远在获取写锁的时候等待，从而造成阻塞，让相关的线程永远没有机会被唤醒。**java** 之所以不支持锁升级，究其原因，很有可能是因为可能会有多个线程同时持有读锁，此时想要获取写锁，由于同一时刻只能有一个线程获取到，所以可能会获取不到而导致阻塞较长时间，**java SDK** 从而也就放弃了锁升级。

但是锁的降级在 **ReadWriteLock** 中是被允许的，也就是写锁降级为读锁。如以下代码所示(来自官方示例)：
```java
// 获取写锁
w.lock();
try {
// 再次检查状态  
if (!cacheValid) {
    data = ...
    cacheValid = true;
    }
    // 释放写锁前，降级为读锁
    // 降级是可以的
    r.lock();
} finally {
    // 释放写锁
    w.unlock(); 
}
```
### 总结
读写锁类似于 **ReentrantLock** ，也支持公平和非公平模式。另外读锁和写锁都实现了java.util.concurrent.locks.Lock接口，因此也支持 **tryLock()** 和 **lockInterruptibly()** 等方法，但有一点要注意的是只有写锁支持条件变量，读锁是不支持的，所以读锁不能调用 **newCondition()**。
