package com.lidong.algorithm.leetcode.easy.dynamic;

/**
 * 区域和检索 - 数组不可变（中等-303）
 * 中文链接：https://leetcode-cn.com/problems/range-sum-query-immutable/
 * <p>
 * 问题描述：
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 * <p>
 * 示例：
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * <p>
 * 说明:
 * 你可以假设数组不可变。
 * 会多次调用 sumRange 方法。
 *
 * @author ls J
 * @date 2020/5/22 15:21
 */
public class RangeSumQueryImmutable303 {

    /**
     * dp 数组，sums[i] 保存从 0 到 i（包含）的元素之和
     */
    private int[] sums;

    private int len;

    /**
     * DP
     * <p>
     * 执行用时：10 ms，在所有 Java 提交中击败了 99.41% 的用户
     * 内存消耗：42.8 MB，在所有 Java 提交中击败了 39.13% 的用户
     *
     * @param nums num arr
     */
    public RangeSumQueryImmutable303(int[] nums) {
        int len = nums.length;
        this.sums = new int[len];
        if ((this.len = len) > 0) {
            this.sums[0] = nums[0];
            for (int i = 1; i < len; ++i) {
                this.sums[i] = this.sums[i - 1] + nums[i];
            }
        }
    }

    /**
     * sum = sums[j] - sums[i-1]
     *
     * @param i idx_i
     * @param j idx_j
     * @return sum
     */
    public int sumRange(int i, int j) {
        if (this.len == 0) {
            return 0;
        }
        if (i >= len) {
            i = len - 1;
        }
        if (j >= len) {
            j = len - 1;
        }
        if (i < 0) {
            i = 0;
        }
        if (j < 0) {
            j = 0;
        }
        if (i == 0) {
            return sums[j];
        }
        return this.sums[j] - this.sums[i - 1];
    }

    public static void main(String[] args) {

    }
}
