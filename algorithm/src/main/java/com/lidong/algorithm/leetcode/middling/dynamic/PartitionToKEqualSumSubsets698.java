package com.lidong.algorithm.leetcode.middling.dynamic;

import java.util.Arrays;

/**
 * 划分为k个相等的子集
 * 中文链接：https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets/
 * <p>
 * 问题描述：
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 * <p>
 * 示例 1：
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 *  
 * 提示：
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 *
 * @author ls J
 * @date 2020/5/27 9:47
 */
public class PartitionToKEqualSumSubsets698 {

    /**
     * 方法一：动态规划
     * 来自 leetcode 官方题解：https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets/solution/hua-fen-wei-kge-xiang-deng-de-zi-ji-by-leetcode/
     *
     * @param nums nums
     * @param k    k
     * @return true if success
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int len;
        if ((len = nums.length) < k || len == 0 || k == 0) {
            return false;
        }
        Arrays.sort(nums);
        int sum = Arrays.stream(nums).sum();
        int subSum = sum / k;
        if (sum % k != 0 || nums[len - 1] > subSum) {
            return false;
        }

        boolean[] dp = new boolean[1 << len];
        dp[0] = true;
        int[] total = new int[1 << len];

        for (int state = 0; state < (1 << len); state++) {
            if (!dp[state]) {
                continue;
            }
            for (int i = 0; i < len; i++) {
                int future = state | (1 << i);
                if (state != future && !dp[future]) {
                    if (nums[i] <= subSum - (total[state] % subSum)) {
                        dp[future] = true;
                        total[future] = total[state] + nums[i];
                    } else {
                        break;
                    }
                }
            }
        }
        return dp[(1 << len) - 1];
    }

    /**
     * 方法二：回溯
     * 模拟构造 k 个子集
     * <p>
     * 执行用时：3 ms，在所有 Java 提交中击败了 63.03% 的用户
     * 内存消耗：37.6 MB，在所有 Java 提交中击败了 9.09% 的用户
     *
     * @param nums nums
     * @param k    k
     * @return true if success
     */
    public boolean canPartitionKSubsets2(int[] nums, int k) {
        int len;
        if ((len = nums.length) < k || len == 0 || k == 0) {
            return false;
        }
        Arrays.sort(nums);
        int sum = Arrays.stream(nums).sum();
        int subSum = sum / k;
        if (sum % k != 0 || nums[len - 1] > subSum) {
            return false;
        }

        int idx = len - 1;
        while (idx >= 0 && nums[idx] == subSum) {
            k--;
            idx--;
        }
        return doSearch(nums, idx, subSum, new int[k], k);
    }

    /**
     * 回溯
     *
     * 对于 nums 中的每个数字，我们可以将其添加到 k 个子集中的一个，只要该子集的和不会超过目标值。
     * 对于每一个选择，我们都递归地用一个更小的数字进行搜索，以便在 nums 中考虑。
     * 如果我们成功地放置了每个数字，那么我们的搜索就成功了。
     *
     * @param nums   nums[]
     * @param idx    curIdx
     * @param subSum subSum
     * @param groups groups[]
     * @param k      k
     * @return true if success
     */
    private boolean doSearch(int[] nums, int idx, int subSum, int[] groups, int k) {
        if (idx < 0) {
            return true;
        }
        int curNum = nums[idx--];
        for (int i = 0; i < k; ++i) {
            if (groups[i] + curNum <= subSum) {
                groups[i] += curNum;
                if (doSearch(nums, idx, subSum, groups, k)) {
                    return true;
                }
                groups[i] -= curNum;
            }
            // 确保每个 group 的所有 0 值都出现在数组 groups 的末尾
            if (groups[i] == 0) {
                break;
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
