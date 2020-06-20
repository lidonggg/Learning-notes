package com.lidong.algorithm.leetcode.medium.binarysearch;

/**
 * 搜索旋转排序数组 II（中等-81）
 * 中文链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
 * <p>
 * 问题描述：
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * (例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2])。
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 * <p>
 * 示例 1:
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 * <p>
 * 进阶:
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 *
 * @author Ls J
 * @date 2020/6/20 1:47 PM
 */
public class SearchInRotatedSortedArrayII81 {

    /**
     * 二分搜索
     *
     * @param nums   nums
     * @param target target
     * @return true if found
     */
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == target) {
                return true;
            }
            // 如果相等，我们不能确定 l ~ mid 到底是不是生序，这里将 l + 1 即可
            if (nums[l] == nums[mid]) {
                l++;
                continue;
            }
            // 此时 l ~ mid 是生序的
            if (nums[mid] > nums[l]) {
                if (target >= nums[l] && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            } else {
                // 此时 mid ~ r 是生序的
                if (target > nums[mid] && target <= nums[r]) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
        }

        return false;
    }
}
