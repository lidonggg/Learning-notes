package com.lidong.algorithm.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ls J
 * @date 2019/6/26 4:00 PM
 *
 * 两数之和（简单）
 * 问题描述：给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 */
public class TwoSum {

    /**
     * 方法1 遍历
     *
     * @param nums   原数组
     * @param target 目标值
     * @return int[]
     */
    private static int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        for (int i = 0; i < len - 1; ++i) {
            int goal = target - nums[i];
            for (int j = i + 1; j < len; ++j) {
                if (goal == nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 方法2 哈希映射
     *
     * 遍历数组 nums，i 为当前下标，每个值都判断map中是否存在 target-nums[i] 的 key 值
     * 如果存在则找到了两个值，如果不存在则将当前的 (nums[i],i) 存入 map 中，继续遍历直到找到为止
     *
     * @param nums   原数组
     * @param target 目标值
     * @return int[]
     */
    private static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 9};
        int[] result = twoSum(nums, 4);
        for (int m : result) {
            System.out.print(m + " ");
        }
    }
}
