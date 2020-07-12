package com.lidong.algorithm.leetcode.medium.dynamic;

/**
 * 计算各个位数不同的数字个数（中等-357）
 * 中文链接：https://leetcode-cn.com/problems/count-numbers-with-unique-digits
 * <p>
 * 问题描述：
 * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。
 * <p>
 * 示例:
 * 输入: 2
 * 输出: 91
 * 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
 *
 * @author Ls J
 * @date 2020/7/13 1:38 AM
 */
public class CountNumbersWithUniqueDigits357 {

    /**
     * 直接 dp
     * 一位数有 10 个，二位数有 81 = 9 * 9 个
     * 三位数有 9 * 9 * 8 个
     * 由此可见，就是一个排列组合的问题，从 i = 3 开始，i 位数的最后一位可选择的数的个数是 (10 - (i - 1))
     *
     * @param n n
     * @return num
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 9;
        int res = 10;
        for (int i = 2; i <= n; ++i) {
            dp[i] = dp[i - 1] * (10 - i + 1);
            res += dp[i];
        }
        return res;
    }
}
