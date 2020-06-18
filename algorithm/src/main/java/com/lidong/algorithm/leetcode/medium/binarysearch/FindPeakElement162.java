package com.lidong.algorithm.leetcode.medium.binarysearch;

/**
 * 寻找峰值（中等-162）
 * 中文链接：https://leetcode-cn.com/problems/find-peak-element/
 * <p>
 * 问题描述：
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * <p>
 * 示例 1:
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * <p>
 * 示例 2:
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 * <p>
 * 说明:
 * 你的解法应该是 O(logN) 时间复杂度的。
 *
 * @author ls J
 * @date 2020/6/18 19:50
 */
public class FindPeakElement162 {

    /**
     * 二分查找
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.4 MB，在所有 Java 提交中击败了 10.00% 的用户
     *
     * @param nums nums
     * @return index
     */
    public static int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        if (len == 1) {
            return 0;
        }

        int l = 0, r = len - 1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            // 如果中间值比右边的元素大，那么在左半部分一定可以找到一个峰值
            if (nums[mid] > nums[mid + 1]) {
                r = mid;
            } else {
                // 相反，在右半部分一定可以找到一个峰值
                l = mid + 1;
            }
        }

        return l;
    }

    public static void main(String[] args) {
        System.out.print(findPeakElement(new int[]{1, 2, 3, 1}));
    }
}
