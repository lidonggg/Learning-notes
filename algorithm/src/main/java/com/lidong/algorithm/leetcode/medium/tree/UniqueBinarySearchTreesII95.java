package com.lidong.algorithm.leetcode.medium.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 不同的二叉搜索树 II（中等-95）
 * 中文链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 * <p>
 * 问题描述：
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 * <p>
 * 示例：
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>    1         3     3      2      1
 * <p>     \       /     /      / \      \
 * <p>      3     2     1      1   3      2
 * <p>     /     /       \                 \
 * <p>   2     1         2                 3
 *  
 * <p>
 * 提示：
 * 0 <= n <= 8
 *
 * @author Ls J
 * @date 2020/7/5 2:32 AM
 */
public class UniqueBinarySearchTreesII95 {

    /**
     * 执行用时：1 ms，在所有 Java 提交中击败了 99.94% 的用户
     * 内存消耗：40.3 MB，在所有 Java 提交中击败了 14.29% 的用户
     *
     * @param n n
     * @return list
     */
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        return dfs(1, n);
    }

    /**
     * dfs
     *
     * @param l 左边界
     * @param r 右边界
     * @return list
     */
    private List<TreeNode> dfs(int l, int r) {
        List<TreeNode> res = new ArrayList<>();
        if (l > r) {
            res.add(null);
            return res;
        }
        // 如果 l = r，代表找到了叶子节点，此时只有一种情况
        if (l == r) {
            res.add(new TreeNode(l));
            return res;
        }

        for (int i = l; i <= r; ++i) {
            // 分别找到左右子树的所有可能情况
            List<TreeNode> lefts = dfs(l, i - 1);
            List<TreeNode> rights = dfs(i + 1, r);
            // 构造左右子树
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
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
