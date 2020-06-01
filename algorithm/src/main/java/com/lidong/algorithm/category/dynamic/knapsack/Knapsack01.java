package com.lidong.algorithm.category.dynamic.knapsack;

/**
 * 0-1 背包问题
 * <p>
 * 问题描述：
 * 有 n 种物品和容量为 V 的背包，每种物体只有一件，第 i 件物品的体积为 c[i]，价值为 v[i]。
 * 现在的目标是确定要将哪些物体放入背包，以保证在体积不超过背包容量的前提下，背包内的总价值最高？
 * 返回最大价值
 *
 * @author ls J
 * @date 2020/5/28 9:39
 */
public class Knapsack01 {

    /**
     * 方法一：用一个二维数组来表示
     * dp[i][j] 代表前 i 个物品，体积刚好为 j 时的最大价值
     * <p>
     * 状态转移方程：
     * dp[i][j] = max(dp[i-1][j], dp[i-1][j-value[i]] + weight[i])
     * 1. 如果不选第 i 个物品，那么前 i 个物品的最大价值就是前 i-1 个物品的最大价值， dp[i][j] = dp[i-1][j]；
     * 2. 如果选第 i 个物品，那么前 i-1 个物品的体积是 j-value[i]，状态方程是 dp[i-1][j-value[i]]，
     * 因为选了第 i 个物品，所以前 i 个物品的最大价值是 dp[i-1][j-value[i]] + weight[i]；
     * 3. dp[i][j] 从上述两种情况中选择最大的即可。
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
        // 第 0 行代表前 0 个物品，dp[0][j] 应该始终为 0，所以这里从第 1 行开始 dp
        for (int i = 1; i <= len; ++i) {
            // 第 0 列，代表 v = 0，dp[i][0] 也始终为 0，同理从第 1 列开始 dp
            for (int j = 1; j <= capacity; ++j) {
                dp[i][j] = dp[i - 1][j];
                // 第 i 个物品对应的重量应该是 weight[i - 1]，价值是 value[i - 1]，注意这里面有一个 -1
                if (c[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - c[i - 1]] + v[i - 1]);
                }
            }
        }
        int res = 0;
        // 最大价值从 dp 数组最后一行寻找
        for (int i = 0; i <= capacity; ++i) {
            res = Math.max(res, dp[len][i]);
        }
        return res;
    }

    /**
     * 方法二：用一个一维的 dp 数组来实现
     * 通过方法一我们发现，dp 数组的第 i 行只与第 i - 1 行有关系，因此我们可以用一个一维数组来实现
     * <p>
     * 状态转移方程
     * dp[j] = max(dp[j], dp[j-value[i]] + weight[i])
     * dp[j] 表示物品体积之和最大为 j 时的最大价值，而非恰好为 j 时的最大价值。
     * <p>
     * 与二维数组相比：dp[i][j] -> dp[j]，dp[i-1][j-value[i]] + weight[i] -> dp[j-value[i]] + weight[i]
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
        // dp[i][j] 代表前 i 个物品（下标从 1 开始），体积为 j 时的最大价值
        int[] dp = new int[capacity + 1];
        // 枚举前 i 个物品
        for (int i = 1; i <= len; ++i) {
            // 倒序枚举 v
            // 由于 v 是倒序遍历，dp[j-weight[i]] 在遍历到 dp[j] 时其实已经算过了，只是保存的是 i-1 时的值，
            // 当我们继续倒序遍历到 dp[j-weight[i]] 时，自然就将上一个状态 i-1 的值进行了更新。
            for (int j = capacity; j >= c[i - 1]; --j) {
                // 第 i 个物品对应的重量应该是 weight[i - 1]，价值是 value[i - 1]，注意这里面有一个 -1
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
