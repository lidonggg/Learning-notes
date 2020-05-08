package com.lidong.algorithm.leetcode.middling.dynamic;

/**
 * 等差数列划分（中等-413）
 * 问题描述：
 * 如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * <p>
 * 例如，以下数列为等差数列:
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * <p>
 * 以下数列不是等差数列。
 * 1, 1, 2, 5, 7
 *  
 * 数组 A 包含 N 个数，且索引从0开始。数组 A 的一个子数组划分为数组 (P, Q)，P 与 Q 是整数且满足 0<=P<Q<N 。
 * 如果满足以下条件，则称子数组(P, Q)为等差数组：
 * 元素 A[P], A[p + 1], ..., A[Q - 1], A[Q] 是等差的。并且 P + 1 < Q 。
 * 函数要返回数组 A 中所有为等差数组的子数组个数。
 * <p>
 * 示例:
 * A = [1, 2, 3, 4]
 * 返回: 3, A 中有三个子等差数组: [1, 2, 3], [2, 3, 4] 以及自身 [1, 2, 3, 4]。
 *
 * @author ls J
 * @date 2019/9/2 4:50 PM
 */
public class NumberOfArithmeticSlices413 {

    public static int numberOfArithmeticSlices(int[] arr) {
        int len = arr.length;
        if (len <= 2) {
            return 0;
        }

        // 用 dp[i] 代表走到数组的第 i 个位置的时候，已产生的等差数列的个数
        // 事实上 dp[i] 的前两项都是 0
        int[] dp = new int[len];

        //初始化
        for (int i = 0; i < len; ++i) {
            dp[i] = 0;
        }
        int sum = 0;
        for (int i = 2; i < len; ++i) {
            // 每往后移一位，就与它之前的相邻两位进行比较
            // 如果恰好构成等差了，则 dp[i] = dp[i - 1] + 1，
            // 意思就是以 arr[i] 结尾的等差数列个数等于 以 arr[i - 1] 结尾的等差数列的个数再加一（增加的一个正是 dp[i]、dp[i-1]、dp[i-1]）
            if (arr[i] + arr[i - 2] == 2 * arr[i - 1]) {
                dp[i] = dp[i - 1] + 1;
                sum += dp[i];
            }
        }

        return sum;
    }

    /**
     * 常数空间占用的动态规划
     *
     * @param arr arr
     * @return sum
     */
    public static int numberOfArithmeticSlices2(int[] arr) {
        int dp = 0;
        int sum = 0;
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] == arr[i - 1] - arr[i - 2]) {
                dp = 1 + dp;
                sum += dp;
            } else {
                dp = 0;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 8, 9, 10};
        System.out.println(numberOfArithmeticSlices(arr));
    }
}
