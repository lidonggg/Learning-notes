package com.lidong.algorithm.leetcode.medium.binarysearch;

/**
 * 寻找旋转排序数组中的最小值（中等-153）
 * 中文链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
 * <p>
 * 问题描述：
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 请找出其中最小的元素。
 * 你可以假设数组中不存在重复元素。
 * <p>
 * 示例 1:
 * 输入: [3,4,5,1,2]
 * 输出: 1
 * <p>
 * 示例 2:
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 *
 * @author ls J
 * @date 2020/6/30 13:21
 */
public class FindMinimumInRotatedSortedArray153 {

    /**
     * 二分查找
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.4 MB，在所有 Java 提交中击败了 5.55% 的用户
     *
     * @param nums nums
     * @return num
     */
    public int findMin(int[] nums) {
        int n = nums.length;

        int l = 0, r = n - 1;

        while (l < r) {
            int mid = l + ((r - l) >> 1);
            // 如果中间值小于左边界的值，说明目标元素在 l ~ mid 区间内
            if (nums[mid] < nums[r]) {
                r = mid;
            } else {
                // 否则说明目标元素在 mid + 1 ~ r 区间内
                l = mid + 1;
            }
        }

        return nums[l];
    }
}
