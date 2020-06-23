package com.lidong.algorithm.leetcode.medium.dfs.stock;

/**
 * 买卖股票的最佳时机 III（中等-123）
 * 中文链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 * <p>
 * 问题描述：
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * 输入: [3,3,5,0,0,3,1,4]
 * 输出: 6
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * <p>
 * 示例 2:
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * <p>
 * 示例 3:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 *
 * @author ls J
 * @date 2020/6/23 15:38
 */
public class BestTimeToBuyAndSellStockIII123 {

    /**
     * 方法一：动态规划，数组
     * <p>
     * 执行用时：6 ms，在所有 Java 提交中击败了 59.88% 的用户
     * 内存消耗：41.2 MB，在所有 Java 提交中击败了 28.57% 的用户
     *
     * @param prices prices
     * @return max profit
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        int[][][] dp = new int[n][3][2];

        // 买卖 0 次，收益肯定都是 0
        for (int i = 0; i < n; ++i) {
            dp[i][0][0] = 0;
        }
        // 第 0 天初始化
        for (int i = 0; i < 3; ++i) {
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0];
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < 3; ++j) {
                // 第 i 天买卖了 j 次并且不持有股票 = max(前一天买卖了 j 次并且不持有股票, 前一天买卖了 j 次并且持有股票然后今天抛出)
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                // 第 i 天买卖了 j 次并且持有股票 = max(前一天买卖了 j-1 次并且不持有股票然后今天买入, 前一天买卖了 j 次，并且持有股票然后今天不抛出)
                dp[i][j][1] = Math.max(dp[i - 1][j - 1][0] - prices[i], dp[i - 1][j][1]);
            }
        }
        // 最后想要收益最大，肯定是不持有股票的状态
        return dp[n - 1][2][0];
    }

    /**
     * 方法二：动态规划，常数空间，只记录下次需要的状态
     * <p>
     * 执行用时：3 ms，在所有 Java 提交中击败了 93.26% 的用户
     * 内存消耗：39.8 MB，在所有 Java 提交中击败了 57.14% 的用户
     *
     * @param prices prices
     * @return max profit
     */
    public int maxProfit2(int[] prices) {
        int dpi10 = 0, dpi11 = Integer.MIN_VALUE;
        int dpi20 = 0, dpi21 = Integer.MIN_VALUE;
        for (int price : prices) {
            dpi20 = Math.max(dpi20, dpi21 + price);
            dpi21 = Math.max(dpi21, dpi10 - price);
            dpi10 = Math.max(dpi10, dpi11 + price);
            dpi11 = Math.max(dpi11, -price);
        }
        return dpi20;
    }
}
