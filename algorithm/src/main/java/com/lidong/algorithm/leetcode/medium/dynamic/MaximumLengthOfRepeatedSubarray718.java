package com.lidong.algorithm.leetcode.medium.dynamic;

/**
 * 最长重复子数组（中等-718）
 * 中文链接：https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray
 * <p>
 * 问题描述：
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * <p>
 * 示例：
 * 输入：
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出：3
 * 解释：
 * 长度最长的公共子数组是 [3, 2, 1] 。
 * <p>
 * 提示：
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 *
 * @author ls J
 * @date 2020/7/29 18:31
 */
public class MaximumLengthOfRepeatedSubarray718 {

    /**
     * 方法一：动态规划
     * dp[i][j] = dp[i - 1][j - 1] + 1;  dp[i][j] 代表以 a[i-1], b[j-1] 为结尾的公共子串长度
     * <p>
     * 执行用时：43 ms，在所有 Java 提交中击败了 95.57% 的用户
     * 内存消耗：48.9 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param a a
     * @param b b
     * @return max len
     */
    public int findLength(int[] a, int[] b) {
        int m = a.length, n = b.length;
        // 这里可以简化为一维动态数组
        int[][] dp = new int[m + 1][n + 1];
        int res = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }

    /**
     * 方法二：滑动窗口
     * 来自 leetcode 官方题解：https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/solution/zui-chang-zhong-fu-zi-shu-zu-by-leetcode-solution/
     * <p>
     * 枚举 A 和 B 的所有对齐方式，对齐方式有两类：
     * 1. A 不变，B 的首元素与 A 中的某个元素对齐；
     * 2. B 不变，A 的首元素与 B 中的某个元素对齐。
     * 对于每一种对齐方式，我们计算他们相对位置相同的重复子数组即可。
     * <p>
     * 执行用时：51 ms，在所有 Java 提交中击败了 80.93% 的用户
     * 内存消耗：39.5 MB，在所有 Java 提交中击败了 86.67% 的用户
     *
     * @param a a
     * @param b b
     * @return max len
     */
    public int findLength2(int[] a, int[] b) {
        int n = a.length, m = b.length;
        int res = 0;
        // 1. A 不变，B 的首元素与 A 中的某个元素对齐
        for (int i = 0; i < n; i++) {
            int len = Math.min(m, n - i);
            int maxLen = maxLength(a, b, i, 0, len);
            res = Math.max(res, maxLen);
        }
        // 2. B 不变，A 的首元素与 B 中的某个元素对齐
        for (int i = 0; i < m; i++) {
            int len = Math.min(n, m - i);
            int maxLen = maxLength(a, b, 0, i, len);
            res = Math.max(res, maxLen);
        }
        return res;
    }

    /**
     * 获取某种对齐方式的最长重复子串长度
     *
     * @param a    a
     * @param b    b
     * @param addA a idx 偏移量
     * @param addB b idx 偏移量
     * @param len  要比较的两个数组最大长度
     * @return max len
     */
    private int maxLength(int[] a, int[] b, int addA, int addB, int len) {
        int res = 0, k = 0;
        for (int i = 0; i < len; i++) {
            if (a[addA + i] == b[addB + i]) {
                k++;
            } else {
                k = 0;
            }
            res = Math.max(res, k);
        }
        return res;
    }
}
