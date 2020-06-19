package com.lidong.algorithm.leetcode.medium.greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * 跳跃游戏（中等-55）
 * 中文链接：https://leetcode-cn.com/problems/jump-game/
 * <p>
 * 问题描述：
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 * <p>
 * 示例 1:
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * <p>
 * 示例 2:
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 *
 * @author Ls J
 * @date 2020/6/19 11:05 PM
 */
public class JumpGame55 {

    /**
     * 用于方法一缓存中间结果
     */
    private Map<Integer, Boolean> cache = new HashMap<>();

    /**
     * 方法一：dfs
     * 测试用例最后一个超出时间范围
     *
     * @param nums nums
     * @return true if success
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return false;
        }

        return dfs(0, n, nums);
    }

    /**
     * dfs
     *
     * @param k    当前所在的位置
     * @param n    数组长度
     * @param nums nums 数组
     * @return true if success
     */
    private boolean dfs(int k, int n, int[] nums) {
        if (k >= n - 1) {
            return true;
        }
        if (nums[k] == 0) {
            return false;
        }
        boolean res = false;
        // 枚举所有可以跳跃的步数
        for (int i = 1; i <= nums[k]; ++i) {
            int curIdx = k + i;
            if (cache.containsKey(curIdx)) {
                res = res || cache.get(curIdx);
                continue;
            }
            boolean flag = dfs(k + i, n, nums);
            cache.put(curIdx, flag);
            res = res || flag;
        }
        return res;
    }

    /**
     * 方法二：贪心算法
     * <p>
     * 执行用时：2 ms，在所有 Java 提交中击败了 74.59% 的用户
     * 内存消耗：41.8 MB，在所有 Java 提交中击败了 12.50% 的用户
     *
     * @param nums nums
     * @return true if success
     */
    public boolean canJump2(int[] nums) {
        int n = nums.length;

        // 最原可达位置
        int rightMost = 0;

        for (int i = 0; i < n; ++i) {
            if (i <= rightMost) {
                // 更新最远可以到达的位置
                rightMost = Math.max(rightMost, i + nums[i]);
                if (rightMost >= n - 1) {
                    return true;
                }
            } else {
                return false;
            }
        }

        return false;
    }
}
