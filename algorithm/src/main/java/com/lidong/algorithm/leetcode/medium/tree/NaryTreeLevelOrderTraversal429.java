package com.lidong.algorithm.leetcode.medium.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * N 叉树层序遍历（中等-429）
 * 中文链接：https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 * <p>
 * 问题描述：
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 * <p>
 * 例如，给定一个 3叉树 :
 * 返回其层序遍历:
 * [
 * [1],
 * [3,2,4],
 * [5,6]
 * ]
 *  
 * 说明:
 * 1.树的深度不会超过 1000。
 * 2.树的节点总数不会超过 5000。
 *
 * @author ls J
 * @date 2020/6/30 18:42
 */
public class NaryTreeLevelOrderTraversal429 {

    /**
     * 方法一：bfs
     * <p>
     * 执行用时：4 ms，在所有 Java 提交中击败了 56.84% 的用户
     * 内存消耗：40.5 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param root root
     * @return list
     */
    public List<List<Integer>> levelOrder(Node root) {
        if (null == root) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> itemList = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                Node cur = queue.poll();
                itemList.add(cur.val);
                List<Node> children = cur.children;
                if (null != children) {
                    // addAll
                    queue.addAll(children);
                }
            }
            res.add(itemList);
        }

        return res;
    }


    /**
     * 以下为方法二
     */
    private List<List<Integer>> result = new LinkedList<>();

    /**
     * 方法二：dfs
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 99.88% 的用户
     * 内存消耗：40.8 MB，在所有 Java 提交中击败了 75.00% 的用户
     *
     * @param root root
     * @return list
     */
    public List<List<Integer>> levelOrder2(Node root) {
        if (null == root) {
            return result;
        }
        dfs(root, 0);
        return result;
    }

    /**
     * dfs
     * dfs 过程中记录层高
     *
     * @param node  cur node
     * @param level cur level
     */
    private void dfs(Node node, int level) {
        if (result.size() <= level) {
            result.add(new LinkedList<>());
        }
        result.get(level).add(node.val);
        for (Node child : node.children) {
            dfs(child, level + 1);
        }
    }

    private static class Node {
        int val;
        List<Node> children;

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
