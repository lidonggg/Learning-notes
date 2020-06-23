package com.lidong.algorithm.leetcode.easy.dynamic.stock;

/**
 * 买卖股票的最佳时机（简单-121）
 * 中文链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * <p>
 * 问题描述：
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。
 * <p>
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * <p>
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * @author dlif
 * @date 2020/6/23 14:31
 */
public class BestTimeToBuyAndSellStock121 {

    /**
     * 方法一：两层循环，找差的最大值
     * <p>
     * 执行用时：290 ms，在所有 Java 提交中击败了 15.31% 的用户
     * 内存消耗：39.9 MB，在所有 Java 提交中击败了 5.32% 的用户
     *
     * @param prices prices
     * @return max profit
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }

        int res = 0;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (prices[i] <= prices[j]) {
                    continue;
                }
                res = Math.max(res, prices[i] - prices[j]);
            }
        }
        return res;
    }

    /**
     * 方法二：动态规划
     * <p>
     * 执行用时：4 ms，在所有 Java 提交中击败了 29.25% 的用户
     * 内存消耗：39.9 MB，在所有 Java 提交中击败了 5.32% 的用户
     *
     * @param prices prices
     * @return max profit
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }

        int[][] dp = new int[n + 1][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[n - 1][0];
    }

    /**
     * 方法二：动态规划，常数额外空间占用
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100% 的用户
     * 内存消耗：39.9 MB，在所有 Java 提交中击败了 5.32% 的用户
     *
     * @param prices prices
     * @return max profit
     */
    public int maxProfit3(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }

        int dp0 = 0, dp1 = Integer.MIN_VALUE;
        for (int i = 1; i < n; ++i) {
            dp0 = Math.max(dp0, dp1 + prices[i]);
            dp1 = Math.max(dp1, -prices[i]);
        }
        return dp0;
    }
}
