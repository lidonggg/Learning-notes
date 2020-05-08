package com.lidong.algorithm.leetcode.easy;

/**
 * 移除元素（简单-27）
 * 问题描述：
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 示例 1:
 * 给定 nums = [3,2,2,3], val = 3,
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * @author Ls J
 * @date 2020/3/7 1:51 PM
 */
public class RemoveElement27 {

    public static int removeElement(int[] nums, int val) {
        int res = nums.length;
        for (int i = 0; i < nums.length; ++i) {
            // 每次在当前位置与给定元素相同的时候，都去与剩余的最后一个元素替换，直到往后的元素都是给定的值为止
            while (nums[i] == val && i < res) {
                nums[i] = nums[res - 1];
                res--;
            }
        }
        return res;
    }

    /**
     * 方法2：快慢双指针
     *
     * @param nums ums
     * @param val  val
     * @return
     */
    public int removeElement2(int[] nums, int val) {
        // 慢指针
        int current = -1;
        // 快指针
        int index = 0;
        int length = nums.length;

        for (; index < length; index++) {
            if (nums[index] != val) {
                nums[++current] = nums[index];
            }
        }
        return current + 1;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3, 2, 4};
        int res = removeElement(nums, 3);
        System.out.println(res);
        for (int i = 0; i < res; ++i) {
            System.out.println(nums[i]);
        }

    }
}
