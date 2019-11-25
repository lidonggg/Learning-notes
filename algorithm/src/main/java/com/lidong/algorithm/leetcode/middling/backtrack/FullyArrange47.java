package com.lidong.algorithm.leetcode.middling.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author dlif
 * @version 2019/6/26 10:35
 * <p>
 * 有重复元素的数组全排列（中等-47）
 * 问题描述：给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 示例：
 * 输入：[1,1,2]
 * 输出：
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 */
public class FullyArrange47 {

    /**
     * 存储结果列表
     */
    private List<List<Integer>> res = new ArrayList<>();

    /**
     * 标记，存放某个位置上的元素的使用状态，false 表示未使用，这里与排序后每个元素的位置一一对应
     */
    private boolean[] used;

    private void findPermuteUnique(int[] nums, int depth, Stack<Integer> stack) {
        if (depth == nums.length) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // 因为排序以后重复的数一定不会出现在开始，故 i > 0
                // 和之前的数相等，并且之前的数还未使用过，只有出现这种情况，才会出现相同分支
                // 这种情况跳过即可
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }
                used[i] = true;
                // 当前元素入栈
                stack.add(nums[i]);
                // 当前元素入栈的情况下，继续往后
                findPermuteUnique(nums, depth + 1, stack);
                // 当前元素出栈，并且将当前元素标记为未使用
                stack.pop();
                used[i] = false;
            }
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return res;
        }
        // 首先排序，这样如果有元素值相同，则它们一定是相邻的两个元素，这样的话利于去重
        // 这里的排序算法可以使用比较高效的排序算法
        Arrays.sort(nums);

        used = new boolean[len];
        findPermuteUnique(nums, 0, new Stack<>());
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3};
        System.out.println(new FullyArrange47().permuteUnique(nums));
    }
}
