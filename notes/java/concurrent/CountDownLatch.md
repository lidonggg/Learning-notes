## **CountDownLatch**
[demo](https://github.com/lidonggg/Learning-notes/blob/master/java/src/main/java/com/lidong/java/concurrent/countdownlatch/CountDownLatchDemo.java)
设想以下场景：有三个线程A、B、C，其中A、B线程互不相关，而C线程则依赖A、B线程所产生的的结果，此时我们可以让这三个线程串行化工作，更高效率的做法是让A、B线程并行工作，都完成之后再执行C线程，通过 ``thread.join()`` 方法我们可以实现上述要求：
```java
Thread threadA = new Thread(()->{
    // TODO A
});
threadA.start();
Thread threadB = new Thread(()->{
    // TODO B
});
threadB.start();
try{
    threadA.join();
    threadB.join();
    // TODO C
}catch (InterruptedException e){
    e.printStackTrace();
}
```

然而当使用线程池的时候，由于线程不会退出，所以 ``thread.join()`` 方法会失效，此时应该怎么办呢？

最直接的方法是维护一个计数器，初始值设置为 2，当A、B线程的操作执行完成之后，计数器的值分别减 1，然后在主线程中通过一个while循环去轮询计数器的值，当为 0 的时候，则去执行 C 线程的操作即可。CountDownLatch 刚好与此方法类似，但使用起来却更加简单。

从表面上来理解，**count down** 的意思是倒数，**latch** 的意思是门栓， 所以 CountDownLatch 有点类似于“3 2 1，芝麻开门”的意思，而这也正是它的作用所在（是不是与计数器的减 1 操作有异曲同工之妙？）。在不手动创建线程而是利用线程池（通过线程创建的线程池不会退出）的情况下，使用 CountDownLatch 可以实现一个或多个线程等待其他线程完成（退出）后再执行。

### 原理
CountDownLatch 基于 java 的 **AQS**，维护了一个 继承自 AQS 的内部类 **Sync**，在初始化的时候，它会传入计数器参数实例化出一个 Sync 实例，通过 Sync 实例，可以设置或获取到 state 的值。CountDownLatch 主要的方法有两个，一个是 ``countDown()``，另外一个是 ``await()``。
#### **countDown()**
``countDown()`` 方法通过 Sync 实例来实现 state 值的减 1 操作，Sync 实例则通过 ``unsafe.compareAndSwapInt()`` 方法来原子性地更新 state 的值。如果 state 的值为 0 了，则通过 ``LockSupport.unpark()`` 方法来唤醒 ``await()`` 方法中挂起的线程。

#### **await()**
主线程执行 ``await()`` 方法，如果上文中的 state 不等于 0 ，则返回 -1，否则此线程被加入到等待队列，等待执行。

### 使用
CountDownLatch 主要用于一个线程等待多个线程执行完成的情况。比如说并行执行若干个不相关的查询操作，但后续操作却需要前面的所有的查询结果，此时我们就可以使用  CountDownLatch，代码如下（出自极客时间）：
```java
// 创建 2 个线程的线程池
Executor executor = Executors.newFixedThreadPool(2);
while(存在未对账订单){
    // 计数器初始化为 2
    CountDownLatch latch = new CountDownLatch(2);
    // 查询未对账订单
    executor.execute(()-> {
        pos = getPOrders();
        latch.countDown();
    });
    // 查询派送单
    executor.execute(()-> {
        dos = getDOrders();
        latch.countDown();
    });
  
    // 等待两个查询操作结束
    latch.await();
  
    // 执行对账操作
    diff = check(pos, dos);
    // 差异写入差异库
    save(diff);
}
```
### 总结
CountDownLatch  是一个简单易用的线程同步工具类，主要用来解决一个线程等待多个线程的场景。CountDownLatch 内部维护了一个计数器，一旦计数器的值减到 0 ，并且有线程调用其 ``await()`` 方法，则该线程会直接通过，因此 CountDownLatch 的计数器不能循环利用。
