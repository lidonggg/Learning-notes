package com.lidong.algorithm.leetcode.medium.trie;

import java.util.Arrays;

/**
 * 恢复空格（中等-面试题17.13）
 * 中文链接：https://leetcode-cn.com/problems/re-space-lcci
 * <p>
 * 问题描述：
 * 你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。
 * 像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。
 * 在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。
 * 假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 * <p>
 * 注意：本题相对原题稍作改动，只需返回未识别的字符数
 * <p>
 * 示例：
 * 输入：
 * dictionary = ["looked","just","like","her","brother"]
 * sentence = "jesslookedjustliketimherbrother"
 * 输出： 7
 * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
 * <p>
 * 提示：
 * - 0 <= len(sentence) <= 1000
 * - dictionary中总字符数不超过 150000。
 * - 你可以认为dictionary和sentence中只包含小写字母。
 *
 * @author ls J
 * @date 2020/7/9 16:54
 */
public class ReSpaceI1713 {

    /**
     * 利用 trie
     * <p>
     * 执行用时：19 ms，在所有 Java 提交中击败了 89.85% 的用户
     * 内存消耗：63.3 MB，在所有 Java 提交中击败了 100.00% 的用户
     * <p>
     * 时间复杂度：O(|dic| + n^2)，|dic| 代表词典中总的字符数
     * 空间复杂度：O(|dic| * s + n)，s 代表字符集字符数，这里是 26
     *
     * @param dictionary dic
     * @param sentence   sent
     * @return num
     */
    public int respace(String[] dictionary, String sentence) {
        int n = sentence.length();
        if (n == 0 || dictionary.length == 0) {
            return 0;
        }

        Trie root = new Trie();
        root.init(dictionary);
        // dp[i] 代表前 i 个字符中未识别的字符数的最小值
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; ++i) {
            dp[i] = dp[i - 1] + 1;
            Trie curPos = root;
            // 从后往前进行比较
            for (int j = i; j >= 1; --j) {
                int t = sentence.charAt(j - 1) - 'a';
                // 如果当前字符不存在，那么说明字符 j ~ i 不能构成一个单词
                if (null == curPos.nexts[t]) {
                    break;
                }
                // 如果找到了一个单词，那么 dp[i] = min(dp[i], dp[j - 1])
                if (curPos.nexts[t].isEnd) {
                    dp[i] = Math.min(dp[i], dp[j - 1]);
                }
                if (dp[i] == 0) {
                    break;
                }
                // 当前是单词的一部分，继续去看前面一个单词
                curPos = curPos.nexts[t];
            }
        }

        return dp[n];
    }

    private static class Trie {
        Trie[] nexts;
        boolean isEnd;

        Trie() {
            this.nexts = new Trie[26];
            this.isEnd = false;
        }

        /**
         * init
         * 因为比较的时候是以字符串往前反向比较的，因此这里的 trie 树也应该从单词尾进行构造
         *
         * @param dictionary dic
         */
        void init(String[] dictionary) {
            for (String word : dictionary) {
                Trie curPos = this;
                int n = word.length();
                for (int i = n - 1; i >= 0; --i) {
                    int idx = word.charAt(i) - 'a';
                    if (null == curPos.nexts[idx]) {
                        curPos.nexts[idx] = new Trie();
                    }
                    curPos = curPos.nexts[idx];
                }
                curPos.isEnd = true;
            }
        }
    }
}
