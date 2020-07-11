package com.lidong.algorithm.leetcode.medium.trie;

/**
 * 实现 Trie 树（中等-208）
 * 中文链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * <p>
 * 问题描述：
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * <p>
 * 示例:
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * <p>
 * 说明:
 * - 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * - 保证所有输入均为非空字符串。
 *
 * @author Ls J
 * @date 2020/7/11 12:53 PM
 */
public class ImplementTrie208 {

    /**
     * 执行用时：41 ms，在所有 Java 提交中击败了 87.01% 的用户
     * 内存消耗：49.1 MB，在所有 Java 提交中击败了 100.00% 的用户
     */
    private static class Trie {

        Trie[] nexts;

        boolean isEnd;

        /**
         * init
         */
        public Trie() {
            this.nexts = new Trie[26];
            this.isEnd = false;
        }

        /**
         * insert
         *
         * @param word word
         */
        public void insert(String word) {
            // 基于题目描述，这一步可以不需要
            if (null == word || word.length() == 0) {
                return;
            }
            Trie curPos = this;
            for (int i = 0; i < word.length(); ++i) {
                int tidx = word.charAt(i) - 'a';
                if (null == curPos.nexts[tidx]) {
                    curPos.nexts[tidx] = new Trie();
                }
                curPos = curPos.nexts[tidx];
            }
            curPos.isEnd = true;
        }

        /**
         * Returns if the word is in the trie.
         *
         * @param word word
         */
        public boolean search(String word) {
            if (null == word || word.length() == 0) {
                return false;
            }
            Trie curPos = this;
            for (int i = 0; i < word.length(); ++i) {
                int tidx = word.charAt(i) - 'a';
                if (null == curPos.nexts[tidx]) {
                    return false;
                }
                curPos = curPos.nexts[tidx];
            }
            return curPos.isEnd;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         *
         * @param prefix prefix
         */
        public boolean startsWith(String prefix) {
            if (null == prefix || prefix.length() == 0) {
                return false;
            }
            Trie curPos = this;
            for (int i = 0; i < prefix.length(); ++i) {
                int tidx = prefix.charAt(i) - 'a';
                if (null == curPos.nexts[tidx]) {
                    return false;
                }
                curPos = curPos.nexts[tidx];
            }
            return true;
        }
    }
}
