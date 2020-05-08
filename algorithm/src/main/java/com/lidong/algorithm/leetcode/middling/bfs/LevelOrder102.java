package com.lidong.algorithm.leetcode.middling.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层次遍历（中等-102）
 * 问题描述：
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * @author ls J
 * @date 2020/4/9 14:17
 */
public class LevelOrder102 {

    /**
     * 利用 bfs 进行广度优先遍历
     *
     * @param root 根节点
     * @return 结果 list
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<>();
        if (null == root) {
            return resList;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int len = q.size();
            List<Integer> curLevel = new ArrayList<>();
            // 依次遍历当前层的每一个节点
            for (int i = 0; i < len; ++i) {
                TreeNode node = q.poll();
                assert node != null;
                curLevel.add(node.val);
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            resList.add(curLevel);
        }

        return resList;
    }

    class TreeNode {
        int val;

        TreeNode left;

        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
