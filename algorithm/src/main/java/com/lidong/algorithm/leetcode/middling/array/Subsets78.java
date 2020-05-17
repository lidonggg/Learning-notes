package com.lidong.algorithm.leetcode.middling.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 子集（中等-78）
 * 中文链接：https://leetcode-cn.com/problems/subsets
 * <p>
 * 问题描述：
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * <p>  [3],
 * <p>  [1],
 * <p>  [2],
 * <p>  [1,2,3],
 * <p>  [1,3],
 * <p>  [2,3],
 * <p>  [1,2],
 * <p>  []
 * ]
 *
 * @author Ls J
 * @date 2020/5/16 10:54 PM
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
     * 方法一：递归
     *
     * @param nums nums
     * @return list
     */
    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        result.add(new ArrayList<>());
        return helper(0, new LinkedList<>());
    }

    /**
     * 每次针对当前的元素都有两种情况：插入或者不插入
     *
     * @param i      当前遍历到数组下标
     * @param subset 当前元组
     * @return res
     */
    private List<List<Integer>> helper(int i, LinkedList<Integer> subset) {
        if (i >= nums.length) {
            return result;
        }

        subset = new LinkedList<>(subset);
        // 当前列表先保存到结果列表中
        result.add(subset);
        // 继续递归
        helper(i + 1, subset);
        // 添加下一个元素
        subset.add(nums[i]);
        // 继续递归
        helper(i + 1, subset);

        return result;
    }

    /**
     * 方法二：利用二进制位掩码
     *
     * @param nums num arr
     * @return res
     */
    public List<List<Integer>> subsets2(int[] nums) {
        for (int i = 0; i < (1 << nums.length); i++) {
            List<Integer> sub = new LinkedList<>();
            for (int j = 0; j < nums.length; j++) {
                if (((i >> j) & 1) == 1) {
                    sub.add(nums[j]);
                }
            }

            result.add(sub);
        }
        return result;
    }

    /**
     * 方法三：利用二进制位掩码
     * 来自官方题解：https://leetcode-cn.com/problems/subsets/solution/zi-ji-by-leetcode/
     *
     * @param nums num arr
     * @return res
     */
    public List<List<Integer>> subsets3(int[] nums) {
        int n = nums.length;

        for (int i = (int) Math.pow(2, n); i < (int) Math.pow(2, n + 1); ++i) {
            // 从 00..0 到 11..1 构造二进制位
            // 如果当前位是 0，代表存放当前数字，否则相反
            String bitmask = Integer.toBinaryString(i).substring(1);

            // 通过二进位掩码构造当前数组
            List<Integer> curr = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                if (bitmask.charAt(j) == '1') {
                    curr.add(nums[j]);
                }
            }
            result.add(curr);
        }
        return result;
    }
}
