package com.lidong.algorithm.leetcode.medium.tree;

/**
 * 具有所有最深结点的最小子树（中等-865）
 * 中文链接：https://leetcode-cn.com/problems/smallest-subtree-with-all-the-deepest-nodes/
 * <p>
 * 问题描述：
 * 给定一个根为 root 的二叉树，每个结点的深度是它到根的最短距离。
 * 如果一个结点在整个树的任意结点之间具有最大的深度，则该结点是最深的。
 * 一个结点的子树是该结点加上它的所有后代的集合。
 * 返回能满足“以该结点为根的子树中包含所有最深的结点”这一条件的具有最大深度的结点。
 * <p>
 * 示例：
 * 输入：[3,5,1,6,2,0,8,null,null,7,4]
 * 输出：[2,7,4]
 * 解释：
 * 我们返回值为 2 的结点，在图中用黄色标记。
 * 在图中用蓝色标记的是树的最深的结点。
 * 输入 "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" 是对给定的树的序列化表述。
 * 输出 "[2, 7, 4]" 是对根结点的值为 2 的子树的序列化表述。
 * 输入和输出都具有 TreeNode 类型。
 *  
 * 提示：
 * - 树中结点的数量介于 1 和 500 之间。
 * - 每个结点的值都是独一无二的。
 *
 * @author Ls J
 * @date 2020/7/5 2:02 PM
 */
public class SmallestSubtreeWithAllTheDeepestNodes865 {

    /**
     * 方法一：判断左右子树是否包含最深的节点
     *
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：37.4 MB，在所有 Java 提交中击败了 50.00% 的用户
     *
     * @param root root
     * @return min root
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        int maxDepth = maxDepth(root);
        return minRoot(root, 0, maxDepth);
    }

    /**
     * 计算最大深度
     *
     * @param root root
     * @return max depth
     */
    private int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 寻找最小子树根结点
     *
     * @param node     cur node
     * @param curDepth cur depth
     * @param maxDepth max depth
     * @return root node
     */
    private TreeNode minRoot(TreeNode node, int curDepth, int maxDepth) {
        if (null == node) {
            return null;
        }
        // 分别判断左右子树是否包含最深叶子节点
        boolean ld = isCurNodeIncludeDeepest(node.left, curDepth + 1, maxDepth);
        boolean rd = isCurNodeIncludeDeepest(node.right, curDepth + 1, maxDepth);
        // 如果都包含，那么当前节点就是所求的节点
        if (ld && rd) {
            return node;
        }
        // 如果只有左子树包含，那么再递归去找左子树
        if (ld) {
            return minRoot(node.left, curDepth + 1, maxDepth);
        }
        return minRoot(node.right, curDepth + 1, maxDepth);
    }

    /**
     * 判断当前子树是否包含最深节点
     *
     * @param node     cur root
     * @param curDepth cur depth
     * @param maxDepth max depth
     * @return 如果包含，返回 true
     */
    private boolean isCurNodeIncludeDeepest(TreeNode node, int curDepth, int maxDepth) {
        if (null == node) {
            return curDepth == maxDepth;
        }
        return isCurNodeIncludeDeepest(node.left, curDepth + 1, maxDepth) || isCurNodeIncludeDeepest(node.right, curDepth + 1, maxDepth);
    }
    
    /**
     * 方法二：直接比较左右子树的深度
     *
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：37.4 MB，在所有 Java 提交中击败了 50.00% 的用户
     *
     * @param root root
     * @return lowest root
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 计算左右深度
        int ld = depth(root.left);
        int rd = depth(root.right);
        if (ld == rd) {
            return root;
        } else if (ld > rd) {
            return subtreeWithAllDeepest(root.left);
        } else {
            return subtreeWithAllDeepest(root.right);
        }
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = depth(node.right);
        int right = depth(node.left);
        return Math.max(left, right) + 1;
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
