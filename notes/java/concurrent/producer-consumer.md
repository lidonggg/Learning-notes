## [生产者消费者模型](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/procon)

[synchronized实现](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/procon)
[Lock实现](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/proconlock)
生产者-消费者模型在多线程领域算是一个最经典的模型之一。其关键思想大致如下：

- 生产者生产商品，直到库存已满，生产过程中通知消费者进行消费
- 消费者消费商品，直到库存不足，消费过程中通知生产者继续生产

为此，除了生产者和消费者，我们应该还需要一个中间商，负责管理库存，以及通知消费者消费或暂停消费、生产者生产或暂停生产，如下图所示：

<div align=center><img src="https://github.com/lidonggg/Learning-notes/blob/master/imgs/producer-consumer.png"/></div>

#### 中间商

中间商核心代码如下：

```java
/**
 * @author Ls J
 * @date 2019/6/28 8:56 PM
 * 中间商
 */
public class Middleman {

    private int num = 0;

    private static int TOTAL = 10;

    /**
     * 接收生产数据
     */
    public synchronized void put() {
        if (num < TOTAL) {
            System.out.println("新增库存 ------> 当前库存：" + ++num);
            // 唤醒所以有线程，包括生产者和消费者
            notifyAll();
        } else {
            // num = TOTAL，停止生产
            System.out.println("新增库存 ------> 库存已满，停止消费：" + num);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取消费数据
     */
    public synchronized void take() {
        if (num > 0) {
            System.out.println("消费库存 ------> 剩余库存：" + --num);
            notifyAll();
        } else {
            // num = 0，库存不足，停止消费
            System.out.println("消费库存 ------> 库存不足："+num);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

其中，主要有两个方法： ``put()`` 方法负责接收生产数据，并对库存是否已满进行判断；``take()`` 方法负责消费数据，并对库存是否为空进行判断。唤醒方法我们采用了 ``notifyAll()`` 方法，它可以唤醒所以在对象上等待的生产者和消费者线程，从而避免出现生产者或者消费者线程饥饿（比如在库存已满的时候，如果采用 ``notify()`` 方法，很有可能会继续唤醒一个生产者线程，这样的话则无法进行消费）。

#### 生产者消费者

核心代码如下：

```java
/**
 * @author Ls J
 * @date 2019/6/28 8:55 PM
 * 生产者
 */
public class Producer implements Runnable {

    private Middleman middleman;

    public Producer(Middleman middleman){
        this.middleman = middleman;
    }

    @Override
    public void run() {
        while (true){
            middleman.put();
        }
    }
}

/**
 * @author Ls J
 * @date 2019/6/28 8:56 PM
 * 消费者
 */
public class Consumer implements Runnable{

    private Middleman middleman;

    public Consumer(Middleman middleman){
        this.middleman = middleman;
    }

    @Override
    public void run() {
        while (true){
            middleman.take();
        }
    }
}
```

生产者和消费者共同维护一个中间商，生产者负责调用 ``put()`` 方法，消费者负责调用 ``take()`` 方法。
