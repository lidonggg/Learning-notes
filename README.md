# Learning-notes

## [设计模式](https://github.com/lidonggg/Learning-notes/blob/master/notes/designpattern/designpattern.md)
## 算法
### 链表
- [单链表反转](https://github.com/lidonggg/Learning-notes/blob/master/algorithm/src/main/java/com/lidong/algorithm/linkedlist/LinkedListReserve.java)
  - 非递归法
  - 递归法
- [链表中环的检测](https://github.com/lidonggg/Learning-notes/blob/master/algorithm/src/main/java/com/lidong/algorithm/linkedlist/LinkedListCircle.java)
  - 环的存在与否
  - 环的长度
  - 环的入口节点
### 树
- [二叉树遍历(前中后序递归/非递归遍历共6种)](https://github.com/lidonggg/Learning-notes/blob/master/algorithm/src/main/java/com/lidong/algorithm/tree/binarytree/TreeTraverse.java)
### 查找
- [二分查找](https://github.com/lidonggg/Learning-notes/blob/master/notes/algorithm/二分查找.md)
  - 普通二分查找
  - 变形一：查找第一个与某值相等的元素的位置
  - 变形二：查找最后一个与某值相等的元素的位置
  - 变形三：查找第一个大于等于某值的位置
  - 变形四：查找最后一个小于等于某值的元素的位置
### 排序
- [冒泡排序](https://github.com/lidonggg/Learning-notes/blob/master/algorithm/src/main/java/com/lidong/algorithm/sort/BubbleSort.java)
- [插入排序](https://github.com/lidonggg/Learning-notes/blob/master/algorithm/src/main/java/com/lidong/algorithm/sort/InsertSort.java)
- [选择排序](https://github.com/lidonggg/Learning-notes/blob/master/algorithm/src/main/java/com/lidong/algorithm/sort/SelectSort.java)
- [归并排序](https://github.com/lidonggg/Learning-notes/blob/master/algorithm/src/main/java/com/lidong/algorithm/sort/MergeSort.java)
- [快速排序](https://github.com/lidonggg/Learning-notes/blob/master/algorithm/src/main/java/com/lidong/algorithm/sort/QuickSort.java)
- [桶排序](https://github.com/lidonggg/Learning-notes/blob/master/algorithm/src/main/java/com/lidong/algorithm/sort/BucketSort.java)
- [计数排序](https://github.com/lidonggg/Learning-notes/blob/master/algorithm/src/main/java/com/lidong/algorithm/sort/CountingSort.java)
- [基数排序](https://github.com/lidonggg/Learning-notes/blob/master/algorithm/src/main/java/com/lidong/algorithm/sort/RadixSort.java)

### 回溯算法
- [八皇后](https://github.com/lidonggg/Learning-notes/blob/master/algorithm/src/main/java/com/lidong/algorithm/backtracking/EightQueens.java)

### leetcode
- [按照难度排序](https://github.com/lidonggg/Learning-notes/blob/master/notes/algorithm/leetcode/by-difficulty.md)
- [按照考点排序](https://github.com/lidonggg/Learning-notes/blob/master/notes/algorithm/leetcode/by-knowledge.md)

## java
### [common](https://github.com/lidonggg/Learning-notes/blob/master/notes/java/common)
### 多线程
java 提供了非常完备的并发功能，这些知识学起来却不是特别容易。

#### 1.java并发理论基础知识
- [线程的生命周期](https://github.com/lidonggg/Learning-notes/blob/master/notes/java/concurrent/线程的生命周期.md)
#### 2.java并发工具类
- [Lock](https://github.com/lidonggg/Learning-notes/blob/master/notes/java/concurrent/Lock.md)
- [ReadWriteLock](https://github.com/lidonggg/Learning-notes/blob/master/notes/java/concurrent/ReadWriteLock.md)
- [Semaphore](https://github.com/lidonggg/Learning-notes/blob/master/notes/java/concurrent/Semaphore.md)
- [StampedLock](https://github.com/lidonggg/Learning-notes/blob/master/notes/java/concurrent/StampedLock.md)
- [CountDownLatch](https://github.com/lidonggg/Learning-notes/blob/master/notes/java/concurrent/CountDownLatch.md)
- [CyclicBarrier](https://github.com/lidonggg/Learning-notes/blob/master/notes/java/concurrent/CyclicBarrier.md)
- [并发容器](https://github.com/lidonggg/Learning-notes/blob/master/notes/java/concurrent/并发容器.md)
- [原子类-无锁工具类](https://github.com/lidonggg/Learning-notes/blob/master/notes/java/concurrent/原子类-无锁工具类.md)
- [Executor与线程池](https://github.com/lidonggg/Learning-notes/blob/master/notes/java/concurrent/Executor与线程池.md)
- [Future：烧水泡茶](https://github.com/lidonggg/Learning-notes/blob/master/notes/java/concurrent/Future-烧水泡茶.md)
- [CompletableFuture：异步编程](https://github.com/lidonggg/Learning-notes/blob/master/notes/java/concurrent/CompletableFuture：异步编程.md)
- [CompletionService：批量执行异步任务](https://github.com/lidonggg/Learning-notes/blob/master/notes/java/concurrent/CompletionService：批量执行异步任务.md)
- [Fork-Join：并行计算框架](https://github.com/lidonggg/Learning-notes/blob/master/notes/java/concurrent/Fork-Join：并行计算框架.md)
#### 3.并发设计模式
- [生产者-消费者模型](https://github.com/lidonggg/Learning-notes/blob/master/notes/java/concurrent/producer-consumer.md)

