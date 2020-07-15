package com.lidong.algorithm.leetcode.medium.dynamic;

/**
 * 两个字符串的删除操作（中等-583）
 * 中文链接：https://leetcode-cn.com/problems/delete-operation-for-two-strings
 * <p>
 * 问题描述：
 * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 * <p>
 * 示例：
 * 输入: "sea", "eat"
 * 输出: 2
 * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
 *  
 * 提示：
 * 1.给定单词的长度不超过500。
 * 2.给定单词中的字符只含有小写字母。
 *
 * @author ls J
 * @date 2020/7/15 13:57
 */
public class DeleteOperationForTwoStrings583 {

    /**
     * 动态规划（二维数组）
     * 这题跟最小编辑距离很类似，只是操作只有删除一个，而且是在两个字符串上都可以进行
     * 定义状态 dp[i][j] 代表 word1[0~i-1] 与 word2[0~j-1]相同的时候所需要的最小删除次数
     * 那么如果 word1[i] == word2[j] 的话，dp[i+1][j+1] = dp[i][j]，此时不需要任何操作
     * 如果 word1[i] != word2[j]，此时删除第一个单词或者第二个单词对应位置的字符都可以，那么 dp[i+1][j+1] = min(dp[i-1][j], dp[i][j-1]) + 1，
     * <p>
     * 执行用时：9 ms，在所有 Java 提交中击败了 92.51% 的用户
     * 内存消耗：40.6 MB，在所有 Java 提交中击败了 33.33% 的用户
     *
     * @param word1 word1
     * @param word2 word2
     * @return min distance
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];
        // 边界初始化
        for (int i = 0; i <= m; ++i) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= n; ++i) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 方法二：最长公共前缀
     * 假设最长公共前缀为 lcs，那么删除的次数为 m + n - 2 * lcs，m、n 分别为两个字符串的长度。
     * 如果两个字符串完全不匹配（也就是两个字符串没有任何一个字符相同），那么总删除次数是 m + nm+n。
     * 如果两个字符串存在一个公共子序列，长度为 lcs，两个字符串我们都可以减少 lcs 次删除操作，也就是总共减少 2lcs 次删除操作，也就得到了上述等式。
     * <p>
     * 执行用时：73 ms，在所有 Java 提交中击败了 5.88% 的用户
     * 内存消耗：40.6 MB，在所有 Java 提交中击败了 33.33% 的用户
     * <p>
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(m*n)
     *
     * @param s1 s1
     * @param s2 s2
     * @return min distance
     */
    public int minDistance2(String s1, String s2) {
        int[][] memo = new int[s1.length() + 1][s2.length() + 1];
        return s1.length() + s2.length() - 2 * lcs(s1, s2, s1.length(), s2.length(), memo);
    }

    private int lcs(String s1, String s2, int m, int n, int[][] memo) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (memo[m][n] > 0) {
            return memo[m][n];
        }
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            memo[m][n] = 1 + lcs(s1, s2, m - 1, n - 1, memo);
        } else {
            memo[m][n] = Math.max(lcs(s1, s2, m, n - 1, memo), lcs(s1, s2, m - 1, n, memo));
        }
        return memo[m][n];
    }

}
