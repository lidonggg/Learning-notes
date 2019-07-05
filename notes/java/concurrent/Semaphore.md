## [**Semaphore**](https://github.com/lidonggg/Learning-notes/tree/master/notes/java/concurrent/Semaphore.md)
- [demo](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/semaphore/SemaphoreDemo.java)
- [限流器demo](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/semaphore/CurrentLimiterDemo.java)
除了管程之外，**Semaphore** 也是并发编程的另一选择。

### 信号量模型

信号量模型主要包括三部分组成：一个计数器、一个等待队列、三个原子性的方法，其中计数器和等待队列只能通过三个方法来进行访问。三个方法分别如下：

- **init()**：设置计数器的初始值；
- **down()**：计数器的值减一；如果此时计数器的值小于0，则当前线程将被阻塞，否则当前线程继续执行；
- **up()**：计数器的值加一；如果此时计数器的值小于或等于0，则唤醒等待队列中的一个线程，并将该线程从等待队列中移除。

由于信号量模型里面，**down()**、**up()** 最早被称为 P 操作和 V 操作，因此信号量模型也被称为**PV原语**。在 java SDK 的并发包里（java.util.concurrent.Semaphor），**down()** 和  **up()** 对应的方法是 **acquire()** 和 **release()**。

### 信号量的使用

信号量的使用跟锁类似，只需要在进入临界区之前执行一下 **down()** 操作，在退出临界区之前执行一下 **up()** 操作即可。相应的 **java** 使用方法如下：
```java
/**
 * @author ls J
 * @date 2019/7/4 9:09 AM
 * 信号量demo
 */
public class SemaphoreDemo {
    private static int count;
    private static final Semaphore SP = new Semaphore(1);
    public static void addOne() {
        try {
            SP.acquire();
            Thread.sleep(100);
            count += 1;
            System.out.println("当前值为：" + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            SP.release();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; ++i) {
            new Thread(SemaphoreDemo::addOne).start();
        }
    }
}

```
### 使用场景
由于 **Semaphore** 支持多个线程同时访问一个临界区，因此它可以实现很多 **Lock** 不容易实现的功能，例如通过使用 **Semaphore** 初始化不同的计数器，我们可以快速实现一个简单的限流器。[代码在这]
