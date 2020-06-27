package com.lidong.algorithm.leetcode.medium.hash;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 出现次数最多的子树元素和（中等-508）
 * 中文链接：https://leetcode-cn.com/problems/most-frequent-subtree-sum/
 * <p>
 * 问题描述：
 * 给你一个二叉树的根结点，请你找出出现次数最多的子树元素和。一个结点的「子树元素和」定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
 * 你需要返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
 * <p>
 * 示例 1：
 * 输入:
 * <p>  5
 * <p> /  \
 * <p>2   -3
 * 返回 [2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。
 * <p>
 * 示例 2：
 * 输入：
 * <p>  5
 * <p> /  \
 * <p>2   -5
 * 返回 [2]，只有 2 出现两次，-5 只出现 1 次。
 * <p>
 * 提示： 假设任意子树元素和均可以用 32 位有符号整数表示。
 *
 * @author Ls J
 * @date 2020/6/27 2:07 PM
 */
public class MostFrequentSubtreeSum508 {

    /**
     * 记录每个和出现的次数
     */
    private Map<Integer, Integer> map = new HashMap<>();

    /**
     * 记录最大出现次数
     */
    private int max = 0;

    /**
     * map + list + dfs
     * <p>
     * 执行用时：8 ms，在所有 Java 提交中击败了 41.70% 的用户
     * 内存消耗：40 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param root root
     * @return int[]
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        if (null == root) {
            return new int[0];
        }
        dfs(root);
        List<Integer> resList = new LinkedList<>();

        for (int key : map.keySet()) {
            if (map.get(key) == max) {
                resList.add(key);
            }
        }
        int[] res = new int[resList.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = resList.get(i);
        }
        return res;
    }

    /**
     * 递归计算每个子树和
     *
     * @param root 当前根节点
     * @return sum
     */
    private int dfs(TreeNode root) {
        if (null == root) {
            return 0;
        }

        int sum = root.val + dfs(root.left) + dfs(root.right);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        max = Math.max(max, map.get(sum));
        return sum;
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
