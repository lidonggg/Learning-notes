package com.lidong.algorithm.leetcode.medium.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的中序遍历（中等-94）
 * 中文链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * <p>
 * 问题描述：
 * 给定一颗二叉树，输出它的中序遍历序列。
 * 进阶：能不能用迭代的方式而不是递归
 *
 * @author Ls J
 * @date 2020/6/12 9:42 PM
 */
public class BinaryTreeInorderTraversal94 {

    /**
     * 中序遍历，迭代
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 49.81% 的用户
     * 内存消耗：37.9 MB，在所有 Java 提交中击败了 5.79% 的用户
     *
     * @param root root
     * @return list
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (null == root) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        while (null != curNode || !stack.isEmpty()) {
            // 如果有左节点，则一直找到最左子节点
            while (null != curNode) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            // 栈中最上面保存的是最左子节点，出栈
            curNode = stack.pop();
            res.add(curNode.val);
            // 同样的方法处理它的右子树
            curNode = curNode.right;
        }
        return res;
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
