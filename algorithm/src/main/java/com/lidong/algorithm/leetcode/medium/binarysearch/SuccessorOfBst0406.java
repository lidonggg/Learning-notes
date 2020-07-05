package com.lidong.algorithm.leetcode.medium.binarysearch;

/**
 * 二叉搜索树的后继者（中等-面试题 04.06）
 * 中文链接：https://leetcode-cn.com/problems/successor-lcci
 * <p>
 * 问题描述：
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 * <p>
 * 示例 1:
 * 输入: root = [2,1,3], p = 1
 * <p>  2
 * <p> / \
 * <p>1   3
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 * <p>      5
 * <p>     / \
 * <p>    3   6
 * <p>   / \
 * <p>  2   4
 * <p> /
 * <p>1
 * 输出: null
 *
 * @author Ls J
 * @date 2020/7/5 4:32 PM
 */
public class SuccessorOfBst0406 {

    /**
     * 二分法，可以很好的用到二叉搜索树的性质
     * <p>
     * 执行用时：3 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40.8 MB，在所有 Java 提交中击败了 100.00% 的用户
     * <p>
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     *
     * @param root root
     * @param p    p
     * @return res node
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (null == root) {
            return null;
        }
        TreeNode cur = root;
        TreeNode res = null;
        while (null != cur) {
            // 如果 p 的值小于当前节点值，说明解可能在左子树，如果左子树不存在，那么当前节点就是后继节点
            if (cur.val > p.val) {
                res = cur;
                cur = cur.left;
            } else {
                // 如果 p 等于根节点，在右子树中查找最小节点。最小节点就是解，找不到返回 null。
                // 如果 p 大于根节点，那么解一定在右子树
                cur = cur.right;
            }
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
