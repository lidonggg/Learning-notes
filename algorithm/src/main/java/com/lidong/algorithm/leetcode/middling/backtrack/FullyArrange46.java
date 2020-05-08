package com.lidong.algorithm.leetcode.middling.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
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
 * 效率一般，内存消耗一般
 *
 * @author dlif
 * @version 2019/6/26 10:30
 */
public class FullyArrange46 {

    /**
     * 存放结果列表
     */
    private List<List<Integer>> result;

    /**
     * 标记，存放某个位置上的元素的使用状态，false 表示未使用
     */
    private boolean[] used;

    /**
     * 解决方案
     *
     * @param nums 输入数组
     * @return 返回结果
     */
    private List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        used = new boolean[len];
        List<Integer> curList = new ArrayList<>();
        result = new ArrayList<>();
        recursive(nums, len, curList);
        return result;
    }

    /**
     * 通过递归来进行解决，每次递归找出一个结果
     *
     * @param nums    原数组
     * @param len     原数组长度
     * @param curList 当前操作的链表，这里可以使用栈来代替，比较方便些
     */
    private void recursive(int[] nums, int len, List<Integer> curList) {
        if (curList.size() == len) {
            result.add(new ArrayList<>(curList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // 当前元素入队列
                curList.add(nums[i]);
                used[i] = true;
                recursive(nums, len, curList);
                // 当前元素出队列
                curList.remove(curList.size() - 1);
                used[i] = false;
            }
        }

    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(new FullyArrange46().permute(nums));
    }
}
