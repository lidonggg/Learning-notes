package com.lidong.algorithm.category.dynamic.knapsack;

/**
 * 完全背包问题
 * <p>
 * 问题描述：
 * 有 n 种物品和容量为 V 的背包，每种物体有无数件，第 i 件物品的体积为 c[i]，价值为 v[i]。
 * 现在的目标是确定要将哪些物体放入背包，以保证在体积不超过背包容量的前提下，背包内的总价值最高？
 * 返回最大价值
 *
 * @author ls J
 * @date 2020/5/29 10:16
 */
public class KnapsackFully {

    /**
     * 方法一：二维数组实现
     *
     * @param c        weight[]
     * @param v        value[]
     * @param capacity v
     * @return max value
     */
    public static int knapsack1(int[] c, int[] v, int capacity) {
        int len;
        if ((len = c.length) == 0 || capacity == 0) {
            return 0;
        }
        // dp[i][j] 代表前 i 个物品（下标从 1 开始），体积为 j 时的最大价值
        int[][] dp = new int[len + 1][capacity + 1];
        for (int i = 1; i <= len; ++i) {
            for (int j = 1; j <= capacity; ++j) {
                // 每个物品最多可以放 k 个，其中 k * c[i - 1] <= j
                for (int k = 0; k * c[i - 1] <= j; ++k) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * c[i - 1]] + k * v[i - 1]);
                }
            }
        }

        return dp[len][capacity];
    }

    /**
     * 方法二：一维数组实现
     *
     * @param c        weight[]
     * @param v        value[]
     * @param capacity v
     * @return max value
     */
    public static int knapsack2(int[] c, int[] v, int capacity) {
        int len;
        if ((len = c.length) == 0 || capacity == 0) {
            return 0;
        }
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= len; ++i) {
            // 这里从前往后遍历，因为每个物品可以取任意多次，所以不再强求用上一轮的状态，本轮放过的物品，在后面还可以再放
            for (int j = c[i - 1]; j <= capacity; ++j) {
                dp[j] = Math.max(dp[j], dp[j - c[i - 1]] + v[i - 1]);
            }
        }
        return dp[capacity];
    }

    public static void main(String[] args) {
        int[] weight = {1, 2, 5};
        int[] value = {1, 4, 8};
        int v = 8;
        System.out.println(knapsack2(weight, value, v));
    }
}
