package com.lidong.algorithm.leetcode.medium.twopointers;

import java.util.Arrays;

/**
 * 最小差（面试题-16.06）
 * 中文链接：https://leetcode-cn.com/problems/smallest-difference-lcci
 * <p>
 * 问题描述：
 * 给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
 * <p>
 * 示例：
 * 输入：{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
 * 输出： 3，即数值对(11, 8)
 * <p>
 * 提示：
 * - 1 <= a.length, b.length <= 100000
 * - -2147483648 <= a[i], b[i] <= 2147483647
 * - 正确结果在区间[-2147483648, 2147483647]内
 *
 * @author ls J
 * @date 2020/7/10 16:07
 */
public class SmallestDifference1606 {

    /**
     * 双指针：先排序，然后再用双指针
     * <p>
     * 也可以先排序然后再用二分法
     * <p>
     * 执行用时：22 ms，在所有 Java 提交中击败了 78.49% 的用户
     * 内存消耗：48.1 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param a a
     * @param b b
     * @return minDiff
     */
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0, j = 0;
        // 为了避免溢出，这里保存差的负数值，然后更改的时候，按照两个负数中的较大值来更新
        int minDiff = Integer.MIN_VALUE;
        while (i < a.length && j < b.length) {
            // 负数域大于正数域
            minDiff = Math.max(minDiff, -Math.abs(a[i] - b[j]));
            if (a[i] < b[j]) {
                i++;
            } else {
                j++;
            }
        }
        // 最后取绝对值
        return Math.abs(minDiff);
    }
}
