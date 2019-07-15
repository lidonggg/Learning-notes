package com.lidong.algorithm.leetcode.easy;

/**
 * @author ls J
 * @date 2019/7/15 1:37 AM
 * 反转字符串（简单-344）
 * 问题描述：
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 */
public class ReverseString344 {

    public void reverseString(char[] s) {
        char tmp;
        for (int i = 0; i < s.length / 2; ++i) {
            tmp = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = tmp;
        }
    }

    /**
     * 使用三次异或来交换两个值
     *
     * @param s s
     */
    public void reverseString2(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            s[i] ^= s[s.length - 1 - i];
            s[s.length - 1 - i] ^= s[i];
            s[i] ^= s[s.length - 1 - i];
        }
    }
}
