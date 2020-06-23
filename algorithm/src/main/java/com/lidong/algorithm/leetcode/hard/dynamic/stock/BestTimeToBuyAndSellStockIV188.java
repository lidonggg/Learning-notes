package com.lidong.algorithm.leetcode.hard.dynamic.stock;

/**
 * 买卖股票的最佳时机 IV（困难-188）
 * 中文链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
 * <p>
 * 问题描述：
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * 输入: [2,4,1], k = 2
 * 输出: 2
 * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * <p>
 * 示例 2:
 * 输入: [3,2,6,5,0,3], k = 2
 * 输出: 7
 * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *
 * @author ls J
 * @date 2020/6/23 16:11
 */
public class BestTimeToBuyAndSellStockIV188 {

    /**
     * 动态规划，数组占用
     * 其实跟 “买卖股票的最佳时机 III” 的方法一致，只是 k 从常数 2 变成了个参数
     * <p>
     * 测试数据量过大的时候，会爆内存
     *
     * @param k      k
     * @param prices prices
     * @return max profit
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        int[][][] dp = new int[n][k + 1][2];

        // 买卖 0 次，收益肯定都是 0
        for (int i = 0; i < n; ++i) {
            dp[i][0][0] = 0;
        }
        // 第 0 天初始化
        for (int i = 0; i <= k; ++i) {
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0];
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 1; j <= k; ++j) {
                // 第 i 天买卖了 j 次并且不持有股票 = max(前一天买卖了 j 次并且不持有股票, 前一天买卖了 j 次并且持有股票然后今天抛出)
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                // 第 i 天买卖了 j 次并且持有股票 = max(前一天买卖了 j-1 次并且不持有股票然后今天买入, 前一天买卖了 j 次，并且持有股票然后今天不抛出)
                dp[i][j][1] = Math.max(dp[i - 1][j - 1][0] - prices[i], dp[i - 1][j][1]);
            }
        }
        // 最后想要收益最大，肯定是不持有股票的状态
        return dp[n - 1][k][0];
    }

    /**
     * 方法二：滚动数组，跟前几个股票版本的思路一致，只是这里增加了一个 k 变量
     * <p>
     * 执行用时：4 ms，在所有 Java 提交中击败了 88.20% 的用户
     * 内存消耗：39.8 MB，在所有 Java 提交中击败了 11.11% 的用户
     *
     * @param k      k
     * @param prices prices
     * @return max profit
     */
    public int maxProfit2(int k, int[] prices) {
        if (k < 1) {
            return 0;
        }

        // k 超过了上限，也就变成了 无限次交易问题
        if (k > prices.length / 2) {
            return maxProfitOfII(prices);
        }
        // 状态定义
        int[][] dp = new int[k][2];

        // 初始化
        for (int i = 0; i < k; i++) {
            dp[i][0] = Integer.MIN_VALUE;
        }
        // 遍历每一天，模拟 k 次交易，计算并更新状态
        for (int p : prices) {
            // 第 1 次买
            dp[0][0] = Math.max(dp[0][0], 0 - p);
            // 第 1 次卖
            dp[0][1] = Math.max(dp[0][1], dp[0][0] + p);
            for (int i = 1; i < k; i++) {
                // 第 i 次买
                dp[i][0] = Math.max(dp[i][0], dp[i - 1][1] - p);
                // 第 i 次卖
                dp[i][1] = Math.max(dp[i][1], dp[i][0] + p);
            }
        }
        return dp[k - 1][1];
    }

    /**
     * 无限次交易
     *
     * @param prices prices
     * @return max profit
     */
    private int maxProfitOfII(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }
}
