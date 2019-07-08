## **StampedLock**
[demo]()

在读多写少的应用场景中，**java 1.8** 提供了一种更高效的读写锁。

### **StampedLock** 中的写锁、悲观读和乐观读
**StampedLock** 支持三种模式：写锁、悲观读锁和乐观读，其中写锁和悲观读锁与 **ReadWriteLock** 的写锁和读锁的原理相似，允许多个线程获取悲观读锁，只允许一个线程获取写锁，写锁与悲观读锁互斥。

**StampedLock** 在操作的时候多了一个邮戳（stamp）的概念，用来表示该锁的版本，在写锁和悲观读锁加锁成功之后都会返回一个 stamp，在解锁的时候则需要返回这个 stamp 。如以下代码所示：
```java
private final StampedLock sl = new StampedLock();
private T data;
public T read(){
    // 获取以及释放悲观读锁
    long stamp = sl.readLock();
    try {
      // 业务代码
    } finally {
      sl.unlockRead(stamp);
    }
    return data;
}

public void write(){
    // 获取以及释放写锁
    long stamp = sl.writeLock();
    try {
      // 业务代码
    } finally {
      sl.unlockWrite(stamp);
    }
}
```
悲观读锁之所以称之为悲观，是因为在读取操作之前，线程会悲观地认为有其他的线程可能会对自己的操作数据进行修改，从而要先对数据加锁，禁止写线程操作数据。

写锁和悲观读锁的性能与 **ReadWriteLock** 的性能基本一致，前者的性能之所以会有提升，是因为**乐观读**模式的存在。乐观读的主要思想就是：当多个线程同时读的时候，运行一个线程进行写操作，而不是阻塞写线程。

### 乐观读的使用方法
乐观读是不需要加锁的（从命名中就可以看出来，否则应该叫乐观读锁才读），**java SDK** 提供的示例如下：
```java
class Point {
  private int x, y;
  final StampedLock sl = new StampedLock();
  // 计算到原点的距离  
  int distanceFromOrigin() {
    // 乐观读
    long stamp = sl.tryOptimisticRead();
    // 读取局部变量
    int curX = x, curY = y;
    // 判断执行读操作期间，有没有被其他写线程抢占
    // 如果存在写操作，则升级为悲观读锁，重新读取数据
    if (!sl.validate(stamp)){    // 1
      // 升级为悲观读锁
      stamp = sl.readLock();
      try {
        curX = x;
        curY = y;
      } finally {
        // 释放悲观读锁
        sl.unlockRead(stamp);
      }
    }
    return Math.sqrt(
      curX * curX + curY * curY);
  }
}
```
由于 **tryOptimisticRead()** 是无锁的，所以在读取共享变量之后，需要判断在操作期间，有没有写线程获取到锁（也就是数据有可能被更改了），因此这里要进行一次验证，即通过 **validate(stamp)** 来实现，此方法的返回值为 boolean 类型，具体说明如下：

- 如果在本线程获取到 stamp 后没有获取过锁，则返回 true；
- 如果 stamp 为0，则始终返回 false；
- 如果 stamp 代表当前持有的锁，则返回 true；
- 调用此方法的参数如果使用未从 tryOptimisticRead() 方法返回的值（针对乐观读）或从锁的加锁方法返回的值（针对写锁和悲观读锁），则不会有预期的影响或结果。

由于使用的场景是读多写少，因此上述代码的 if 语句内的操作（也就是升级为悲观读锁）发生的概率比较低，因此整体上将会有一个性能的提升。

### 注意事项
**StampedLock** 的功能仅仅是 **ReadWriteLock** 的子集，前者不支持重入，并且前者的写锁和悲观读锁都不支持条件变量，因此在使用上会有一些限制。

还有一点需要注意的是，在使用 **StampedLock** 的时候一定不要调用中断操作，如果需要中断功能，一定要使用可中断的悲观读锁 **readLockInterruptibly()** 和写锁 **writeLockInterruptibly()**，否则会导致线程所在的CUP飙升到100%。

### 模板
#### **StampedLock** 读模板：
```java
final StampedLock sl = new StampedLock();

// 乐观读
long stamp = sl.tryOptimisticRead();
// 读入方法局部变量
......
// 校验 stamp
if (!sl.validate(stamp)){
    // 升级为悲观读锁
    stamp = sl.readLock();
    try {
        // 读入方法局部变量
        .....
    } finally {
        // 释放悲观读锁
        sl.unlockRead(stamp);
    }
}
// 使用方法局部变量执行业务操作
......
```

#### **StampedLock** 写模板：
```java
long stamp = sl.writeLock();
try {
    // 写共享变量
    ......
} finally {
    sl.unlockWrite(stamp);
}
```