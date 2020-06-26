package com.lidong.algorithm.leetcode.medium.tree;

/**
 * 翻转等价二叉树（中等-951）
 * 中文链接：https://leetcode-cn.com/problems/flip-equivalent-binary-trees/
 * <p>
 * 问题描述：
 * 我们可以为二叉树 T 定义一个翻转操作，如下所示：选择任意节点，然后交换它的左子树和右子树。
 * 只要经过一定次数的翻转操作后，能使 X 等于 Y，我们就称二叉树 X 翻转等价于二叉树 Y。
 * 编写一个判断两个二叉树是否是翻转等价的函数。这些树由根节点 root1 和 root2 给出。
 * <p>
 * 示例：
 * 输入：root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
 * 输出：true
 * 解释：我们翻转值为 1，3 以及 5 的三个节点。
 * <p>
 * 提示：
 * 1.每棵树最多有 100 个节点。
 * 2.每棵树中的每个值都是唯一的、在 [0, 99] 范围内的整数。
 *
 * @author Ls J
 * @date 2020/6/26 3:36 PM
 */
public class FlipEquivalentBinaryTrees951 {

    /**
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：37.7 MB，在所有 Java 提交中击败了 33.33% 的用户
     *
     * @param root1 root1
     * @param root2 root2
     * @return true / false
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (null == root1 && null == root2) {
            return true;
        }
        if (null == root1 || null == root2 || root1.val != root2.val) {
            return false;
        }
        // 拿 root1 的左子树跟 root2 的左右子树进行比较
        boolean s1 = flipEquiv(root1.left, root2.right) || flipEquiv(root1.left, root2.left);
        // 拿 root1 的右子树跟 root2 的左右子树进行比较
        boolean s2 = flipEquiv(root1.right, root2.left) || flipEquiv(root1.right, root2.right);
        // 返回的结果应该是左右子树都能够比较成功
        return s1 && s2;
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
