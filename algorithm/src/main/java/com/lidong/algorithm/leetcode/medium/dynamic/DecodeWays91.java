package com.lidong.algorithm.leetcode.medium.dynamic;

/**
 * 解码方法（中等-91）
 * 中文链接：https://leetcode-cn.com/problems/decode-ways/
 * <p>
 * 问题描述：
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * <p>
 * 示例 2:
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 *
 * @author ls J
 * @date 2020/6/5 10:11
 */
public class DecodeWays91 {

    /**
     * 方法一：动态规划，一维 dp 数组
     * 思路都在注释中
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：37.9 MB，在所有 Java 提交中击败了 7.69% 的用户
     *
     * @param s string
     * @return 解码方法总数
     */
    public static int numDecodings(String s) {
        int len;
        if ((len = s.length()) == 0) {
            return 0;
        }
        if (s.charAt(0) == '0') {
            return 0;
        }
        // 操作整型数组，这样方便一些
        // 也可以直接在 string 上操作
        int[] source = new int[len + 1];
        for (int i = 0; i < len; ++i) {
            source[i + 1] = s.charAt(i) - 48;
        }
        // dp[i] 保存以 source[i] 结尾能生成的最多解码数
        int[] dp = new int[len + 1];
        // dp[0] 要初始化为 1
        // 或者也可以直接先初始化 dp[1] 和 dp[2]
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= len; ++i) {
            // 如果有 0，那么它必须是 >=10 的个位数，因此这里要去看一下 source[i - 1] 的值
            if (source[i] == 0 && (source[i - 1] > 2 || source[i - 1] == 0)) {
                return 0;
            }
            int sum = source[i - 1] * 10 + source[i];
            // 如果前一个数是 0 或者前一个数与当前数的和大于 26，那么它不能和当前数组合，所以 dp[i] = dp[i - 1]
            if (source[i - 1] == 0 || sum > 26) {
                dp[i] = dp[i - 1];
            } else if (source[i] != 0) {
                // 否则如果当前数字不是 0，那么它可以和前一个数组合也可以单独解码成英文字符
                dp[i] = dp[i - 2] + dp[i - 1];
            } else {
                // 如果当前数字是 0，那么它只能和前一个数字组合
                dp[i] = dp[i - 2];
            }
        }
        return dp[len];
    }

    /**
     * 方法二：动态规划：一维 dp 数组改成两个 int 类型变量
     * 思路都在注释中
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：37.9 MB，在所有 Java 提交中击败了 7.69% 的用户
     *
     * @param s string
     * @return 解码方法总数
     */
    public static int numDecodings2(String s) {
        int len;
        if ((len = s.length()) == 0) {
            return 0;
        }
        if (s.charAt(0) == '0') {
            return 0;
        }
        // 操作整型数组，这样方便一些
        // 也可以直接在 string 上操作
        int[] source = new int[len + 1];
        for (int i = 0; i < len; ++i) {
            source[i + 1] = s.charAt(i) - 48;
        }
        // 由于 dp[i] 只与 dp[i-1] 和 dp[i-2] 有关系，所以可以用常量空间复杂度来保存 dp 数据
        int dp1 = 1, dp2 = 1, curDp = 1;

        for (int i = 2; i <= len; ++i) {
            // 如果有 0，那么它必须是 >=10 的个位数，因此这里要去看一下 source[i - 1] 的值
            if (source[i] == 0 && (source[i - 1] > 2 || source[i - 1] == 0)) {
                return 0;
            }
            int sum = source[i - 1] * 10 + source[i];
            // 如果前一个数是 0 或者前一个数与当前数的和大于 26，那么它不能和当前数组合，所以 dp[i] = dp[i - 1]
            if (source[i - 1] == 0 || sum > 26) {
                curDp = dp2;
            } else if (source[i] != 0) {
                // 否则如果当前数字不是 0，那么它可以和前一个数组合也可以单独解码成英文字符
                curDp = dp1 + dp2;
            } else {
                // 如果当前数字是 0，那么它只能和前一个数字组合
                curDp = dp1;
            }
            // 后移
            dp1 = dp2;
            dp2 = curDp;
        }
        return curDp;
    }

    public static void main(String[] args) {
        String s = "12343614573491283755262120";
        System.out.println(numDecodings(s));
    }
}
