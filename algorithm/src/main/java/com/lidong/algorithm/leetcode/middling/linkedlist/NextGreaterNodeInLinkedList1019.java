package com.lidong.algorithm.leetcode.middling.linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <p>
 * 链表中下一个更大节点（中等-1019）
 * 中文链接：https://leetcode-cn.com/problems/next-greater-node-in-linked-list/
 * 问题描述：
 * 给出一个以头节点 head 作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ... 。
 * 每个节点都可能有下一个更大值（next larger value）：对于 node_i，如果其 next_larger(node_i) 是 node_j.val，那么就有 j > i 且  node_j.val > node_i.val，而 j 是可能的选项中最小的那个。如果不存在这样的 j，那么下一个更大值为 0 。
 * 返回整数答案数组 answer，其中 answer[i] = next_larger(node_{i+1}) 。
 * <p>
 * 注意：在下面的示例中，诸如 [2,1,5] 这样的输入（不是输出）是链表的序列化表示，其头节点的值为 2，第二个节点值为 1，第三个节点值为 5 。
 * <p>
 * 示例 1：
 * 输入：[2,1,5]
 * 输出：[5,5,0]
 * <p>
 * 示例 2：
 * 输入：[2,7,4,3,5]
 * 输出：[7,0,5,5,0]
 * <p>
 * 示例 3：
 * 输入：[1,7,5,1,9,2,5,1]
 * 输出：[7,9,9,9,0,5,0,0]
 * <p>
 * 提示：
 * 对于链表中的每个节点，1 <= node.val <= 10^9
 * 给定列表的长度在 [0, 10000] 范围内
 *
 * @author ls J
 * @date 2020/5/7 10:30
 */
public class NextGreaterNodeInLinkedList1019 {

    /**
     * 暴力求解，先转换成数组，然后针对数组进行遍历
     *
     * @param head 头结点
     * @return int[]
     */
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> resList = new ArrayList<>();

        while (head != null) {
            resList.add(head.val);
            head = head.next;
        }
        int len = resList.size();
        int[] res = new int[len];
        for (int i = 0; i < len; ++i) {
            res[i] = resList.get(i);
        }

        for (int i = 0; i < len; ++i) {
            int val = res[i];
            if (i == len - 1) {
                res[i] = 0;
                break;
            }
            for (int j = i + 1; j < len; ++j) {
                if (val < res[j]) {
                    res[i] = res[j];
                    break;
                }
                res[i] = 0;
            }
        }

        return res;
    }

    /**
     * 方法二，单调栈，更优
     * 栈中保存链表的节点编号（即下标），还需要一个 data 数组保存下标对应的链表值（避免再次遍历链表）
     * 按单调递减栈的入栈顺序，如果出现当前遍历到的节点比栈顶值大，说明需要将栈中元素出栈，并更新 res 值
     *
     * @param head 头结点
     * @return res
     */
    public int[] nextLargerNodes2(ListNode head) {
        int[] arr = new int[10000];
        int[] valueArr = new int[10000];
        Stack<Integer> stack = new Stack<>();
        int len = 0;
        int value;
        while (head != null) {
            value = head.val;
            valueArr[len] = value;
            while (!stack.isEmpty() && value > valueArr[stack.peek()]) {
                // 将栈顶元素（下标）对应的值更新
                arr[stack.pop()] = value;
            }
            stack.add(len);
            len++;
            head = head.next;
        }
        int[] result = new int[len];
        System.arraycopy(arr, 0, result, 0, len);
        return result;
    }
}
