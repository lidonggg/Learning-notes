package com.lidong.algorithm.leetcode.easy.dynamic;

/**
 * 最大子序和（简单-53）
 * 问题描述：
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 进阶：
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * @author ls J
 * @date 2020/3/25 10:54
 */
public class MaxSubArray53 {

    /**
     * 动态规划，状态转移方程如下：
     * dp[i] = dp[i-1] <= 0 ? dp[i] : dp[i-1] + dp[i], i >= 1
     * dp[0] = nums[0]
     *
     * @param nums nums
     * @return res
     */
    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        // 可以另外新生成一个数组，也可以直接在 nums 数组上进行动态规划
//        int[] dp = new int[len];
//        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < len; ++i) {
            // or dp[i-1]
            if (nums[i - 1] <= 0) {
                nums[i] = nums[i];
            } else {
                nums[i] = nums[i - 1] + nums[i];
            }
            max = Math.max(max, nums[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
