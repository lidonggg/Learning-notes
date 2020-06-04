package com.lidong.algorithm.leetcode.medium.dynamic;

/**
 * 删除与获得点数（中等-740）
 * 中文链接：https://leetcode-cn.com/problems/delete-and-earn/
 * <p>
 * 问题描述：
 * 给定一个整数数组 nums ，你可以对它进行一些操作。
 * 每次操作中，选择任意一个 nums[i]，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * <p>
 * 示例 1:
 * 输入: nums = [3, 4, 2]
 * 输出: 6
 * 解释:
 * 删除 4 来获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 来获得 2 个点数。总共获得 6 个点数。
 * <p>
 * 示例 2:
 * 输入: nums = [2, 2, 3, 3, 3, 4]
 * 输出: 9
 * 解释:
 * 删除 3 来获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 * <p>
 * 注意:
 * nums 的长度最大为 20000。
 * 每个整数 nums[i] 的大小都在 [1, 10000] 范围内。
 *
 * @author ls J
 * @date 2020/5/28 16:29
 */
public class DeleteAndEarn740 {

    /**
     * 动态规划：
     * 首先计算每个数字出现的总和，然后按照从小到大对数字排序
     * 为了减少排序复杂度，这里用了计数排序的思想，由于 nums[i] 最大值不超过 10000，因此即时中间会出现很多 0 值，空间消耗也不会特别高
     * 如果要 delete 某个数 i，那么所有的 i-1 和 i+1 都会被删除，也就是删除了 sum[i-1] + sum[i+1]
     * 因此在 delete i 的时候，我们可以发现把所有的 i 都 delete 掉能使得到的总和尽可能高
     * 状态转移方程：
     * dp[i] = Math.max(dp[i - 1], dp[i - 2] + sums[i])，dp[i] 表示到 i 为止，所得到的数的最大值
     * <p>
     * 执行用时：2 ms，在所有 Java 提交中击败了 93.45% 的用户
     * 内存消耗：39.5 MB，在所有 Java 提交中击败了 50.00% 的用户
     *
     * @param nums num arr
     * @return max earn
     */
    public static int deleteAndEarn(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int max = 1;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] sums = new int[max + 1];
        // 统计每个数字的总和，因为如果要减的话，肯定是所有该数字都会被减
        for (int num : nums) {
            sums[num] += num;
        }

        int[] dp = new int[max + 1];
        dp[1] = sums[1];
        for (int i = 2; i <= max; ++i) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + sums[i]);
        }

        return dp[max];
    }

    public static void main(String[] args) {
        System.out.println(deleteAndEarn(new int[]{3, 4, 2}));
    }
}
