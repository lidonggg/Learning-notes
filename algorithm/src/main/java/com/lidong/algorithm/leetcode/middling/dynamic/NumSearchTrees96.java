package com.lidong.algorithm.leetcode.middling.dynamic;

/**
 * 不同的二叉搜索树（中等-96）
 * 问题描述：
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 示例:
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * <p>
 * 1         3     3      2      1
 * \       /     /      /  \      \
 * 3     2      1      1   3      2
 * /     /       \                 \
 * 2     1        2                 3
 *
 * @author ls J
 * @date 2019/8/29 1:39 PM
 */
public class NumSearchTrees96 {

    /**
     * 对于每一个根 i 它都是由左边子树（1, 2, ..., i - 1)和右边子树（i + 1, i + 2, ..., n)组成的。
     * 因此它的个数肯定是两个子树情况的积。而且，这种根一共有 n 个，再将这些加起来就可以了。
     *
     * @param n n
     * @return 能构造成的二叉搜索树的个数
     */
    public static int numSearchTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numSearchTrees(3));
    }
}
