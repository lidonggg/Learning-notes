package com.lidong.algorithm.leetcode.hard.linkedlist;

import java.util.HashMap;

/**
 * @author Ls J
 * @date 2020/4/19 12:47 AM
 * LRU缓存机制（困难-146）
 * 问题描述：
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥已经存在，则变更其数据值；如果密钥不存在，则插入该组「密钥/数据值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶:
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 */
public class Lruache146 {

    private DoubleList cache;

    private HashMap<Integer, Node> map;

    private int capacity;

    public Lruache146(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        int val = map.get(key).val;
        put(key, val);
        return val;
    }

    public void put(int key, int value) {
        Node newNode = new Node(key, value);
        if (map.containsKey(key)) {
            cache.remove(map.get(key));
            cache.addFirst(newNode);
            map.put(key, newNode);
        } else {
            if (cache.getSize() == capacity) {
                Node last = cache.removeLast();
                map.remove(last.key);
            }
            cache.addFirst(newNode);
        }
    }
}

/**
 * 定义一个双向链表
 */
class DoubleList {

    private Node head;

    private Node tail;

    private int size;

    DoubleList() {
        // 添加哨兵元素，避免边界处理
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    void addFirst(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
        size++;
    }

    void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    Node removeLast() {
        if (tail.prev == head) {
            return null;
        }
        Node last = tail.prev;
        remove(last);
        return last;
    }

    public int getSize() {
        return size;
    }

}

/**
 * 链表节点
 */
class Node {

    int key;

    int val;

    Node next;

    Node prev;

    Node(int k, int v) {
        this.key = k;
        this.val = v;
    }
}

