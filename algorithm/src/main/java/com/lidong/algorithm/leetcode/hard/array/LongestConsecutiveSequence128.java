package com.lidong.algorithm.leetcode.hard.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 最长连续序列（困难-128）
 * 中文链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 * <p>
 * 问题描述：
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 * <p>
 * 示例:
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * @author ls J
 * @date 2020/5/14 9:47
 */
public class LongestConsecutiveSequence128 {

    /**
     * 方法一：O(n) 时间复杂度
     * 这些数字用一个 HashSet 保存，实现 O(1) 时间的查询，同时，我们只对 k -1 不在哈希表里的数字，
     * 作为连续序列的第一个数字去找对应的最长序列，这是因为其他数字一定已经出现在了某个序列里。
     *
     * @param nums num arr
     * @return longestConsecutive
     */
    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;
        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int curNum = num;
                int curStreak = 1;

                while (numSet.contains(curNum + 1)) {
                    curNum += 1;
                    curStreak += 1;
                }
                longestStreak = Math.max(longestStreak, curStreak);
            }
        }

        return longestStreak;
    }

    /**
     * 方法二：先排序，排序需要 O(nlgn) 时间复杂度
     *
     * @param nums num arr
     * @return longestConsecutive
     */
    public static int longestConsecutive2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 排序
        Arrays.sort(nums);

        int longestStreak = 1;
        int curStreak = 1;
        for (int i = 1; i < nums.length; ++i) {
            // 如果 nums[i] == nums[i - 1]，则不需要做任何操作，因为对于此值来说，curStreak并没有变化
            if (nums[i] != nums[i - 1]) {
                // 与前面一个数字是连续的
                if (nums[i] - nums[i - 1] == 1) {
                    curStreak++;
                } else {
                    longestStreak = Math.max(longestStreak, curStreak);
                    curStreak = 1;
                }
            }
        }

        return Math.max(longestStreak, curStreak);
    }

    public static void main(String[] args) {
        int[] arr = {2147483646, -2147483647, 0, 2, 2147483644, -2147483645, 2147483645};
        System.out.println(longestConsecutive(arr));
    }
}
