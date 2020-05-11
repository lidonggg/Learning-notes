package com.lidong.algorithm.leetcode.middling.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 在每个树行中找最大值（中等-515）
 * 中文链接：https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/
 * 问题描述:
 * 您需要在二叉树的每一行中找到最大的值。
 * <p>
 * 示例：
 * 输入:
 * <p>           1
 * <p>          / \
 * <p>         3   2
 * <p>        / \   \
 * <p>       5   3   9
 * <p>
 * 输出: [1, 3, 9]
 *
 * @author ls J
 * @date 2020/5/11 9:39
 */
public class FindLargestValueInEachTreeRow515 {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> resList = new ArrayList<>();

        if (root == null) {
            return resList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < len; ++i) {
                TreeNode curNode = queue.poll();
                if (curNode.val > max) {
                    max = curNode.val;
                }
                if (null != curNode.left) {
                    queue.add(curNode.left);
                }
                if (null != curNode.right) {
                    queue.add(curNode.right);
                }
            }
            resList.add(max);
        }

        return resList;
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
