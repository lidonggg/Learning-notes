package com.lidong.algorithm.leetcode.medium.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和（中等-18）
 * 中文链接：https://leetcode-cn.com/problems/4sum
 * <p>
 * 问题描述：
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 *
 * @author ls J
 * @date 2020/7/10 21:12
 */
public class SumFour18 {

    /**
     * 固定前两个数字，后面两个数字用双指针
     * <p>
     * 执行用时：3 ms，在所有 Java 提交中击败了 99.96% 的用户
     * 内存消耗：39.7 MB，在所有 Java 提交中击败了 13.16% 的用户
     *
     * @param nums   nums
     * @param target target
     * @return list
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        if (n <= 3) {
            return res;
        }

        Arrays.sort(nums);

        for (int i = 0; i < n - 3; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 包含当前数字的最小值比 target 大，那么所有情况都不可能成立，直接 break 循环
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            // 包含当前数字的最大值比 target 小，当前数字都不会成立，去循环下一个数字
            if (nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) {
                continue;
            }

            for (int j = i + 1; j < n - 2; ++j) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 同理
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 1] > target) {
                    break;
                }
                // 同理
                if (nums[i] + nums[j] + nums[n - 1] + nums[n - 2] < target) {
                    continue;
                }

                int k = j + 1, h = n - 1;
                while (k < h) {
                    int cur = nums[i] + nums[j] + nums[k] + nums[h];
                    if (cur < target) {
                        k++;
                    } else if (cur > target) {
                        h--;
                    } else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[h]));
                        k++;
                        while (k < h && nums[k] == nums[k - 1]) {
                            k++;
                        }
                        h--;
                        while (k < h && j < h && nums[h] == nums[h + 1]) {
                            h--;
                        }
                    }
                }
            }
        }

        return res;
    }
}
