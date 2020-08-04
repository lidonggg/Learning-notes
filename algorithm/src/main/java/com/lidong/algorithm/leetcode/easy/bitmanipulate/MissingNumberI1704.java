package com.lidong.algorithm.leetcode.easy.bitmanipulate;

/**
 * 消失的数字（简单-面试题17.04）
 * 中文链接：https://leetcode-cn.com/problems/missing-number-lcci
 * <p>
 * 问题描述：
 * 数组 nums 包含从 0 到 n 的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在 O(n) 时间内完成吗？
 * 注意：本题相对书上原题稍作改动
 * <p>
 * 示例 1：
 * 输入：[3,0,1]
 * 输出：2
 *  
 * 示例 2：
 * 输入：[9,6,4,2,3,5,7,0,1]
 * 输出：8
 *
 * @author Ls J
 * @date 2020/8/4 10:14 PM
 */
public class MissingNumberI1704 {

    /**
     * 异或性质：
     * res = res ^ x ^ x
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40.5 MB，在所有 Java 提交中击败了 20.31% 的用户
     *
     * @param nums nums
     * @return num
     */
    public int missingNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            res ^= i;
            res ^= nums[i];
        }
        res ^= nums.length;
        return res;
    }
}
