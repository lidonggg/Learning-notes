package com.lidong.algorithm.leetcode.medium.dfs;

import java.util.HashMap;
import java.util.Map;

/**
 * 从中序与后续遍历序列构造二叉树（中等-106）
 * 中文链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * <p>
 * 问题描述：
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 * <p>    3
 * <p>   / \
 * <p>  9  20
 * <p>    /  \
 * <p>   15   7
 *
 * @author ls J
 * @date 2020/6/29 14:42
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal106 {

    private Map<Integer, Integer> inMap = new HashMap<>();

    private int[] postorder;

    private int postPr;

    /**
     * 执行用时：2 ms，在所有 Java 提交中击败了 97.76% 的用户
     * 内存消耗：39.8 MB，在所有 Java 提交中击败了 76.19% 的用户
     *
     * @param inorder   inorder
     * @param postorder postorder
     * @return root
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        if (n == 0) {
            return null;
        }
        for (int i = 0; i < n; ++i) {
            inMap.put(inorder[i], i);
        }
        this.postorder = postorder;
        this.postPr = n - 1;

        return dfs(0, n - 1);
    }

    /**
     * dfs 构造子树
     *
     * @param inLeft  inorder 中的左端点
     * @param inRight inorder 中的右端点
     * @return 子树根节点
     */
    private TreeNode dfs(int inLeft, int inRight) {
        if (inLeft > inRight) {
            return null;
        }
        int rootVal = postorder[postPr];
        TreeNode root = new TreeNode(rootVal);
        int idx = inMap.get(rootVal);
        // 定位到下一个子树根节点的位置
        postPr--;
        // 构造右子树
        root.right = dfs(idx + 1, inRight);
        // 构造左子树
        root.left = dfs(inLeft, idx - 1);
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
