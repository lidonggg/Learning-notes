package com.lidong.algorithm.leetcode.medium.dfs;

/**
 * 扁平化多级双向链表（中等-430）
 * 中文链接：https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list
 * <p>
 * 问题描述：
 * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。
 * 这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 * 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * 输出：[1,2,3,7,8,11,12,9,10,4,5,6]
 * <p>
 * 提示：
 * - 节点数目不超过 1000
 * - 1 <= Node.val <= 10^5
 *
 * @author ls J
 * @date 2020/7/9 14:33
 */
public class FlattenMultilevelDoublyLinkedList430 {

    /**
     * dfs -- 方法一
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 27.42% 的用户
     * 内存消耗：37.5 MB，在所有 Java 提交中击败了 50.00% 的用
     *
     * @param head head
     * @return new head
     */
    public Node flatten(Node head) {
        if (null == head) {
            return null;
        }

        Node tmp = head;
        // 记录本层中的下一个节点
        Node next = tmp.next;
        while (null != tmp) {
            // 如果当前节点有 child，那么它的下一个节点应该变成该 child 节点
            if (null != tmp.child) {
                // dfs child 节点，返回该 child 的头结点
                Node nc = flatten(tmp.child);
                // 新的 next 指向 child 节点
                tmp.next = nc;
                // child 指针置位 null
                tmp.child = null;
                // child 节点的前置节点变成 tmp
                nc.prev = tmp;
                // 寻找 nc 中的最后一个节点
                // 这里需要从前往后进行一次遍历，是时间复杂度区别于方法二的主要原因
                Node nce = nc;
                while (null != nce.next) {
                    nce = nce.next;
                }
                // nc 中最后一个节点指向本来的 next 节点
                nce.next = next;
                if (null != next) {
                    next.prev = nce;
                }
            }
            // 继续去看之前的下一个节点
            tmp = next;
            if (null != next) {
                next = tmp.next;
            }
        }
        return head;
    }

    /**
     * dfs -- 方法二
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：37.5 MB，在所有 Java 提交中击败了 50.00% 的用
     *
     * @param head head
     * @return new head
     */
    public Node flatten2(Node head) {
        if (null == head) {
            return null;
        }
        // 哨兵节点，处理边界
        Node pseudoHead = new Node(-1, null, head, null);
        flattenDFS(pseudoHead, head);
        pseudoHead.next.prev = null;
        return pseudoHead.next;
    }

    private Node flattenDFS(Node prev, Node curr) {
        if (null == curr) {
            return prev;
        }
        curr.prev = prev;
        prev.next = curr;
        // 记录本来的 next 节点
        Node tempNext = curr.next;
        // 对子链表进行操作
        // 当前节点的子链表的 prev 应该指向当前节点
        Node tail = flattenDFS(curr, curr.child);
        curr.child = null;
        // 对本层原始的下一个节点进行操作，并将它与前面的 tail 节点连到一起
        return flattenDFS(tail, tempNext);
    }

    private static class Node {
        int val;
        Node prev;
        Node next;
        Node child;

        Node(int val, Node prev, Node next, Node child) {
            this.val = val;
            this.prev = prev;
            this.next = next;
            this.child = child;
        }
    }
}
