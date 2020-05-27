package com.lidong.algorithm.leetcode.middling.dynamic;

/**
 * 回文子串（中等-647）
 * 中文链接：https://leetcode-cn.com/problems/palindromic-substrings/
 * <p>
 * 问题描述：
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 * <p>
 * 示例 1:
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 * <p>
 * 示例 2:
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 * <p>
 * 注意:
 * 输入的字符串长度不会超过1000。
 *
 * @author ls J
 * @date 2020/5/27 14:36
 */
public class PalindromicSubstrings647 {

    /**
     * 方法一：遍历以每个元素为中心的回文串，有 2*len-1 个中心
     * <p>
     * 执行用时：2 ms，在所有 Java 提交中击败了 98.53% 的用户
     * 内存消耗：37.6 MB，在所有 Java 提交中击败了 5.55% 的用户
     *
     * @param s s
     * @return res
     */
    public static int countSubstrings(String s) {
        int len;
        if ((len = s.length()) == 0) {
            return 0;
        }

        int res = 0;
        char[] chars = s.toCharArray();
        // 找以每个元素为中心的回文子串，总共有 2*len-1 个中心（数组中的每个元素以及相邻两个元素的中心）
        for (int i = 0; i < len * 2 - 1; ++i) {
            // 如果 i % 2 == 0，left = right
            int left = i / 2;
            int right = left + i % 2;
            while (left >= 0 && right < len && chars[left] == chars[right]) {
                res++;
                left--;
                right++;
            }
        }
        return res;
    }

    /**
     * 方法二：动态规划
     * dp[i][j] = true 代表 s[i...j] 是回文子串
     * <p>
     * 执行用时：15 ms，在所有 Java 提交中击败了 29.97% 的用户
     * 内存消耗：40.3 MB，在所有 Java 提交中击败了 5.55% 的用户
     *
     * @param s s
     * @return res
     */
    public static int countSubstrings2(String s) {
        int len;
        if ((len = s.length()) == 0) {
            return 0;
        }
        boolean[][] dp = new boolean[len][len];
        int res = s.length();
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // 两个元素是相邻的
                    if (j - i == 1) {
                        dp[i][j] = true;
                    } else {
                        // 否则由这两个元素之间的子串是否为回文子串决定
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j]) {
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(countSubstrings2("aaa"));
    }
}
