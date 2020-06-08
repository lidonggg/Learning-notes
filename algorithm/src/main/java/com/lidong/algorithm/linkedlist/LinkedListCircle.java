package com.lidong.algorithm.linkedlist;

/**
 * 检测链表中环的存在
 *
 * @author Ls J
 * @date 2019/4/30 3:08 PM
 */
public class LinkedListCircle {

    /**
     * 检测链表中环的存在
     * 通过快慢指针的方式，如果两个指针在遍历的过程中能相遇，说明存在环
     *
     * @param head 头节点
     * @return 如果存在环，则返回true
     */
    private static boolean testLinkedListCircle(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast != null && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();

            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    /**
     * 获取环形链表中环的长度
     *
     * @param head 链表头节点
     * @return 环的长度，不存在环则返回0
     */
    private static int getLinkedListCircleLength(ListNode head) {
        ListNode fast = head, slow = head;

        int cLen = 0;
        while (fast != null && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();

            // 如果快慢指针相遇了，暂停本while循环，让fast不动，slow继续
            if (fast == slow) {
                break;
            }
        }

        // fast不等于null，代表上一个while是循环提前退出的，存在环
        if (fast != null && fast.getNext() != null) {
            // 相遇的时候特殊处理
            slow = slow.getNext();
            // 第一次相遇开始记录cLen
            cLen = 1;
            // slow继续移动，直到下次与fast相遇，此时slow每走一步，cLen就+1
            while (slow != fast) {
                slow = slow.getNext();
                cLen++;
            }
        }

        return cLen;
    }

    /**
     * 中文地址：https://leetcode-cn.com/problems/linked-list-cycle-ii/
     * 获取环的入口点
     * 如果单链表有环，按照判断是否有环的思路，当快指针和慢指针相遇时，slow指针肯定没有遍历完链表，而fast指针已经在环内循环了n圈。假设slow指针走了s步，则fast指针走了2*s步，设环长为r，则：
     * 2*s=s+n*r;
     * s=n*r;
     * 设链表头到环入口距离为l，入口处距离相遇点距离为a，则：
     * s=l+a+m*r;
     * 得：l=（n-m）r - a
     * 所以，相遇后，如果在链表头和相遇点各设置一个指针，每次走一步，两指针必定相遇，且在相遇第一个点为环入口
     *
     * @param head 链表头节点
     * @return 入口点，不存在则返回null
     */
    public static ListNode getLinkedListCircleEntrance(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            // 第一次相遇，退出 while 循环
            if (fast == slow) {
                break;
            }
        }

        // 此时代表不存在环，直接退出
        if (fast == null || fast.getNext() == null) {
            return null;
        }

        // 将其中一个指针指向链表头部，然后一起步进
        slow = head;
        // 相遇则停止 while 循环，此时就是在入口处
        while (slow != fast) {
            slow = slow.getNext();
            fast = fast.getNext();
        }

        return fast;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);
        node7.setNext(node4);

        System.out.println(getLinkedListCircleLength(node1));
    }
}
