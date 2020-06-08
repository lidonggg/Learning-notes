## **Executor** 与线程池

创建线程，需要调用操作系统内核的 API，然后操作系统为线程分配一系列资源，这样的话，创建线程的成本会非常高，因此线程是一个重量级的对象，应该避免频繁地创建和销毁。通过使用线程池，可以有效地避免上述情况的发生。并且，由于当请求到达时线程已经存在，因此无意中也消除了由于创建线程所带来的的延迟，从而降低了请求的响应时间。通过合理地控制线程池中的线程数目，可以有效地避免资源不足的情况，防止由于线程过多地创建而产生 OOM 等。

### java 线程池的工作原理
java 提供的线程池是一种生产者-消费者模式，这也是目前业界普遍采用的线程池的设计模式。
java 线程池内部维护了一个阻塞队列 **workQueue**和一组工作线程，工作线程的个数由构造函数的参数来指定。用户通过 execute() 方法来提交 Runnable 任务，execute() 方法的作用仅仅是将任务加入到 workQueue 中，然后 ThreadPool 内部维护的工作线程会循环消费并执行 workQueue 中的任务。

### 如何使用 java 的线程池

#### 1. 使用 **ThreadPoolExecutor**
ThreadPoolExecutor 的构造函数提供了多个参数，最完备的构造函数如下所示：
```java
/**
 * Creates a new {@code ThreadPoolExecutor} with the given initial
 * parameters.
 *
 * @param corePoolSize the number of threads to keep in the pool, even
 *        if they are idle, unless {@code allowCoreThreadTimeOut} is set
 * @param maximumPoolSize the maximum number of threads to allow in the
 *        pool
 * @param keepAliveTime when the number of threads is greater than
 *        the core, this is the maximum time that excess idle threads
 *        will wait for new tasks before terminating.
 * @param unit the time unit for the {@code keepAliveTime} argument
 * @param workQueue the queue to use for holding tasks before they are
 *        executed.  This queue will hold only the {@code Runnable}
 *        tasks submitted by the {@code execute} method.
 * @param threadFactory the factory to use when the executor
 *        creates a new thread
 * @param handler the handler to use when execution is blocked
 *        because the thread bounds and queue capacities are reached
 * @throws IllegalArgumentException if one of the following holds:<br>
 *         {@code corePoolSize < 0}<br>
 *         {@code keepAliveTime < 0}<br>
 *         {@code maximumPoolSize <= 0}<br>
 *         {@code maximumPoolSize < corePoolSize}
 * @throws NullPointerException if {@code workQueue}
 *         or {@code threadFactory} or {@code handler} is null
 */
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler) {
    if (corePoolSize < 0 ||
        maximumPoolSize <= 0 ||
        maximumPoolSize < corePoolSize ||
        keepAliveTime < 0)
        throw new IllegalArgumentException();
    if (workQueue == null || threadFactory == null || handler == null)
        throw new NullPointerException();
    this.corePoolSize = corePoolSize;
    this.maximumPoolSize = maximumPoolSize;
    this.workQueue = workQueue;
    this.keepAliveTime = unit.toNanos(keepAliveTime);
    this.threadFactory = threadFactory;
    this.handler = handler;
} 
```
- **corePoolSize**：当线程池比较空闲的时候，线程池保有的最小线程数；
- **maximumPoolSize**：线程池创建的最大线程数；
- **keepAliveTime && unit**：线程空闲时，线程的存活时间，unit 表示时间的单位；
- **workQueue**：工作队列（nWorks）；
- **ThreadFactory**：可选参数，线程工厂，可以自定义如何创建线程，例如指定线程的名称；
- **handler**：可选参数，任务的拒绝策略，如果线程池中所有的线程都在工作，并且线程池（有界）已满，那么此时提交任务，线程池就会拒绝接收。ThreadPoolExecutor 提供了如下四种拒绝策略：
    - CallerRunsPolicy：提交任务的线程自己去执行该任务；
    - AbortPolicy：默认的拒绝策略，会抛出异常 RejectedExecutionException；
    - DiscardPolicy：直接丢弃任务，不会抛出任何异常；
    - DiscardOldestPolicy：丢弃最老的任务，也就是把最早进入工作队列的任务丢弃，然后把新的任务加入到工作队列。

Java 在 1.6 版本还增加了 allowCoreThreadTimeOut(boolean value) 方法，它可以让所有线程都支持超时，这意味着如果项目很闲，就会将项目组的成员都撤走。
##### 运行机制

**1. 线程池五种状态**

- **RUNNING**：能接受新提交的任务，也能处理阻塞队列中的任务。
- **SHUTDOWN**：关闭状态，不再接受新提交的任务，但是却可以继续处理阻塞队列中已保存的任务。在线程池处于 RUNNING 状态时，调用 shutdown() 方法会使线程池进入到该状态。（finalize() 方法在执行过程中也会调用shutdown()方法进入该状态）。
- **STOP**：不能接受新任务，也不处理队列中的任务，会中断正在处理任务的线程。在线程池处于 RUNNING 或 SHUTDOWN 状态时，调用 shutdownNow() 方法会使线程池进入到该状态。
- **TIDYING**：如果所有的任务都已终止了，workerCount (有效线程数)为0，线程池进入该状态后会调用 terminated() 方法进入TERMINATED 状态。
- **TERMINATED**：在 terminated() 方法执行完后进入该状态，默认 terminated() 方法中什么也没有做。进入 TERMINATED 的条件如下：
    - 线程池不是 RUNNING 状态；
    - 线程池状态不是 TIDYING 状态或 TERMINATED 状态；
    - 如果线程池状态是 SHUTDOWN 并且 workerQueue 为空；
    - workerCount 为 0；
    - 设置 TIDYING 状态成功。

这几种状态之间的转换如下图所示：
<div align=center><img src="https://github.com/lidonggg/Learning-notes/blob/master/notes/java/concurrent/images/ThreadPoolExector-status.png"/></div>

**2. execute()**

通过 new 创建线程池时，除非调用 prestartAllCoreThreads 方法初始化核心线程数，否则此时线程池中有 0 个线程，即使工作队列中存在多个任务，同样不会执行。

我们假设任务数为 x：

- 当 x <= corePoolSize 时，只启动 x 个线程；
- 当 x >= corePoolSize && x < nWorks + corePoolSize 时，会启动 <= corePoolSize 个线程，其他任务放到工作队列中；
- 当 x > corePoolSize && x > nWorks + corePoolSize 时，分为两种情况：
    - 当 x - nWorks <= maximumPoolSize 时，会启动 (x - nWorks) 个线程;
    - 当 x - nWorks > maximumPoolSize，会启动 maximumPoolSize 个线程来执行任务，其余的执行相应的拒绝策略。

以上处理流程是在 Execute() 方法中执行的。

**3. addWorker()**

addWorker() 方法的签名如下：
```java
private boolean addWorker(Runnable firstTask, boolean core);
```
它的主要工作是在线程池中创建一个新的线程并执行，firstTask 参数用于指定新增的线程执行的第一个任务，core 参数为 true 表示在新增线程时会判断当前活动线程数是否少于 corePoolSize，false 表示新增线程前需要判断当前活动线程数是否少于 maximumPoolSize。

**4. Worker 类**

线程池中的每一个线程被封装成一个 Worker 对象，ThreadPool 维护的其实就是一组 Workder 对象。它类继承了 AQS 来实现独占锁的功能，并实现了 Runnable 接口。
    
#### 2. 为什么不推荐使用 **Executors** ?
由于 ThreadPoolExecutor 的构造函数比较复杂，java 并发包还提供了一个线程的静态工厂类 Executors，利用它可以快速地创建线程。它提供了六种创建线程的方法：
```java
// 创建一个可以根据需要创建新线程的线程池，如果线程池中有空闲线程，则优先使用空闲的线程
// 内部使用 SynchronousQueue 作为工作队列
ExecutorService executorService = Executors.newCachedThreadPool();
// 创建一个固定大小的线程池，在任何时候，最多只有 N 个线程在处理任务
// 背后使用的是无界的工作队列，任何时候最多有 nThreads 个工作线程是活动的
ExecutorService executorService1 = Executors.newFixedThreadPool(2);
// 能延迟执行、定时执行的线程池
// 操作一个无界的工作队列，所以它保证了所有任务的都是被顺序执行的
ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
// 工作窃取线程池，使用多个队列来减少竞争
// 内部会构建 ForkJoinPool，利用 Work-Stealing 算法，并行地处理任务，不保证处理顺序
ExecutorService executorService2 = Executors.newWorkStealingPool();
// 单一线程的线程池，只会使用唯一一个线程来执行任务，即使提交再多的任务，也都是会放到等待队列里进行等待
ExecutorService executorService3 = Executors.newSingleThreadExecutor();
// 单线程能延迟执行、定时执行的线程池
ScheduledExecutorService scheduledExecutorService1 = Executors.newSingleThreadScheduledExecutor();
```
通过这六种方法都最终创建出了一个 ThreadPoolExecutor 实例。但是为什么要尽量避免这么使用呢？不建议使用 Executors 的最重要的原因是：Executors 提供的很多方法默认使用的都是无界的（请求队列长度为 Integer.MAX_VALUE） LinkedBlockingQueue，高负载情境下，无界队列很容易导致 OOM，而 OOM 会导致所有请求都无法处理，这是致命问题。newFixedThreadPool、newSingleThreadExecutor 允许的最大请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM；而其他的方法例如 newCachedThreadPool、 newScheduledThreadPool，它们允许的最大创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM（创建线程时用的内存并不是我们制定jvm堆内存，而是系统的剩余内存）。

### 注意事项
- 尽量不要使用 Executors，要使用 ThreadPoolExecutor 来通过有界队列创建线程池，原因就是使用前者容易在负载下产生 OOM 危险；
- 使用有界队列，当任务数过多时，不要使用默认的拒绝策略。默认的拒绝策略会触发 RejectedExecutionException 这个运行时异常，对于运行时异常编译器不会强制 catch 它，所以很容易被忽略掉。如果处理的任务非常重要，最好自定义拒绝策略，它往往会和降级策略配合使用；
- 注意对异常的处理，例如通过 ThreadPoolExecutor 对象的 execute() 方法提交任务时，如果任务在执行的过程中出现运行时异常，会导致执行任务的线程终止；不过，最致命的是任务虽然异常了，但是你却获取不到任何通知，这会让你误以为任务都执行得很正常。虽然线程池提供了很多用于异常处理的方法，但是最稳妥和简单的方案还是捕获所有异常并按需处理。

### 参考

- [1] [深入理解java线程池：ThreadPoolExecutor](https://www.jianshu.com/p/d2729853c4da).
- [2] [Java 线程池实现原理及其在美团业务中的实践](https://tech.meituan.com/2020/04/02/java-pooling-pratice-in-meituan.html).
