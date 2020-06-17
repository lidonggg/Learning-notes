package com.lidong.algorithm.leetcode.medium.dfs;

import java.util.HashMap;
import java.util.Map;

/**
 * 从前序与中序遍历序列构造二叉树（中等-105）
 * 中文链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 官方题解：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/cong-qian-xu-he-zhong-xu-bian-li-xu-lie-gou-zao-er
 * <p>
 * 问题描述：
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>    3
 * <p>   / \
 * <p>  9  20
 * <p>    /  \
 * <p>   15   7
 *
 * @author ls J
 * @date 2020/5/12 10:40
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal105 {

    private int[] preorder;

    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了 79.76% 的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了 83.33% 的用户
     *
     * @param preorder 前序遍历数组
     * @param inorder  中序遍历数组
     * @return root
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        for (int i = 0; i < inorder.length; ++i) {
            inMap.put(inorder[i], i);
        }
        this.preorder = preorder;
        TreeNode root = new TreeNode(preorder[0]);
        recurse(root, preorder[0], 0, 0, inorder.length - 1);
        return root;
    }

    /**
     * 递归寻找左右子节点
     *
     * @param root     当前中间节点（根节点）
     * @param rootVal  当前节点值
     * @param preStart 前序遍历开始位置
     * @param inStart  中序遍历开始位置
     * @param inEnd    中序遍历结束位置
     */
    private void recurse(TreeNode root, int rootVal, int preStart, int inStart, int inEnd) {
        // 从中序遍历数组中找中间节点的位置，那么在该数组分片中，它的左边就是该节点的左子树，右边就是该节点的右子树
        int inRootIdx = findIndex(rootVal);
        // 存在左子节点
        if (inRootIdx != inStart) {
            // 找到左子节点（也就是前序遍历中当前节点的下一个值）
            int leftIdx = preStart + 1;
            int leftVal = preorder[leftIdx];
            root.left = new TreeNode(leftVal);
            // 继续去找左子树
            recurse(root.left, leftVal, leftIdx, inStart, inRootIdx - 1);
        }
        // 存在右子节点
        if (inRootIdx != inEnd) {
            // 找到右子节点
            // 在前序遍历中左子树的后面，中序遍历中，左子节点的个数是（rootIdx - inStart）
            // 对应前序遍历的 preStart + (inRootIdx - inStart) + 1 位置处
            int rightIdx = preStart + (inRootIdx - inStart) + 1;
            int rightVal = preorder[rightIdx];
            root.right = new TreeNode(rightVal);
            // 继续去找右子树
            recurse(root.right, rightVal, rightIdx, inRootIdx + 1, inEnd);
        }
    }

    /**
     * 利用 hash 优化索引查找效率
     */
    private Map<Integer, Integer> inMap = new HashMap<>();

    /**
     * 查找找值等于 key 的元素的索引
     *
     * @param key key
     * @return 对应的索引
     */
    private int findIndex(int key) {
        return inMap.get(key);
    }

    private int preIdx = 0;

    /**
     * 官方题解
     *
     * @param preorder 前序遍历数组
     * @param inorder  中序遍历数组
     * @return root
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        int idx = 0;
        for (Integer val : inorder) {
            inMap.put(val, idx++);
        }
        return helper(0, inorder.length);
    }

    private TreeNode helper(int inLeft, int inRight) {
        if (inLeft == inRight) {
            return null;
        }

        int rootVal = preorder[preIdx];
        // 找到根节点
        TreeNode root = new TreeNode(rootVal);
        // 根节点的索引，通过它进行区分左右子树
        int idx = inMap.get(rootVal);

        // 定位到下一个父节点
        preIdx++;
        // 左子树在父节点左边
        root.left = helper(inLeft, idx);
        // 右子树在父节点右边
        root.right = helper(idx + 1, inRight);
        // 返回子树的父节点
        return root;
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromPreorderAndInorderTraversal105 cb = new ConstructBinaryTreeFromPreorderAndInorderTraversal105();
        int[] pre = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};
        cb.buildTree(pre, in);
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
