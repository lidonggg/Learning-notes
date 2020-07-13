package com.lidong.algorithm.leetcode.medium.array;

/**
 * 删掉一个元素以后全为 1 的最长子数组（中等-1493）
 * 中文链接：https://leetcode-cn.com/problems/longest-subarray-of-1s-after-deleting-one-element
 * <p>
 * 问题描述：
 * 给你一个二进制数组 nums ，你需要从中删掉一个元素。
 * 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
 * 如果不存在这样的子数组，请返回 0 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,0,1]
 * 输出：3
 * 解释：删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,1,1,0,1,1,0,1]
 * 输出：5
 * 解释：删掉位置 4 的数字后，[0,1,1,1,1,1,0,1] 的最长全 1 子数组为 [1,1,1,1,1] 。
 * <p>
 * 示例 3：
 * 输入：nums = [1,1,1]
 * 输出：2
 * 解释：你必须要删除一个元素。
 * <p>
 * 示例 4：
 * 输入：nums = [1,1,0,0,1,1,1,0,1]
 * 输出：4
 * <p>
 * 示例 5：
 * 输入：nums = [0,0,0]
 * 输出：0
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * nums[i] 要么是 0 要么是 1 。
 *
 * @author Ls J
 * @date 2020/7/14 1:01 AM
 */
public class LongestSubarrayOf1AfterDeletingOneElement1493 {

    /**
     * 方法一：前缀和后缀连续一之和
     * <p>
     * 执行用时：4 ms，在所有 Java 提交中击败了 28.53% 的用户
     * 内存消耗：47.7 MB，在所有 Java 提交中击败了 100.00% 的用户
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param nums nums
     * @return int
     */
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        // 保存当前元素的前缀后后缀连续 1 的个数
        int[] pre = new int[n];
        int[] suf = new int[n];

        pre[0] = nums[0];
        for (int i = 1; i < n; ++i) {
            pre[i] = nums[i] != 0 ? pre[i - 1] + 1 : 0;
        }

        suf[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            suf[i] = nums[i] != 0 ? suf[i + 1] + 1 : 0;
        }

        int res = 0;
        for (int i = 0; i < n; ++i) {
            int preSum = i == 0 ? 0 : pre[i - 1];
            int sufSum = i == n - 1 ? 0 : suf[i + 1];
            res = Math.max(res, preSum + sufSum);
        }

        return res;
    }

    /**
     * 方法二：迭代优化，思想与方法一类似，只是变成了常数级别的空间占用
     * <p>
     * 执行用时：3 ms，在所有 Java 提交中击败了 63.46% 的用户
     * 内存消耗：47.7 MB，在所有 Java 提交中击败了 100.00% 的用户
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums nums
     * @return int
     */
    public int longestSubarray2(int[] nums) {
        int n = nums.length;
        int res = 0;
        // p0[i] -- 以第 i 位结尾的最长连续全 1 子数组
        // p1[i] -- 以第 i 位结尾，并且可以在某个位置删除一个 0 的最长连续全 1 子数组
        // 当遇到 0 时，由于 p1[i] 允许删除一个 0，那么我们可以把这个 0 删除，将 p0[i - 1] 的值赋给 p1[i]
        int p0 = 0, p1 = 0;

        for (int num : nums) {
            if (num == 1) {
                ++p0;
                ++p1;
            } else {
                p1 = p0;
                p0 = 0;
            }
            res = Math.max(res, p1);
        }
        // 如果全是 1，也需要删除一个元素
        if (res == n) {
            --res;
        }
        return res;
    }
}
