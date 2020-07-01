package com.lidong.algorithm.leetcode.medium.dynamic;

/**
 * 整数拆分（中等-343）
 * 中文链接：https://leetcode-cn.com/problems/integer-break
 * <p>
 * 问题描述：
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * <p>
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * <p>
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * <p>
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 *
 * @author ls J
 * @date 2020/7/1 19:58
 */
public class IntegerBreak343 {

    /**
     * 方法一：动态规划
     * 状态转移方程：
     * dp[i] = max(dp[i], dp[i-j] * dp[j])
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：36.3 MB，在所有 Java 提交中击败了 7.69% 的用户
     *
     * @param n n
     * @return max res
     */
    public int integerBreak(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for (int i = 4; i <= n; ++i) {
            for (int j = 2; j <= i / 2; ++j) {
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }
        return dp[n];
    }

    /**
     * 方法二：数学
     * 尽可能多的出现 3，最后剩余 1 的话，-3 +4
     *
     * @param n n
     * @return max res
     */
    public int integerBreak2(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int a = n / 3, b = n % 3;
        if (b == 0) {
            return (int) Math.pow(3, a);
        }
        if (b == 1) {
            return (int) Math.pow(3, a - 1) * 4;
        }
        return (int) Math.pow(3, a) * 2;
    }

}
