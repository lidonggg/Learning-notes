package com.lidong.algorithm.leetcode.easy.bitmanipulate;

/**
 * 找不同（简单-389）
 * 中文链接：https://leetcode-cn.com/problems/find-the-difference/
 * <p>
 * 问题描述：
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 * <p>
 * 示例:
 * 输入：
 * s = "abcd"
 * t = "abcde"
 * 输出：
 * e
 * 解释：
 * 'e' 是那个被添加的字母。
 *
 * @author ls J
 * @date 2020/5/22 9:51
 */
public class FindTheDifference389 {

    /**
     * 方法一：数组加减法
     * <p>
     * 执行用时：3 ms，在所有 Java 提交中击败了 77.55% 的用户
     * 内存消耗：38.1 MB，在所有 Java 提交中击败了 25.00% 的用户
     *
     * @param s s
     * @param t t
     * @return diff
     */
    public static char findTheDifference(String s, String t) {
        int[] tmp = new int[26];
        int len = s.length();
        // 一次 for 循环
        for (int i = 0; i < len; ++i) {
            tmp[s.charAt(i) - 97]++;
            tmp[t.charAt(i) - 97]--;
        }
        tmp[t.charAt(len) - 97]--;
        for (int i = 0; i < 26; ++i) {
            if (tmp[i] != 0) {
                return (char) (i + 97);
            }
        }

        return 'a';
    }

    /**
     * 方法二：ASCII 码之差
     * 由于两个字符串只相差一个字符，因此两个字符串的 ASCII 码之差就是增加的字符
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 100% 的用户
     * 内存消耗：38.1 MB，在所有 Java 提交中击败了 25.00% 的用户
     *
     * @param s s
     * @param t t
     * @return diff
     */
    public static char findTheDifference2(String s, String t) {
        int res = 0;
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            res -= s.charAt(i);
            res += t.charAt(i);
        }
        res += t.charAt(len);
        return (char) res;
    }

    /**
     * 方法三：异或位运算
     * 相同字符异或操作以后为0，最后会得到被添加的字母
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 100% 的用户
     * 内存消耗：38.1 MB，在所有 Java 提交中击败了 25.00% 的用户
     *
     * @param s s
     * @param t t
     * @return diff
     */
    public static char findTheDifference3(String s, String t) {
        char res = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            res ^= s.charAt(i) ^ t.charAt(i);
        }
        res ^= t.charAt(len);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findTheDifference3("abab", "ababc"));
    }
}
