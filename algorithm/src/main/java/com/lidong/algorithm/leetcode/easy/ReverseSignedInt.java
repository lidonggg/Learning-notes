package com.lidong.algorithm.leetcode.easy;

/**
 * @author Ls J
 * @date 2019/6/26 4:18 PM
 *
 * 整数反转（简单）
 * 问题描述：给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 示例：
 * 123 --> 321
 * -123 --> -321
 * 120 --> 21
 */
public class ReverseSignedInt {

    /**
     * 我们可以一次构建反转整数的一位数字。在这样做的时候，我们可以预先检查向原整数附加另一位数字是否会导致溢出。
     *
     * @param x 原数字
     * @return 输出结果
     */
    private static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(120));
    }
}
