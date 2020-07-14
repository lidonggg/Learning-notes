package com.lidong.algorithm.leetcode.medium.dfs;

import java.util.Arrays;

/**
 * 根据前序和后序遍历构造二叉树（中等-889）
 * 中文链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal
 * <p>
 * 问题描述：
 * 返回与给定的前序和后序遍历匹配的任何二叉树。
 * pre 和 post 遍历中的值是不同的正整数。
 * <p>
 * 示例：
 * 输入：pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
 * 输出：[1,2,3,4,5,6,7]
 *  
 * 提示：
 * 1 <= pre.length == post.length <= 30
 * pre[] 和 post[] 都是 1, 2, ..., pre.length 的排列
 * 每个输入保证至少有一个答案。如果有多个答案，可以返回其中一个。
 *
 * @author ls J
 * @date 2020/7/14 19:10
 */
public class ConstructBinaryTreeFromPreorderAndPostorderTraversal889 {

    /**
     * 执行用时：1 ms，在所有 Java 提交中击败了 42.08% 的用户
     * 内存消耗：39.1 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param pre  pre
     * @param post post
     * @return root
     */
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        int n = pre.length;
        if (n == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        if (n == 1) {
            return root;
        }
        // 寻找左分支
        int l = 0;
        for (int i = 0; i < n; ++i) {
            if (post[i] == pre[1]) {
                l = i + 1;
            }
        }
        // 左分支 pre[1~l+1]，post[0, l]
        root.left = constructFromPrePost(Arrays.copyOfRange(pre, 1, l + 1),
                Arrays.copyOfRange(post, 0, l));
        // 右分支 pre[l+1,n]，post[l, n-1]
        root.right = constructFromPrePost(Arrays.copyOfRange(pre, l + 1, n),
                Arrays.copyOfRange(post, l, n - 1));
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
