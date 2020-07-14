package com.lidong.algorithm.leetcode.medium.twopointers;

import java.util.Arrays;

/**
 * 最接近的三数之和（中等-16）
 * 中文链接：https://leetcode-cn.com/problems/3sum-closest
 * <p>
 * 问题描述：
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 示例：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * <p>
 * 提示：
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 *
 * @author ls J
 * @date 2020/7/14 21:03
 */
public class Sum3Closest16 {

    /**
     * 三个指针，固定两边两个，移动中间指针
     * <p>
     * 执行用时：6 ms，在所有 Java 提交中击败了 85.58% 的用户
     * 内存消耗：39.4 MB，在所有 Java 提交中击败了 6.82% 的用户
     *
     * @param nums   nums
     * @param target target
     * @return sum
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = Integer.MAX_VALUE >> 4;
        for (int i = 0; i < n - 2; ++i) {
            // 排除掉第一个指针相同的数字
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = n - 1;
            int k = i + 1;

            while (k < j) {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum == target) {
                    return sum;
                }
                if (Math.abs(res - target) > Math.abs(sum - target)) {
                    res = sum;
                }

                if (sum < target) {
                    ++k;
                    // 跳过相同数字
                    while (k < j && nums[k] == nums[k - 1]) {
                        ++k;
                    }
                } else {
                    --j;
                    // 跳过相同数字
                    while (j > k && nums[j] == nums[j + 1]) {
                        --j;
                    }
                }
            }
        }
        return res;
    }
}
