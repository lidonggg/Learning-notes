package com.lidong.algorithm.leetcode.medium.binarysearch;

/**
 * 在排序数组中查找元素的第一个和最后一个位置（中等-34）
 * 中文链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * <p>
 * 问题描述：
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * <p>
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * @author Ls J
 * @date 2020/6/20 11:33 PM
 */
public class FindFirstAndLastPositionOfElementInSortedArray34 {

    /**
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：42.9 MB，在所有 Java 提交中击败了 63.16% 的用户
     *
     * @param nums   nums
     * @param target target
     * @return int[]
     */
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        int mid;
        int tf = -1;
        // 查找第一个值等于某个值的位置
        while (l <= r) {
            mid = l + ((r - l) >> 1);

            if (nums[mid] > target) {
                r = mid - 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                if (mid == 0 || nums[mid - 1] != target) {
                    tf = mid;
                    break;
                }
                r = mid - 1;
            }
        }
        // 如果不存在，直接返回
        if (tf == -1) {
            return new int[]{-1, -1};
        }
        l = tf;
        r = n - 1;
        // 查找最后一个值等于某个值的位置
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (nums[mid] > target) {
                r = mid - 1;
            } else {
                if (mid == n - 1 || nums[mid + 1] != target) {
                    r = mid;
                    break;
                }
                l = mid + 1;
            }
        }
        return new int[]{tf, r};
    }
}
