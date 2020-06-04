package com.lidong.algorithm.leetcode.interview.medium;

/**
 * 硬币（中等-面试08-11）
 * 中文链接：https://leetcode-cn.com/problems/coin-lcci/
 * <p>
 * 问题描述：
 * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上 1000000007)
 * <p>
 * 示例1:
 * 输入: n = 5
 * 输出：2
 * 解释: 有两种方式可以凑成总金额:
 * 5=5
 * 5=1+1+1+1+1
 * <p>
 * 示例2:
 * 输入: n = 10
 * 输出：4
 * 解释: 有四种方式可以凑成总金额:
 * 10=10
 * 10=5+5
 * 10=5+1+1+1+1+1
 * 10=1+1+1+1+1+1+1+1+1+1
 * 说明：
 * <p>
 * 注意:
 * 你可以假设：
 * 0 <= n (总金额) <= 1000000
 *
 * @author Ls J
 * @date 2020/5/24 3:30 PM
 */
public class CoinLCCI0811 {

    private int[] units = {25, 10, 5, 1};

    /**
     * 方法一：动态规划 - 背包方案数问题：
     * dp[i] 存放 i 分可以被表示的不同方法个数。
     * 前 i 种硬币，构成面值 v 的方案数量由前 i - 1 种面值构成的一些面值的方案数量决定。
     * <p>
     * 执行用时：42 ms，在所有 Java 提交中击败了 69.44% 的用户
     * 内存消耗：43.5 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param n n
     * @return res mod 1000000007
     */
    public int waysToChange(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int unit : units) {
            for (int i = unit; i <= n; ++i) {
                dp[i] = (dp[i] + dp[i - unit]) % 1000000007;
            }
        }
        return dp[n];
    }

    /**
     * 方法二：数学，等差数列
     * 先枚举 25 分的硬币的个数
     * <p>
     * 来自 leetcode 官方题解：https://leetcode-cn.com/problems/coin-lcci/solution/ying-bi-by-leetcode-solution/
     *
     * @param n n
     * @return res mod 1000000007
     */
    public int waysToChange2(int n) {
        int res = 0;
        for (int i = 0; i * 25 <= n; ++i) {
            int rest = n - i * 25;
            int a = rest / 10;
            int b = rest % 10 / 5;
            res = (res + (int) ((long) (a + 1) * (long) (a + b + 1)) % 1000000007) %1000000007;
        }
        return res;
    }

    public static void main(String[] args) {
        CoinLCCI0811 cc = new CoinLCCI0811();
        System.out.println(cc.waysToChange2(900750));
    }
}
