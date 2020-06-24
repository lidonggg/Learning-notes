package com.lidong.algorithm.leetcode.medium.dynamic.stock;

/**
 * 买卖股票的最佳时机含手续费（中等-714）
 * 中文链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 * <p>
 * 问题描述：
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * <p>
 * 示例 1
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * <p>
 * 注意:
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 *
 * @author ls J
 * @date 2020/6/23 17:20
 */
public class BestTimeToBuyAndSellStockWithTransactionFee714 {

    /**
     * 动态规划，这里这给出常数空间占用的方法
     * <p>
     * 执行用时：4 ms，在所有 Java 提交中击败了 99.96% 的用户
     * 内存消耗：48.9 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param prices prices
     * @param fee    fee
     * @return max profit
     */
    public int maxProfit(int[] prices, int fee) {

        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        int dp0 = 0, dp1 = -prices[0] - fee;
        for (int i = 1; i < n; ++i) {
            dp0 = Math.max(dp0, dp1 + prices[i]);
            // 每次交易的同时带上手续费
            dp1 = Math.max(dp1, dp0 - prices[i] - fee);
        }

        return dp0;
    }
}
