package com.lidong.algorithm.leetcode.medium.dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 填充每个节点的下一个右侧节点指针（中等-116）
 * 中文链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 * <p>
 * 问题描述：
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * @author ls J
 * @date 2020/6/22 15:39
 */
public class PopulatingNextRightPointersInEachNode116 {

    private static class Node {

        public int val;

        public Node left;

        public Node right;

        public Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right, Node next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }

    /**
     * 方法一：dfs
     * <p>
     * 执行用时：3 ms，在所有 Java 提交中击败了 45.22% 的用户
     * 内存消耗：40 MB，在所有 Java 提交中击败了 6.67% 的用户
     *
     * @param root 根节点
     * @return 处理后的树的根节点
     */
    public Node connect(Node root) {
        if (null == root) {
            return null;
        }
        dfs(root.left, root.right);
        return root;
    }

    /**
     * dfs
     *
     * @param left  相邻两个节点中的左侧节点
     * @param right 相邻两个节点中的右侧节点
     */
    private void dfs(Node left, Node right) {
        if (null != left) {
            // left 的 next 指针指向 right
            left.next = right;
        }
        if (null != left && null != right) {
            // 递归遍历左子树
            dfs(left.left, left.right);
            // 递归遍历右子树
            dfs(right.left, right.right);
            // 递归左子树右侧节点与右子树左侧节点
            dfs(left.right, right.left);
        }
    }

    /**
     * 方法二：bfs
     * <p>
     * 很容易想到按层处理每个节点
     * <p>
     * 执行用时：3 ms，在所有 Java 提交中击败了 45.22% 的用户
     * 内存消耗：40 MB，在所有 Java 提交中击败了 6.67% 的用户
     *
     * @param root 根节点
     * @return 处理后的树的根节点
     */
    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int size = queue.size();
            // 处理每一层
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (i < size - 1) {
                    node.next = queue.peek();
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return root;
    }

}
