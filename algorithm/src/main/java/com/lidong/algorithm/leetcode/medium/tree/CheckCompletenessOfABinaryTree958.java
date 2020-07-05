package com.lidong.algorithm.leetcode.medium.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的完全性检验（中等-958）
 * 中文链接：https://leetcode-cn.com/problems/check-completeness-of-a-binary-tree
 * <p>
 * 问题描述：
 * 给定一个二叉树，确定它是否是一个完全二叉树。
 * 百度百科中对完全二叉树的定义如下：
 * 若设二叉树的深度为 h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。（注：第 h 层可能包含 1~ 2h 个节点。） 
 * <p>
 * 示例 1：
 * 输入：[1,2,3,4,5,6]
 * 输出：true
 * 解释：最后一层前的每一层都是满的（即，结点值为 {1} 和 {2,3} 的两层），且最后一层中的所有结点（{4,5,6}）都尽可能地向左。
 * <p>
 * 示例 2：
 * 输入：[1,2,3,4,5,null,7]
 * 输出：false
 * 解释：值为 7 的结点没有尽可能靠向左侧。
 * <p>
 * 提示：
 * 树中将会有 1 到 100 个结点。
 *
 * @author ls J
 * @date 2020/6/30 13:58
 */
public class CheckCompletenessOfABinaryTree958 {

    /**
     * bfs
     * 保存当前遍历到的上一个元素，如果上一个元素是 null 但是当前元素不是，则说明它不是一个完全二叉树
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 96.58% 的用户
     * 内存消耗：39.3 MB，在所有 Java 提交中击败了 25.00% 的用户
     *
     * @param root root
     * @return true if is complete
     */
    public boolean isCompleteTree(TreeNode root) {
        if (null == root) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        // 保存遍历到的上一个节点
        TreeNode last = root;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (null == last && null != cur) {
                return false;
            }
            last = cur;
            if (null != cur) {
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        return true;
    }

    private static class TreeNode {
        int val;

        TreeNode left;

        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
