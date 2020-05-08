package com.lidong.algorithm.leetcode.easy.dynamic;

/**
 * 爬楼梯（简单-70）
 * 问题描述：
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <br>
 * 注意：给定 n 是一个正整数。
 * <br>
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * <br>
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * @author ls J
 * @date 2019/7/25 2:02 PM
 */
public class ClimbStairs70 {

    /**
     * 动态规划
     *
     * @param n 台阶数
     * @return dp[n]
     */
    public int climbStairs(int n) {

        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int climbStairs1(int n) {
        int[] memo = new int[n + 1];
        return doRecursive(0, n, memo);
    }

    /**
     * 带记忆的暴力求解
     *
     * @param i
     * @param n    台阶数
     * @param memo
     * @return
     */
    private int doRecursive(int i, int n, int[] memo) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = doRecursive(i + 1, n, memo) + doRecursive(i + 2, n, memo);
        return memo[i];
    }

}
