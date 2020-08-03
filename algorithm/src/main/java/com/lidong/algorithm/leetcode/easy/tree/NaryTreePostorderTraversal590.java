package com.lidong.algorithm.leetcode.easy.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * N 叉树的后序遍历（简单-590）
 * 中文链接：https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal
 * <p>
 * 问题描述：
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 * <p>
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 *
 * @author Ls J
 * @date 2020/8/2 11:22 PM
 */
public class NaryTreePostorderTraversal590 {

    /**
     * 迭代法
     *
     * 执行用时：4 ms，在所有 Java 提交中击败了 40.79% 的用户
     * 内存消耗：40.5 MB，在所有 Java 提交中击败了 82.30% 的用户
     *
     * @param root root
     * @return list
     */
    public List<Integer> postorder(Node root) {
        LinkedList<Integer> res = new LinkedList<>();

        if (null == root) {
            return res;
        }

        LinkedList<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            // 依次当前节点加入到结果列表头
            res.addFirst(node.val);
            for (Node child : node.children) {
                if (null != child) {
                    stack.add(child);
                }
            }
        }

        return res;
    }

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    ;
}
