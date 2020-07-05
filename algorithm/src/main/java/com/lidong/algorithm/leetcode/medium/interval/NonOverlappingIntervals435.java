package com.lidong.algorithm.leetcode.medium.interval;

import java.util.Arrays;

/**
 * 无重叠区间（中等-435）
 * 中文链接：https://leetcode-cn.com/problems/non-overlapping-intervals/
 * <p>
 * 问题描述：
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * <p>
 * 注意:
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * <p>
 * 示例 1:
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * <p>
 * 示例 2:
 * 输入: [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * <p>
 * 示例 3:
 * 输入: [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 * @author Ls J
 * @date 2020/7/5 1:16 PM
 */
public class NonOverlappingIntervals435 {

    /**
     * 贪心算法
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.9 MB，在所有 Java 提交中击败了 8.33% 的用户
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param intervals intervals
     * @return min removes
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        // 先根据区间左端点进行排序
        Arrays.sort(intervals, (a1, a2) -> Integer.compare(a1[0], a2[0]));

        int rem = 0;
        // 保存最后留下来的区间
        int l = 0;
        for (int i = 1; i < n; ++i) {
            // 如果当前区间与上次最后留下来的区间有重叠
            if (intervals[l][1] > intervals[i][0]) {
                // 那我们保留两个区间中又端点比较小的那个
                // 这样的话能够使最后的区间与下一个区间相比的时候，重叠的概率尽可能小
                if (intervals[l][1] > intervals[i][1]) {
                    l = i;
                }
                rem++;
            } else {
                // 否则没有重叠，那么本次保留的最后一个区间就是第 i 个
                l = i;
            }
        }

        return rem;
    }
}
