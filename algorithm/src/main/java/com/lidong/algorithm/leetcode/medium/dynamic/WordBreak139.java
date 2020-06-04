package com.lidong.algorithm.leetcode.medium.dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * 单词拆分（中等-139）
 * 中文链接：https://leetcode-cn.com/problems/word-break/
 * <p>
 * 问题描述：
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * <p>
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * <p>
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * <p>
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * <p>
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 * @author Ls J
 * @date 2020/5/24 12:06 PM
 */
public class WordBreak139 {

    private List<String> wordDict;

    private boolean result;

    /**
     * 方法一：wordDict 递归，超时
     *
     * @param s        s
     * @param wordDict wordDict
     * @return result
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }
        if (wordDict.size() == 0) {
            return false;
        }

        // 首先删除 s 中不存在的字符串
        wordDict.removeIf(ss -> !s.contains(ss));
        this.wordDict = wordDict;
        recurse(s);
        return result;
    }

    /**
     * 根据字典中的每个单词，都去查看 s 是否以它开头
     * 如果是的，那么继续去寻找 s 中剩下的子字符串
     * 递归结束的条件是 s 剩余长度为 0，获取 result 为 true
     *
     * @param s s
     */
    private void recurse(String s) {
        if (s.length() == 0) {
            result = true;
            return;
        }

        if (result) {
            return;
        }

        for (String dictStr : wordDict) {
            if (s.startsWith(dictStr)) {
                recurse(s.substring(dictStr.length()));
            }
        }
    }

    /**
     * 方法二：动态规划
     * 这个方法的想法是对于给定的字符串（s）可以被拆分成子问题 s1 和 s2。
     * 如果这些子问题都可以独立地被拆分成符合要求的子问题，那么整个问题 s 也可以满足。
     * 比如说如果 "catsanddog" 可以拆分成两个子字符串 "catsand" 和 "dog"，子问题 "catsand" 可以进一步拆分成 "cats" 和 "and"，
     * 这两个独立的部分都是字典的一部分，所以 "catsand" 满足题意条件，再往前，"catsand" 和 "dog" 也分别满足条件，
     * 所以整个字符串 "catsanddog" 也满足条件。
     * <p>
     * 执行用时：9 ms，在所有 Java 提交中击败了 68.25% 的用户
     * 内存消耗：40.3 MB，在所有 Java 提交中击败了 8.00% 的用户
     *
     * @param s        s
     * @param wordDict wordDict
     * @return true if success
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        // dp[0] 代表空字符串，它应该始终为 true
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        String s = "cars";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("car");
        wordDict.add("ca");
        wordDict.add("rs");
        wordDict.add("and");
        wordDict.add("cat");
        wordDict.add("og");
        WordBreak139 wb = new WordBreak139();
        System.out.println(wb.wordBreak(s, wordDict));

        String s1 = "catsandog";
        System.out.println(s1.substring(3));
    }
}
