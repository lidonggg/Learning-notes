package com.lidong.algorithm.leetcode.medium.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的锯齿形层次遍历（中等-103）
 * 中文链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 * <p>
 * 问题描述：
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>    3
 * <p>   / \
 * <p>  9  20
 * <p>    /  \
 * <p>   15   7
 * 返回锯齿形层次遍历如下：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 *
 * @author ls J
 * @date 2020/5/22 10:27
 */
public class BinaryTreeZigzagLevelOrderTraversal103 {

    /**
     * DFS：按层区分左右侧开始遍历
     * 使用双端队列来保存遍历过程
     *
     * 执行用时：1 ms，在所有 Java 提交中击败了 97.74% 的用户
     * 内存消耗：39.9 MB，在所有 Java 提交中击败了 7.41% 的用户
     *
     * @param root root node
     * @return list
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (null == root) {
            return res;
        }

        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.addLast(root);
        nodeQueue.addLast(null);

        LinkedList<Integer> levelList = new LinkedList<>();
        boolean isOrderLeft = true;

        while (nodeQueue.size() > 0) {
            TreeNode currNode = nodeQueue.pollFirst();
            if (currNode != null) {
                if (isOrderLeft) {
                    // 从左侧开始
                    levelList.addLast(currNode.val);
                } else {
                    // 从右侧开始
                    levelList.addFirst(currNode.val);
                }
                if (currNode.left != null) {
                    nodeQueue.addLast(currNode.left);
                }
                if (currNode.right != null) {
                    nodeQueue.addLast(currNode.right);
                }

            } else {
                res.add(levelList);
                levelList = new LinkedList<>();
                // null 值作为分界点
                if (nodeQueue.size() > 0) {
                    nodeQueue.addLast(null);
                }
                isOrderLeft = !isOrderLeft;
            }
        }
        return res;
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
