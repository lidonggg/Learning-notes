package com.lidong.algorithm.leetcode.medium.dfs;

import java.util.Stack;

/**
 * 先序遍历构造二叉搜索树（中等-1008）
 * 中文链接：
 * <p>
 * 问题描述：
 *
 * @author ls J
 * @date 2020/6/30 14:53
 */
public class ConstructBinarySearchTreeFromPreorderTraversal1008 {

    private int[] preorder;

    private int[] firstGreaters;

    /**
     * 方法一：单调栈 + dfs
     * <p>
     * 执行用时：2 ms，在所有 Java 提交中击败了 17.51% 的用户
     * 内存消耗：38.1 MB，在所有 Java 提交中击败了 11.11% 的用户
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param preorder preorder
     * @return root
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        int n = preorder.length;
        this.preorder = preorder;
        int[] firstGreaters = new int[n];
        Stack<Integer> stack = new Stack<>();
        // 利用单调栈寻找每个节点的右子节点（也就是第一个值比当前元素大的那个）
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && preorder[stack.peek()] < preorder[i]) {
                firstGreaters[stack.pop()] = i;
            }
            stack.push(i);
        }
        // 剩余的都是没有右子树的
        while (!stack.isEmpty()) {
            firstGreaters[stack.pop()] = n;
        }
        this.firstGreaters = firstGreaters;
        return dfs(0, n - 1);
    }

    private TreeNode dfs(int left, int right) {
        if (left > right) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[left]);
        // 寻找根节点的右子节点（右子树的第一个节点）
        int firstRight = firstGreaters[left];
        // 左子树范围 [left + 1, firstRight - 1]
        root.left = dfs(left + 1, firstRight - 1);
        // 右子树范围 [firstRight, right]
        root.right = dfs(firstRight, right);
        return root;
    }


    /**
     * 以下为方法二
     */
    private int idx = 0;

    private int n;

    /**
     * 方法二：根据节点值可以存在的区间来 dfs
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：38 MB，在所有 Java 提交中击败了 11.11% 的用户
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param preorder preorder
     * @return root
     */
    public TreeNode bstFromPreorder2(int[] preorder) {
        this.preorder = preorder;
        n = preorder.length;
        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * dfs
     *
     * @param lower 区间最小值
     * @param upper 区间最大值
     * @return root node
     */
    private TreeNode helper(int lower, int upper) {
        if (idx == n) {
            return null;
        }

        int val = preorder[idx];
        if (val < lower || val > upper) {
            return null;
        }

        idx++;
        TreeNode root = new TreeNode(val);
        // 左区间值的范围为 (lower, val)
        root.left = helper(lower, val);
        // 右区间值的范围为 (val, upper)
        root.right = helper(val, upper);
        return root;
    }

    /**
     * 方法三：将 preorder 进行排序可以构造出二叉搜索树的中序遍历，然后就可以根据前序与中序遍历序列来恢复二叉树了
     * 这种方法效率比较低
     */

    private class TreeNode {
        int val;

        TreeNode left;

        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
