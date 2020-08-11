package com.lidong.algorithm.leetcode.easy.bitmanipulate;

/**
 * 两整数之和（简单-371）
 * 中文链接：https://leetcode-cn.com/problems/sum-of-two-integers
 * <p>
 * 问题描述：
 * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
 * <p>
 * 示例 1:
 * 输入: a = 1, b = 2
 * 输出: 3
 * <p>
 * 示例 2:
 * 输入: a = -2, b = 3
 * 输出: 1
 *
 * @author Ls J
 * @date 8/12/20 12:23 AM
 */
public class SumOfTwoIntegers371 {

    /**
     * 二进制运算与十进制类似
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：36.5 MB，在所有 Java 提交中击败了 22.78% 的用户
     *
     * @param a a
     * @param b b
     * @return sum
     */
    public int getSum(int a, int b) {
        int res = 0;
        int left = -1;
        while (left != 0) {
            // 计算低位
            res = a ^ b;
            // 计算进位
            left = a & b;
            a = res;
            b = left << 1;
        }
        return res;
    }
}
