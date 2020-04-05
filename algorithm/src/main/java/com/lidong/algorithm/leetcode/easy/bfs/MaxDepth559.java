package com.lidong.algorithm.leetcode.easy.bfs;

import java.util.*;

/**
 * @author Ls J
 * @date 2020/4/5 11:53 PM
 * N叉树的最大深度（简单-559）
 * 问题描述：
 * 给定一个 N 叉树，找到其最大深度。
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * <p>
 * 说明:
 * 树的深度不会超过 1000。
 * 树的节点总不会超过 5000。
 */
public class MaxDepth559 {

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int depth = 1;

        Queue<List<Node>> q = new LinkedList<>();
        if (root.children == null) {
            return 1;
        }
        q.add(root.children);
        while (!q.isEmpty()) {
            int len = q.size();
            boolean hasNext = false;
            // 遍历每一层，如果有子节点，那么 depth+1
            for (int i = 0; i < len; ++i) {
                List<Node> curList = q.poll();
                for (Node node : curList) {
                    if (node.children != null) {
                        q.add(node.children);
                        if (!hasNext) {
                            hasNext = true;
                        }
                    }
                }
            }
            if (hasNext) {
                depth++;
            }
        }
        return depth;
    }

    public int maxDepth1(Node root) {
        return dfs(root,0);
    }

    /**
     * dfs
     *
     * @param node node
     */
    private int dfs(Node node, int curDepth) {
        if (node == null) {
            return curDepth;
        }
        curDepth += 1;
        List<Node> children = node.children;
        if (children == null || children.isEmpty()) {
            return curDepth;
        }
        List<Integer> heights = new ArrayList<>();
        for (Node child : children) {
            heights.add(dfs(child,curDepth));
        }
        return Collections.max(heights);
    }

    class Node {

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
}
