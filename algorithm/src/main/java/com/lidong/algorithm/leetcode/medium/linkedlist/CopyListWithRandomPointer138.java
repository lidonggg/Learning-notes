package com.lidong.algorithm.leetcode.medium.linkedlist;

/**
 * 复制带随机指针的链表（中等-138）
 * 中文链接：https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 * <p>
 * 问题描述：
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * 要求返回这个链表的 深拷贝。 
 * 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 *  
 * 示例 1：
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * <p>
 * 示例 2：
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * <p>
 * 示例 3：
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * <p>
 * 示例 4：
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *  
 * 提示：
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 *
 * @author ls J
 * @date 2020/6/24 13:18
 */
public class CopyListWithRandomPointer138 {

    /**
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.2 MB，在所有 Java 提交中击败了 72.73% 的用户
     *
     * @param head head 结点
     * @return 新数组 head 结点
     */
    public Node copyRandomList(Node head) {
        if (null == head) {
            return null;
        }
        Node pointer = head;
        // 让当前节点的下一个节点指向当前节点的拷贝
        // 当前节点的拷贝的下一个节点指向当前节点之前的下一个节点
        while (null != pointer) {
            Node newNode = new Node(pointer.val);
            Node tmp = pointer.next;
            pointer.next = newNode;
            newNode.next = tmp;
            pointer = tmp;
        }

        pointer = head;
        // 设置 random 节点
        while (null != pointer) {
            if (null != pointer.random) {
                pointer.next.random = pointer.random.next;
            }
            pointer = pointer.next.next;
        }

        Node hair = new Node(-1);
        Node tmp = hair;
        tmp.next = head;

        // 分离新链表和原链表
        while (null != tmp.next) {
            Node originNode = tmp.next;
            tmp.next = tmp.next.next;
            tmp = tmp.next;
            originNode.next = tmp.next;
        }

        return hair.next;
    }

    private static class Node {
        int val;
        Node next;
        Node random;

        Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
