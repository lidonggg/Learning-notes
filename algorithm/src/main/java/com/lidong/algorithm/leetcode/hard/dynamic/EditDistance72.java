package com.lidong.algorithm.leetcode.hard.dynamic;

import org.apache.commons.lang3.StringUtils;

/**
 * 编辑距离（困难-72）
 * 中文地址：https://leetcode-cn.com/problems/edit-distance/
 * <p>
 * 问题描述：
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * - 插入一个字符
 * - 删除一个字符
 * - 替换一个字符
 *  
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * <p>
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * @author Ls J
 * @date 2020/6/13 11:22 AM
 */
public class EditDistance72 {

    /**
     * 动态规划
     * <p>
     * 执行用时：7 ms，在所有 Java 提交中击败了 54.17% 的用户
     * 内存消耗：39.8 MB，在所有 Java 提交中击败了 5.00% 的用户
     *
     * @param word1 word1
     * @param word2 word2
     * @return minDistance
     */
    public static int minDistance(String word1, String word2) {
        if (StringUtils.isBlank(word1) && StringUtils.isBlank(word2)) {
            return 0;
        }
        if (StringUtils.isBlank(word1)) {
            return word2.length();
        }
        if (StringUtils.isBlank(word2)) {
            return word1.length();
        }
        int len1 = word1.length(), len2 = word2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        // 边界处理
        // 第一个字符串前 i 个字符变成与第二个前 0 个字符相等
        // 那么需要删除前 i 个字符
        for (int i = 0; i <= len1; ++i) {
            dp[i][0] = i;
        }
        // 边界处理
        // 第一个字符串前 0 个字符变成与第二个前 i 个字符相等
        // 那么需要添加前 i 个字符
        for (int i = 0; i <= len2; ++i) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= len1; ++i) {
            for (int j = 1; j <= len2; ++j) {
                // dp[i-1][j] -> dp[i][j]，第一个字符串添加一个字符
                // dp[i][j-1] -> dp[i][j]，第一个字符串减一个字符
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                // dp[i - 1][j - 1] -> dp[i][j]，如果第 i-1 个字符不同，才需要 +1（替换）
                int repDown = dp[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    repDown += 1;
                }
                dp[i][j] = Math.min(dp[i][j], repDown);
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        System.out.println(minDistance(word1, word2));
    }
}
