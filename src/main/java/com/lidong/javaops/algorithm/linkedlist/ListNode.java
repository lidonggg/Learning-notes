package com.lidong.javaops.algorithm.linkedlist;

/**
 * @author Ls J
 * @date 2019/4/30 3:00 PM
 */
public class ListNode {
    protected int n;
    protected ListNode next;

    ListNode() {
        this.n = 0;
        this.next = null;
    }

    ListNode(int n) {
        this.n = n;
        this.next = null;
    }

    ListNode(int n, ListNode next) {
        this.n = n;
        this.next = next;
    }
}