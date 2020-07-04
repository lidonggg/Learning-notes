package com.lidong.algorithm.leetcode.hard.dynamic;

/**
 * 通配符匹配（困难-44）
 * 中文链接：https://leetcode-cn.com/problems/wildcard-matching/
 * <p>
 * 问题描述：
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * - '?' 可以匹配任何单个字符。
 * - '*' 可以匹配任意字符串（包括空字符串）。
 * - 两个字符串完全匹配才算匹配成功。
 * <p>
 * 说明:
 * - s 可能为空，且只包含从 a-z 的小写字母。
 * - p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * <p>
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * <p>
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * <p>
 * 示例 3:
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * <p>
 * 示例 4:
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce"。
 * <p>
 * 示例 5:
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 *
 * @author Ls J
 * @date 2020/7/5 1:24 AM
 */
public class WildcardMatching44 {

    private String s;

    private int sn;

    private String p;

    private int pn;

    /**
     * 用于剪枝
     */
    private Boolean[][] touched;

    /**
     * 方法一：dfs + 剪枝
     * <p>
     * 执行用时：22 ms，在所有 Java 提交中击败了 67.20% 的用户
     * 内存消耗：42.4 MB，在所有 Java 提交中击败了 7.14% 的用户
     * <p>
     * 时间复杂度：O(mn)
     * *空间复杂度：O(mn)
     *
     * @param s s
     * @param p p
     * @return true / false
     */
    public boolean isMatch(String s, String p) {
        this.s = s;
        this.sn = s.length();
        this.p = p;
        this.pn = p.length();
        this.touched = new Boolean[sn][pn];
        return dfs(0, 0);
    }

    private boolean dfs(int sIdx, int pIdx) {
        boolean res = false;
        if (sIdx < sn) {
            if (pIdx < pn) {
                if (null != touched[sIdx][pIdx]) {
                    return touched[sIdx][pIdx];
                }
                // 如果当前 s 中的字符与 p 中的字符相同或者 p 中的字符为 ？，那么直接去匹配下一位
                if (s.charAt(sIdx) == p.charAt(pIdx) || p.charAt(pIdx) == '?') {
                    res = dfs(sIdx + 1, pIdx + 1);
                } else if (p.charAt(pIdx) == '*') {
                    // 如果 p 是 *，那么此时有两种选择：
                    // 1. * 看作空字符串，那么需要拿 p 的下一位与 s 的当前位匹配
                    // 2. * 匹配一位 s[sIdx]，由于 * 可以匹配任意多字符，因此此时 pIdx 不需要后移
                    res = dfs(sIdx, pIdx + 1) || dfs(sIdx + 1, pIdx);
                }
                touched[sIdx][pIdx] = res;
                return res;
            }
            return res;
        }
        // 如果 s 匹配到最后了
        else {
            if (pIdx >= pn) {
                return true;
            }
            // 需要判断 p 剩下的是不是全是 *，如果不是，则匹配失败
            int i = pIdx;
            while (i < pn && p.charAt(i) == '*') {
                i++;
            }
            return i == pn;
        }
    }

    /**
     * 方法二：动态规划
     * <p>
     * 执行用时：35 ms，在所有 Java 提交中击败了 50.42% 的用户
     * 内存消耗：39.9 MB，在所有 Java 提交中击败了 53.57% 的用户
     * <p>
     * 时间复杂度：O(mn)
     * 空间复杂度：O(mn)
     *
     * @param s s
     * @param p p
     * @return true / false
     */
    public boolean isMatch2(String s, String p) {
        int sn = s.length(), pn = p.length();
        boolean[][] dp = new boolean[sn + 1][pn + 1];
        dp[0][0] = true;
        // 如果 p 以 * 开头，那么可以进行初始化 s[0] 始终匹配成功
        for (int i = 1; i <= pn; ++i) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= sn; ++i) {
            for (int j = 1; j <= pn; ++j) {
                // 如果 p 当前位置 (j - 1) 是 *，那么此时有两种情况转换过来：
                // 1. s[i-1] 匹配 p[j-2]
                // 2. s[i-2] 匹配 p[j-1] (*)，则 s[i-1] 肯定也可以匹配 *
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[sn][pn];
    }
}
