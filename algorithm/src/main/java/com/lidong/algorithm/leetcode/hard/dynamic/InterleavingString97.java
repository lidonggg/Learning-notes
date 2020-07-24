package com.lidong.algorithm.leetcode.hard.dynamic;

/**
 * 交错字符串（困难-97）
 * 中文链接：https://leetcode-cn.com/problems/interleaving-string
 * <p>
 * 问题描述：
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。 
 * <p>
 * 示例 1：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 *
 * @author ls J
 * @date 2020/7/23 14:15
 */
public class InterleavingString97 {

    private String s1, s2, s3;

    private int n1, n2, n3;

    /**
     * 方法一：dfs
     * 思路很简单，就是 s3 的当前字符依次与 s1 和 s2 当前字符去匹配，每个字符都有两个分支，按照这个思路进行 dfs 就行
     * 可以通过缓存去对时间复杂度进行一些优化
     * <p>
     * 执行用时：1743 ms，在所有 Java 提交中击败了 6.17% 的用户
     * 内存消耗：37.9 MB，在所有 Java 提交中击败了 14.29% 的用户
     *
     * @param s1 s1
     * @param s2 s2
     * @param s3 s3
     * @return true / false
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int n1 = s1.length(), n2 = s2.length(), n3 = s3.length();
        if (n3 != n1 + n2) {
            return false;
        }

        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        return dfs(0, 0, 0);
    }

    /**
     * dfs
     *
     * @param idx1 idx1
     * @param idx2 idx2
     * @param idx3 idx3
     * @return true / false
     */
    private boolean dfs(int idx1, int idx2, int idx3) {
        // 匹配结束
        if (idx3 == n3) {
            return true;
        }

        char c3 = s3.charAt(idx3);
        boolean res = false;
        // 如果 s1 当前位置与 s3 当前位置匹配，那么我们可以尝试将 c3 看成是 s1 的一个字符，然后去匹配下一个
        if (idx1 < n1 && c3 == s1.charAt(idx1)) {
            res = dfs(idx1 + 1, idx2, idx3 + 1);
        }
        // 如果匹配成功了，直接返回就行
        if (res) {
            return true;
        }
        // s2 同理
        if (idx2 < n2 && c3 == s2.charAt(idx2)) {
            return dfs(idx1, idx2 + 1, idx3 + 1);
        }
        // 当前位置与 s1、s2 当前位置都不匹配，返回 false
        return false;
    }

    /**
     * 方法二：动态规划 -- 二维数组
     * <p>
     * 执行用时：5 ms，在所有 Java 提交中击败了 58.33% 的用户
     * 内存消耗：37.9 MB，在所有 Java 提交中击败了 14.29% 的用户
     * <p>
     * n - s1 长度，m - s2 长度
     * 时间复杂度：O(mn)
     * 空间复杂度：O(mn)
     *
     * @param s1 s1
     * @param s2 s2
     * @param s3 s3
     * @return true / false
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();

        if (n + m != t) {
            return false;
        }

        boolean[][] dp = new boolean[n + 1][m + 1];
        // 第 0 个字母初始化为 true
        dp[0][0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                // 找到 s3 要匹配的相应的位置
                int p = i + j - 1;
                // 拿 s1 对应位置与 s3 进行匹配
                if (i > 0) {
                    dp[i][j] = dp[i][j] || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                // 拿 s2 对应位置与 s3 进行匹配
                if (j > 0) {
                    dp[i][j] = dp[i][j] || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return dp[n][m];
    }

    /**
     * 方法三：动态规划 -- 一维数组
     * 方法二中的 dp 数组的第 i 行只与第 i - 1 行有关系，所以这里可以使用一个一维的滚动数组
     * <p>
     * 执行用时：4 ms，在所有 Java 提交中击败了 71.10% 的用户
     * 内存消耗：37.9 MB，在所有 Java 提交中击败了 14.29% 的用户
     * <p>
     * n - s1 长度，m - s2 长度
     * 时间复杂度：O(mn)
     * 空间复杂度：O(m)
     *
     * @param s1 s1
     * @param s2 s2
     * @param s3 s3
     * @return true / false
     */
    public boolean isInterleave3(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();

        if (n + m != t) {
            return false;
        }

        boolean[] dp = new boolean[m + 1];

        dp[0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(p);
                }
                if (j > 0) {
                    dp[j] = dp[j] || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return dp[m];
    }
}
