## [Lock](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/lock)
[demo](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/lock)
### 1. **synchronized** 关键字的缺陷
**synchronized** 关键字在 **java** 1.6版本之后，性能获得了显著提升，那么在这种情况下，为什么还要有Lock的存在呢？这不得不从 **synchronized** 的缺陷来谈起。
一个代码块被 **synchronized**  关键字修饰之后，当一个线程获得了锁并且执行该代码块的时候，其他的线程便只能一直等待下去。而如果此线程在申请资源的时候申请不到，则线程直接进入了阻塞状态，并不能去主动释放已经占有的资源，在这种情况下，死锁问题便产生了。
因此还需要重新设计一把互斥锁，它在申请资源的时候，如果申请不到，应该能够主动释放它已占有的资源，这样的话，便可以破坏死锁的 “不可抢占” 这个条件。**java SDK** 的 **Lock** 接口就是为了解决这个问题而存在的。
### 2. **Lock** 接口的三个方法
``` java
// 支持中断的 API
void lockInterruptibly() throws InterruptedException;
// 支持超时的 API
boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
// 支持非阻塞获取锁的 API
boolean tryLock();
```
从这三个方法中我们可以发现，**Lock** 接口提供了三种方案去解决上述存在的问题：

- 响应中断。**synchronized** 存在的问题是，在持有锁 A 后，如果尝试获取锁 B 失败，那么线程就处于阻塞状态，一旦发生死锁，则没有任何方法来唤起已经阻塞的线程。但如果阻塞状态的线程能够响应中断信号，那它就很有机会重新被唤醒，然后释放已占有的锁 A ，这样就破坏了死锁的 “不可抢占” 条件了。
- 支持超时。如果线程在一定的时间内没有获取到锁，那它可以主动释放已占有的资源，“不可抢占” 的条件也可以被破坏。
- 非阻塞地获取锁。如果尝试获取锁失败，则直接返回，释放已占有的资源。

以上三个方法都可以破坏死锁的 “不可抢占” 条件，这也是 **Lock** 有别于 **synchronized** 的三个重要特性。

### 3. 可重入锁 **ReentrantLock**
可重入锁的意思是线程可以重复获取同一把锁，通过 **Lock** 接口的一个实现 **ReentrantLock** 可以实现可重入锁。
除了可重入锁，还有一个名词叫可重入函数，所谓可重入函数，指的是多个线程可以同时调用该函数，每个线程都能得到正确结果；同时在一个线程内支持线程切换，无论被切换多少次，结果都是正确的。多线程可以同时执行，还支持线程切换，这意味着可重入函数是线程安全的。

### 4. 公平锁与非公平锁
**ReentrantLock** 提供了两个构造函数，一个是无参的，另外一个有一个布尔类型的参数 fair，如果 fair 的值为 false，则构造出的响应的锁就是非公平的。
```java
// 无参构造函数：默认非公平锁
public ReentrantLock() {
    sync = new NonfairSync();
}
// 根据公平策略参数创建锁
public ReentrantLock(boolean fair){
    sync = fair ? new FairSync() : new NonfairSync();
}
```
每个锁都对应着一个等待队列，如果所是公平的，则在唤醒的时候谁等的时间长，就先唤醒谁，也就是遵循先进先出（FIFO）的原则；如果锁是非公平的，则并不会保证以上原则。

### 5. 用锁的最佳实践
出自 Doun Lea 的《java并发编程：设计原则与模式》：
> - 永远只在更新对象的成员变量时加锁
> - 永远只在访问可变的成员变量的时候加锁
> - 永远不在调用其他对象的方法时加锁

对于最后一条规则，主要是因为在其他对象的方法里，可能会有一些比较耗时的操作，例如 I/O 操作，或者调用 sleep() 方法休眠等，这些会严重影响性能。而且，其他的类的方法也可能会加锁，双重锁会导致死锁。

### 6. 总结
**Lock** 为我们写出安全、健壮的并发代码提供了很好的帮助，它刚好弥补了 **sychronized** 在某些方面的缺陷。但需要注意的是，在使用 **Lock** 的过程中，要注意手动释放所持有的锁。
