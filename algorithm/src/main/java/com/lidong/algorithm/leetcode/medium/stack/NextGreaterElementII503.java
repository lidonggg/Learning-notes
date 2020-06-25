package com.lidong.algorithm.leetcode.medium.stack;

import java.util.Stack;

/**
 * 下一个更大元素 II（中等-503）
 * 中文链接：https://leetcode-cn.com/problems/next-greater-element-ii/
 * <p>
 * 问题描述：
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * <p>
 * 示例 1:
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 注意: 输入数组的长度不会超过 10000。
 *
 * @author Ls J
 * @date 2020/6/25 2:58 PM
 */
public class NextGreaterElementII503 {

    /**
     * 单调递减栈
     *
     * 执行用时：8 ms，在所有 Java 提交中击败了 94.28% 的用户
     * 内存消耗：41.2 MB，在所有 Java 提交中击败了 14.29% 的用户
     *
     * @param nums nums
     * @return int[]
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        // 第一遍找到所有当前元素后面的第一个比它大的值
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                res[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        // 第二次遍历从前面找第一个比当前元素大的值
        for (int num : nums) {
            while (!stack.isEmpty() && nums[stack.peek()] < num) {
                res[stack.pop()] = num;
            }
        }

        // 剩下的元素都是找不到比它更大的值的元素
        while (!stack.isEmpty()) {
            res[stack.pop()] = -1;
        }

        return res;
    }
}
