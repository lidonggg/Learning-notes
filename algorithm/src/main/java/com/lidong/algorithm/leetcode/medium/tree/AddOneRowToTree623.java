package com.lidong.algorithm.leetcode.medium.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 在二叉树中增加一行（中等-623）
 * 中文链接：https://leetcode-cn.com/problems/add-one-row-to-tree/
 *
 * 问题描述：
 * 给定一个二叉树，根节点为第1层，深度为 1。在其第 d 层追加一行值为 v 的节点。
 * 添加规则：给定一个深度值 d （正整数），针对深度为 d-1 层的每一非空节点 N，为 N 创建两个值为 v 的左子树和右子树。
 * 将 N 原先的左子树，连接为新节点 v 的左子树；将 N 原先的右子树，连接为新节点 v 的右子树。
 * 如果 d 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 v，原先的整棵树将作为 v 的左子树。
 * <p>
 * 示例 1:
 * 输入:
 * 二叉树如下所示:
 * <p>       4
 * <p>     /   \
 * <p>    2     6
 * <p>   / \   /
 * <p>  3   1 5
 * v = 1
 * d = 2
 * 输出:
 * <p>       4
 * <p>      / \
 * <p>     1   1
 * <p>    /     \
 * <p>   2       6
 * <p>  / \     /
 * <p> 3   1   5
 * <p>
 * 示例 2:
 * 输入:
 * 二叉树如下所示:
 * <p>      4
 * <p>     /
 * <p>    2
 * <p>   / \
 * <p>  3   1
 * v = 1
 * d = 3
 * 输出:
 * <p>      4
 * <p>     /
 * <p>    2
 * <p>   / \
 * <p>  1   1
 * <p> /     \
 * <p>3       1
 * 注意:
 * <p>
 * 输入的深度值 d 的范围是：[1，二叉树最大深度 + 1]。
 * 输入的二叉树至少有一个节点。
 *
 * @author Ls J
 * @date 2020/6/27 11:40 AM
 */
public class AddOneRowToTree623 {

    /**
     * 方法一：dfs
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.5 MB，在所有 Java 提交中击败了 33.33% 的用户
     *
     * @param root root
     * @param v    v
     * @param d    depth
     * @return new root
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode t = new TreeNode(v);
            t.left = root;
            return t;
        }
        insert(root, v, d, 1);
        return root;
    }

    private void insert(TreeNode node, int v, int d, int depth) {
        if (null == node) {
            return;
        }
        if (depth == d - 1) {
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = new TreeNode(v);
            node.left.left = left;
            node.right = new TreeNode(v);
            node.right.right = right;
        } else {
            insert(node.left, v, d, depth + 1);
            insert(node.right, v, d, depth + 1);
        }
    }

    /**
     * 方法二：bfs
     *
     * @param root root
     * @param v    v
     * @param d    depth
     * @return new root
     */
    public TreeNode addOneRow2(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode n = new TreeNode(v);
            n.left = root;
            return n;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1;
        while (depth < d - 1) {
            Queue<TreeNode> temp = new LinkedList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.remove();
                if (node.left != null) {
                    temp.add(node.left);
                }
                if (node.right != null) {
                    temp.add(node.right);
                }
            }
            queue = temp;
            depth++;
        }
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            TreeNode temp = node.left;
            node.left = new TreeNode(v);
            node.left.left = temp;
            temp = node.right;
            node.right = new TreeNode(v);
            node.right.right = temp;
        }
        return root;
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
