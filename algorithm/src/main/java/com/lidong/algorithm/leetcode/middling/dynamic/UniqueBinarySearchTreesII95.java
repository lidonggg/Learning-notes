package com.lidong.algorithm.leetcode.middling.dynamic;

import java.util.List;

/**
 * 不同的二叉搜索树 II（中等-95）
 * 中文链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
 * 问题描述：
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 * <p>
 * 示例:
 * 输入: 3
 * 输出:
 * [
 * <p>  [1,null,3,2],
 * <p>  [3,2,null,1],
 * <p>  [3,1,null,null,2],
 * <p>  [2,1,3],
 * <p>  [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 * <p>  1         3     3      2      1
 * <p>    \       /     /      / \      \
 * <p>     3     2     1      1   3      2
 * <p>    /     /       \                 \
 * <p>   2     1         2                 3
 *
 * @author Ls J
 * @date 2020/5/12 12:07 AM
 */
public class UniqueBinarySearchTreesII95 {

    public List<TreeNode> generateTrees(int n) {
        
        return null;
    }

    private class TreeNode {
        int val;

        TreeNode left;

        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
