package com.lidong.algorithm.leetcode.medium.greedy;

/**
 * 最长上升子序列（中等-300）
 * 中文链接：https://leetcode-cn.com/problems/longest-increasing-subsequence/
 * <p>
 * 问题描述：
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * <p>
 * 说明:
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 * @author Ls J
 * @date 2020/6/20 2:30 PM
 */
public class LongestIncreasingSubsequence300 {

    /**
     * 动态规划
     * <p>
     * 状态转移方程：
     * dp[i] = max(dp[j]) + 1，其中 j < i，且 nums[j] < nums[i]
     * <p>
     * 执行用时：14 ms，在所有 Java 提交中击败了 56.00% 的用户
     * 内存消耗：37.6 MB，在所有 Java 提交中击败了 7.14% 的用户
     * <p>
     * 时间复杂度：O(n^2)
     *
     * @param nums nums
     * @return max
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;

        if (n == 0) {
            return 0;
        }

        int[] dp = new int[n];
        dp[0] = 1;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; --j) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(dp[i], res);
        }

        return res;
    }

    /**
     * 方法二：贪心+二分查找
     * 来自 leetcode 官方题解：https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-by-leetcode-soluti/
     * 如果我们要使上升子序列尽可能的长，则我们需要让序列上升得尽可能慢，因此我们希望每次在上升子序列最后加上的那个数尽可能的小。
     * 基于上面的贪心思路，我们维护一个数组 d[i] ，表示长度为 i 的最长上升子序列的末尾元素的最小值，
     * 用 len 记录目前最长上升子序列的长度，起始时 len 为 1，d[1] = nums[0]。
     *
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：37.8 MB，在所有 Java 提交中击败了 7.14% 的用户
     *
     * @param nums nums
     * @return max
     */
    public int lengthOfLIS2(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        // d[i] 表示长度为 i 的最长上升子序列的末尾元素的最小值
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                // 二分查找 d[1..len]，找到满足 d[i−1] < nums[j] < d[i] 的 i
                int l = 1, r = len, pos = 0;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }

}
