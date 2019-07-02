# Learning-notes

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
### leetcode
#### 简单
- [1.两数之和](https://github.com/lidonggg/Learning-notes/blob/master/algorithm/src/main/java/com/lidong/algorithm/leetcode/easy/TwoSum.java)
- [7.整数反转](https://github.com/lidonggg/Learning-notes/blob/master/algorithm/src/main/java/com/lidong/algorithm/leetcode/easy/ReverseSignedInt.java)
#### 中等
- [46.数组全排列](https://github.com/lidonggg/Learning-notes/blob/master/algorithm/src/main/java/com/lidong/algorithm/leetcode/middling/FullyArrange.java)
#### 困难

## java
### 多线程
java 提供了非常完备的并发功能，这些知识学起来却不是特别容易。在学习过程中，我主要通过如下途径来理解 java 的并发能力：

- 王宝令老师在极客时间开设的 "java并发编程实战专栏"，这也是我花心思最多的一个途径；
- Brian Goetz 出版的《java并发编程实战》;
- 并发大师 Doun Lea 的《java并发编程：设计原则与模式》。

以下的一些知识点是我对于在学习过程中的总结。

#### 1.java并发理论基础知识
#### 2.java并发工具类
- [Lock](https://github.com/lidonggg/Learning-notes/blob/master/notes/java/concurrent/Lock.md)
#### 3.并发设计模式
- [生产者-消费者模型](https://github.com/lidonggg/Learning-notes/blob/master/notes/java/concurrent/producer-consumer.md)
