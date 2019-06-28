## [生产者消费者模型](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/procon)

[源码](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/procon)

生产者-消费者模型在多线程领域算是一个最经典的模型之一。其关键思想大致如下：

- 生产者生产商品，直到库存已满，生产过程中通知消费者进行消费
- 消费者消费商品，直到库存不足，消费过程中通知生产者继续生产

为此，除了生产者和消费者，我们应该还需要一个中间商，负责管理库存，以及通知消费者消费或暂停消费、生产者生产或暂停生产，如下图所示：

<center>![生产者-消费者模型](https://github.com/lidonggg/Learning-notes/blob/master/imgs/producer-consumer.png)</center>
