package com.lidong.algorithm.leetcode.medium.array;

/**
 * 下一个排列（中等-31）
 * 中文链接：https://leetcode-cn.com/problems/next-permutation/
 * <p>
 * 问题描述：
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 * <p>
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * @author Ls J
 * @date 2020/6/20 11:02 PM
 */
public class NextPermutation31 {

    /**
     * 执行用时：1 ms，在所有 Java 提交中击败了 99.57% 的用户
     * 内存消耗：39.7 MB，在所有 Java 提交中击败了 53.13% 的用户
     *
     * @param nums nums
     */
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return;
        }

        // 从后往前找第一个降序的数
        // 例如：1 2 3 1，此时找到的第一个降序数是 2 (first 为 1)
        int first = n - 2;
        while (first >= 0 && nums[first] >= nums[first + 1]) {
            first--;
        }

        if (first >= 0) {
            // 从后往前找 first 之后第一个比 nums[first] 大的数
            // 由 first 的规则可知这里肯定可以找到
            int second = n - 1;
            while (second > first && nums[second] <= nums[first]) {
                second--;
            }
            // swap
            swap(nums, first, second);
        }
        // first 位置之后的数应该被反转，这样才是下一个较大的数
        // 例如原数组为 1 3 5 4 3 2 1，first 为 1，second 为 3
        // 交换 nums[first]，nums[second]：1 4 5 3 3 2 1，此时 first 位置之后的数是递减的
        // 反转：1 4 1 2 3 3 5，此时才是所求的结果
        reverse(nums, first + 1);
    }

    /**
     * 反转
     *
     * @param nums nums
     * @param i    开始位置
     */
    private void reverse(int[] nums, int i) {
        int j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    /**
     * swap
     *
     * @param nums nums
     * @param i    i
     * @param j    j
     */
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
