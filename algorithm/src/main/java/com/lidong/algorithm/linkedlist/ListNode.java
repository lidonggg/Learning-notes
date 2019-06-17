package com.lidong.algorithm.linkedlist;

/**
 * @author Ls J
 * @date 2019/4/30 3:00 PM
 */
class ListNode {

    private int data;

    private ListNode next;

    public ListNode() {
        this.data = 0;
        this.next = null;
    }

    public ListNode(int n) {
        this.data = n;
        this.next = null;
    }

    public ListNode(int n, ListNode next) {
        this.data = n;
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}