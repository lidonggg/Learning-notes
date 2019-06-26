package com.lidong.algorithm.leetcode.middling;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ls J
 * @date 2019/6/26 10:30 AM
 *
 * 数组全排列（中等）
 * 问题描述：给定一个没有重复数字的序列，返回其所有可能的全排列。
 * 示例：
 * 输入：[1,2,3]
 * 输出：
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 *
 * 效率一般，内存消耗一般
 */
public class FullyArrange {

    /**
     * 解决方案
     *
     * @param nums 输入数组
     * @return 返回结果
     */
    private static List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        int[] used = new int[len];
        List<Integer> curList = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        recursive(nums, len, used, curList, result);
        return result;
    }

    /**
     * 通过递归来进行解决，每次递归找出一个结果
     *
     * @param nums    原数组
     * @param len     原数组长度
     * @param used    0--未使用，1--已使用
     * @param curList 当前操作的链表
     * @param result  返回结果
     */
    private static void recursive(int[] nums, int len, int[] used, List<Integer> curList, List<List<Integer>> result) {
        if (curList.size() == len) {
            result.add(new ArrayList<>(curList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 0) {
                curList.add(nums[i]);
                used[i] = 1;
                recursive(nums, len, used, curList, result);
                used[i] = 0;
                curList.remove(curList.size() - 1);
            }
        }

    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(permute(nums));
    }
}
