package com.lidong.algorithm.leetcode.medium.dynamic;

/**
 * 最长回文子串（中等-5）
 * 中文链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * <p>
 * 问题描述：
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * <p>
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * @author ls J
 * @date 2020/7/20 13:36
 */
public class LongestPalindromicSubstring5 {

    /**
     * 方法一：动态规划
     * <p>
     * 执行用时：152 ms，在所有 Java 提交中击败了 22.95% 的用户
     * 内存消耗：42.4 MB，在所有 Java 提交中击败了 14.28% 的用户
     * <p>
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     * @param s s
     * @return res
     */
    public static String longestPalindrome(String s) {
        if (null == s || s.length() == 0) {
            return "";
        }
        int n = s.length();

        // dp[i][j] 代表 s[i..j] 是否为回文子串
        boolean[][] dp = new boolean[n][n];
        int start = 0, end = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j + i < n; ++j) {
                int k = i + j;
                // 只有一个字符
                if (i == 0) {
                    dp[j][k] = true;
                }
                // 只有两个字符
                else if (i == 1) {
                    dp[j][k] = s.charAt(j) == s.charAt(k);
                } else {
                    dp[j][k] = s.charAt(j) == s.charAt(k) && dp[j + 1][k - 1];
                }
                if (dp[j][k] && i + 1 > end - start) {
                    end = k;
                    start = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    public static void main(String[] args) {
        String s = "ababacccc";
        System.out.println(longestPalindrome(s));
    }

    /**
     * 方法二：中心扩展
     * <p>
     * 执行用时：27 ms，在所有 Java 提交中击败了 87.37% 的用户
     * 内存消耗：38.1 MB，在所有 Java 提交中击败了 25.89% 的用户
     * <p>
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     * @param s s
     * @return str
     */
    public static String longestPalindrome2(String s) {
        if (null == s || s.length() == 0) {
            return "";
        }
        int n = s.length();
        int start = 0, end = 0;

        for (int i = 0; i < n; ++i) {
            // 以当前字符作为中心，此时有奇数个字符
            int len1 = expandFromCenter(s, i, i, n);
            // 以相邻的两个字符作为中心，此时有偶数个字符
            int len2 = expandFromCenter(s, i, i + 1, n);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * 由中心向外扩展
     *
     * @param s s
     * @param l 左侧
     * @param r 右侧
     * @param n s.length()
     * @return max len
     */
    private static int expandFromCenter(String s, int l, int r, int n) {
        while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
            --l;
            ++r;
        }
        return r - l - 1;
    }
}
