## CyclicBarrier

### 使用 CyclicBarrier 实现线程同步

CountDownLatch 主要解决一个线程等待多个线程的场景，而 CyclicBarrier 则用来实现一组线程之间的相互等待。例如 [CountDownLatch](https://github.com/lidonggg/Learning-notes/blob/master/notes/java/concurrent/CountDownLatch.md) 一节中的对账操作，在进行一组数据对账的过程中，其实下一组数据的查询操作就可以开始了，使用 CountDownLatch 只能实现对账操作结束了再进行下一组账单查询，实际上，账单查询和对账还是串行化的。

利用 CyclicBarrier 我们可以这样做(出自极客时间)：
```java
// 订单队列
Vector<P> pos;
// 派送单队列
Vector<D> dos;
// 执行回调的线程池 
Executor executor = Executors.newFixedThreadPool(1);
final CyclicBarrier barrier =
    new CyclicBarrier(2, ()->{
        executor.execute(()->check());
    }
);
  
void check(){
    P p = pos.remove(0);
    D d = dos.remove(0);
    // 执行对账操作
    diff = checkAll(p, d);
    // 差异写入差异库
    save(diff);
}
  
void checkAll(){
    // 循环查询订单库
    Thread T1 = new Thread(()->{
        while(存在未对账订单){
            // 查询订单库
            pos.add(getPOrders());
            // 等待
            barrier.await();
        }
    });
    T1.start();  
    // 循环查询运单库
    Thread T2 = new Thread(()->{
        while(存在未对账订单){
            // 查询运单库
            dos.add(getDOrders());
            // 等待
            barrier.await();
        }
    });
    T2.start();
}
```
线程 T1 负责查询订单，当查出一条时，调用 barrier.await() 来将计数器减 1，同时等待计数器变成 0；线程 T2 负责查询派送单，当查出一条时，也调用 barrier.await() 来将计数器减 1，同时等待计数器变成 0；当 T1 和 T2 都调用 barrier.await() 的时候，计数器会减到 0，此时 T1 和 T2 就可以执行下一条语句了，同时会调用 barrier 的回调函数来执行对账操作。

### 总结
CountDownLatch 的计数器是不能循环利用的，也就是说一旦计数器减到 0，再有线程调用 await()，该线程会直接通过。但 CyclicBarrier 的计数器是可以循环利用的，而且具备自动重置的功能，一旦计数器减到 0 会自动重置到你设置的初始值。除此之外，CyclicBarrier 还可以设置回调函数，可以说是功能丰富。


