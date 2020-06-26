package com.lidong.algorithm.leetcode.medium.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 合并区间（中等-56）
 * 中文链接：https://leetcode-cn.com/problems/merge-intervals/
 * <p>
 * 问题描述：
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * @author Ls J
 * @date 2020/6/26 4:07 PM
 */
public class MergeIntervals56 {

    /**
     * 先将区间排序，然后连续的一些区间会合并成一个大区间
     * <p>
     * 执行用时：7 ms，在所有 Java 提交中击败了 88.43% 的用户
     * 内存消耗：42.6 MB，在所有 Java 提交中击败了 35.61% 的用户
     *
     * @param intervals intervals
     * @return int[][]
     */
    public int[][] merge(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval : intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = interval[1];
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }
}
