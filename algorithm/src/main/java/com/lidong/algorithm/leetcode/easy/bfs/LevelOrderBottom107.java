package com.lidong.algorithm.leetcode.easy.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Ls J
 * @date 2020/4/5 10:30 AM
 * 二叉树的层次遍历II（简单-107）
 * 问题描述：
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其自底向上的层次遍历为：
 * <p>
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 */
public class LevelOrderBottom107 {

    /**
     * bfs
     *
     * @param root 根结点
     * @return list res
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            List<Integer> curVals = new ArrayList<>();
            int len = q.size();
            // 遍历当前层
            for (int i = 0; i < len; ++i) {
                TreeNode curNode = q.poll();
                if (curNode != null) {
                    curVals.add(curNode.val);
                    if (curNode.left != null) {
                        q.add(curNode.left);
                    }
                    if (curNode.right != null) {
                        q.add(curNode.right);
                    }
                }
            }
            // 添加到开头
            res.add(0, curVals);
        }
        return res;
    }

    private class TreeNode {
        int val;

        TreeNode left;

        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
