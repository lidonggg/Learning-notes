package com.lidong.algorithm.leetcode.middling;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ls J
 * @date 2019/7/15 9:55 AM
 * 子集（中等-78）
 * 问题描述：
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class Subsets78 {

    /**
     * 结果集
     */
    private List<List<Integer>> result = new LinkedList<>();

    /**
     * 原始数组
     */
    private int[] nums;

    /**
     * 集合的每个元素，都有可以选或不选，可以用二进制和位运算
     *
     * @param nums nums
     * @return list
     */
    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        return preDfs(0, new LinkedList<>());
    }

    private static List<List<Integer>> binarySolution(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < (1 << nums.length); i++) {
            List<Integer> sub = new LinkedList<>();
            for (int j = 0; j < nums.length; j++) {
                if (((i >> j) & 1) == 1) {
                    sub.add(nums[j]);
                }
            }

            res.add(sub);
        }
        return res;
    }

    /**
     * dfs前序，每次针对当前的元素都有两种情况：插入或者不插入
     *
     * @param i      当前遍历到数组下标
     * @param subset 当前元组
     * @return res
     */
    private List<List<Integer>> preDfs(int i, LinkedList<Integer> subset) {
        if (i >= nums.length) {
            return result;
        }

        subset = new LinkedList<>(subset);

        result.add(subset);
        preDfs(i + 1, subset);
        subset.add(nums[i]);
        preDfs(i + 1, subset);

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Subsets78 subsets78 = new Subsets78();
        System.out.print(subsets78.subsets(nums));
    }
}
