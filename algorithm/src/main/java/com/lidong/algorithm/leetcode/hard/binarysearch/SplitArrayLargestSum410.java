package com.lidong.algorithm.leetcode.hard.binarysearch;

import java.util.Arrays;

/**
 * 分割数组的最大值（困难-410）
 * 中文链接：https://leetcode-cn.com/problems/split-array-largest-sum
 * <p>
 * 问题描述：
 * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
 * 注意:
 * 数组长度 n 满足以下条件:
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * <p>
 * 示例:
 * 输入:
 * nums = [7,2,5,10,8]
 * m = 2
 * 输出:
 * 18
 * 解释:
 * 一共有四种方法将nums分割为2个子数组。
 * 其中最好的方式是将其分为[7,2,5] 和 [10,8]，
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 *
 * @author Ls J
 * @date 2020/7/25 3:20 PM
 */
public class SplitArrayLargestSum410 {

    /**
     * 方法一：二分 + 贪心
     * 「使……最大值尽可能小」是二分搜索题目常见的问法。
     * <p>
     * 思路：
     * 1. 找出结果的最大值和最小值，最小值显然是数组最小值，最大值显然是数组所有元素之和。
     * 2. 二分：贪心地模拟分割的过程，从前到后遍历数组，用 sum表示当前分割子数组的和，cnt 表示已经分割出的子数组的数量（包括当前子数组），
     * 那么每当 sum 加上当前值超过了 x，我们就把当前取的值作为新的一段分割子数组的开头，并将 cnt 加 1。
     * 遍历结束后验证是否 cnt 不超过 m。
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：37.2 MB，在所有 Java 提交中击败了 33.33% 的用户
     * <p>
     * 时间复杂度：O(n×log(sum−maxn))
     * 空间复杂度：O(1)
     *
     * @param nums nums
     * @param m    m
     * @return max sum
     */
    public int splitArray(int[] nums, int m) {
        int l = 0, r = 0, n = nums.length;
        for (int num : nums) {
            r += num;
            l = Math.max(l, num);
        }

        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (check(nums, n, mid, m)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    /**
     * check 函数
     * 判断最大和为 x 的时候能不能满足条件
     *
     * @param nums nums
     * @param n    n
     * @param x    x
     * @param m    m
     * @return true if can
     */
    private boolean check(int[] nums, int n, int x, int m) {
        int cnt = 1, sum = 0;
        for (int num : nums) {
            if (sum + num > x) {
                ++cnt;
                sum = num;
            } else {
                sum += num;
            }
        }

        return cnt <= m;
    }

    /**
     * 方法二：动态规划
     * 「将数组分割为 mm 段，求……」是动态规划题目常见的问法。
     * <p>
     * 本题中，我们可以令 dp[i][j] 表示将数组的前 i 个数分割为 j 段所能得到的最大连续子数组和的最小值。
     * 在进行状态转移时，我们可以考虑第 j 段的具体范围，即我们可以枚举 k，其中前 k 个数被分割为 j-1 段，而第 k+1 到第 i 个数为第 j 段。
     * 此时，这 j 段子数组中和的最大值，就等于 dp[k][j−1] 与 sub(k+1,i) 中的较大值，其中 sub(i,j) 表示数组 nums 中下标落在区间 [i,j] 内的数的和。
     * <p>
     * 执行用时：48 ms，在所有 Java 提交中击败了 15.76% 的用户
     * 内存消耗：37.6 MB，在所有 Java 提交中击败了 33.33% 的用户
     * <p>
     * 时间复杂度：O(n^2 * m)
     * 空间复杂度：O(mn)
     *
     * @param nums nums
     * @param m    m
     * @return max sum
     */
    public int splitArray2(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        int[] sub = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + nums[i];
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sub[i] - sub[k]));
                }
            }
        }
        return dp[n][m];
    }
}
