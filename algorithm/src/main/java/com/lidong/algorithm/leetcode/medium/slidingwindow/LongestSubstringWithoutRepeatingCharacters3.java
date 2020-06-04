package com.lidong.algorithm.leetcode.medium.slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * 无重复字符的最长子串（中等-3）
 * 中文链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * <p>
 * 问题描述：
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 * <p>
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * @author ls J
 * @date 2020/5/19 9:45
 */
public class LongestSubstringWithoutRepeatingCharacters3 {

    /**
     * 方法一：动态规划
     * 记录一个 dp 数组，保存以每个位置的元素为结尾的最长子串长度
     * 当遍历到下一个元素 s[i] 的时候，以一个字符为结尾的子串从后往前遍历，有两种情况：
     * 1.如果前一个元素为结尾的子串中（也就是以前一个元素的下标为结尾，前一个元素的 dp 值为长度的子串）存在当前字符，假设在 existIdx 位置，那么 dp[i] = i - existIdx；
     * 2.如果前一个子串不存在当前字符，那么 dp[i] = dp[i-1] + 1
     * <p>
     * 执行用时：6 ms，在所有 Java 提交中击败了 85.04% 的用户
     * 内存消耗：39.6 MB，在所有 Java 提交中击败了 5.89% 的用户
     * <p>
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(n)
     *
     * @param s s
     * @return length
     */
    public static int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (len <= 1) {
            return len;
        }
        int[] dp = new int[len];
        // 用 char 数组代替字符串，能大大提高执行效率
        char[] cs = s.toCharArray();
        dp[0] = 1;
        int res = dp[0];
        for (int i = 1; i < len; ++i) {
            // 找前一个子串中当前字符出现的位置
            int existIdx = findIdxOfCharacter(cs, dp[i - 1], i - 1, cs[i]);
            dp[i] = i - existIdx;
            if (dp[i] > res) {
                res = dp[i];
            } else if (dp[i] + (len - i - 1) < res) {
                // 如果剩余元素无法构成比 res 还大的子串，那么提前退出
                break;
            }
        }
        return res;
    }

    /**
     * 在子串中从后往前查找某个字符出现的位置，如果不存在则返回前一个子串的前一个位置，这样在 dp 的时候可以统一处理
     *
     * @param cs      原始字符串
     * @param subLen  子串长度
     * @param lastIdx 子串最后一个字符的位置
     * @param c       目标字符
     * @return index, -1 if does not exist
     */
    private static int findIdxOfCharacter(char[] cs, int subLen, int lastIdx, char c) {
        for (int i = lastIdx; i >= lastIdx - subLen + 1; --i) {
            if (cs[i] == c) {
                return i;
            }
        }

        return lastIdx - subLen;
    }

    /**
     * 方法二：滑动窗口
     * 来自 leetcode 官方题解：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetc-2/
     * <p>
     * 如果我们依次递增地枚举子串的起始位置，那么子串的结束位置也是递增的！
     * 这里的原因在于，假设我们选择字符串中的第 k 个字符作为起始位置，并且得到了不包含重复字符的最长子串的结束位置为 rk。
     * 那么当我们选择第 k+1 个字符作为起始位置时，首先从 k+1 到 rk 的字符显然是不重复的，
     * 并且由于少了原本的第 k 个字符，我们可以尝试继续增大 rk，直到右侧出现了重复字符为止：
     * 1.我们使用两个指针表示字符串中的某个子串（的左右边界）。其中左指针代表着上文中「枚举子串的起始位置」；
     * 2.在每一步的操作中，我们会将左指针向右移动一格，表示我们开始枚举下一个字符作为起始位置，然后我们可以不断地向右移动右指针，但需要保证这两个指针对应的子串中没有重复的字符。在移动结束后，这个子串就对应着以左指针开始的，不包含重复字符的最长子串，我们记录下这个子串的长度；
     * 3.在枚举结束后，我们找到的最长的子串的长度即为答案。
     * <p>
     * 执行用时：8 ms，在所有 Java 提交中击败了 71.30% 的用户
     * 内存消耗：40 MB，在所有 Java 提交中击败了 5.20% 的用户
     *
     * @param s s
     * @return length
     */
    public int lengthOfLongestSubstring2(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, res = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            res = Math.max(res, rk - i + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}
