package com.lidong.algorithm.leetcode.middling.dynamic;

/**
 * 丑数Ⅱ（中等-264）
 * 问题描述：
 * 编写一个程序，找出第 n 个丑数。
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 * <br>
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * <br>
 * 说明:  
 * 1 是丑数。
 * n 不超过1690。
 *
 * @author ls J
 * @date 2019/8/1 9:49 AM
 */
public class NthUglyNumber264 {

    /**
     * 动态规划 + 三指针：
     * 每次到达第 i 个数的时候，它都有可能是前面某个数 *2，*3 或者 *5 得到，关键是如何在数组中找到要乘以它们的数值。
     * 这里我们用三个指针来分别表示走到某一个数的时候是 *2，*3 还是 *5 来的，并记录相应的操作的个数，
     * 这样的话，下一步的时候的变化就是三个指针指向的最近的位置分别乘以对应的权重的最小值
     * 公式如下：
     * dp[i] = Math.min(dp[i2] * 2, Math.min(dp[i3] * 3, dp[i5] * 5))
     *
     * @param n n
     * @return 第 n 个丑数
     */
    public static int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        for (int i = 1; i < n; ++i) {
            int min = Math.min(dp[i2] * 2, Math.min(dp[i3] * 3, dp[i5] * 5));

            // 这里要用三个并列的 if 语句
            // 因为可能有以下情况，例如：
            // 丑数6，可能由于丑数2乘以权重3产生；也可能由于丑数3乘以权重2产生。
            if (min == dp[i2] * 2) {
                i2++;
            }
            if (min == dp[i3] * 3) {
                i3++;
            }
            if (min == dp[i5] * 5) {
                i5++;
            }
            dp[i] = min;
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(12));
    }
}
