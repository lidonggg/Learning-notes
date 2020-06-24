package com.lidong.algorithm.leetcode.medium.dynamic.stock;

/**
 * 最佳买卖股票时机含冷冻期（中等-309）
 * 中文链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * <p>
 * 问题描述：
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * <p>
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * @author ls J
 * @date 2020/6/23 15:11
 */
public class BestTimeToBuyAndSellStockWithCooldown309 {

    /**
     * 方法一：动态规划，额外二维数组空间占用
     * <p>
     * 执行用时：2 ms，在所有 Java 提交中击败了 20.17% 的用户
     * 内存消耗：38.4 MB，在所有 Java 提交中击败了 11.11% 的用户
     *
     * @param prices prices
     * @return max profit
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        int[][] dp = new int[n][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(0, prices[1] - prices[0]);
        dp[1][1] = Math.max(-prices[0], -prices[1]);
        for (int i = 2; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 间隔超过一天
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    /**
     * 方法二：动态规划
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 99.22% 的用户
     * 内存消耗：37.8 MB，在所有 Java 提交中击败了 33.33% 的用户
     *
     * @param prices prices
     * @return max profit
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        // dpPre0 -> dp[i-2][0]
        int dp0 = 0, dp1 = -prices[0], dpPre0 = 0;
        for (int i = 1; i < n; ++i) {
            int tmp = dp0;
            dp0 = Math.max(dp0, dp1 + prices[i]);
            // 间隔超过一天，所以这里要从 dpPre0 - prices[i] 中取
            dp1 = Math.max(dp1, dpPre0 - prices[i]);
            dpPre0 = tmp;
        }
        return dp0;
    }
}
