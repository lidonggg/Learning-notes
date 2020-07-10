package com.lidong.algorithm.leetcode.medium.trie;

/**
 * 添加与搜索单词 - 数据结构设计（中等-211）
 * 中文链接：https://leetcode-cn.com/problems/add-and-search-word-data-structure-design
 * <p>
 * 问题描述：
 * 设计一个支持以下两种操作的数据结构：
 * void addWord(word)
 * bool search(word)
 * search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
 * <p>
 * 示例:
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * <p>
 * 说明:
 * 你可以假设所有单词都是由小写字母 a-z 组成的。
 *
 * @author ls J
 * @date 2020/7/10 10:47
 */
public class AddAndSearchWord211 {

    private Trie trie;

    /**
     * 执行用时：52 ms，在所有 Java 提交中击败了 83.87% 的用户
     * 内存消耗：50.8 MB，在所有 Java 提交中击败了 100.00% 的用户
     */
    public AddAndSearchWord211() {
        this.trie = new Trie();
    }

    public void addWord(String word) {
        trie.insert(word);
    }

    public boolean search(String word) {
        // 由于 trie 根节点不保存任何数据，所以这里 idx 从 -1 开始，然后比较下一个元素
        return dfs(word, -1, word.length(), trie);
    }

    private boolean dfs(String word, int idx, int len, Trie curPos) {
        if (null == curPos) {
            return false;
        }
        if (idx == len - 1) {
            return curPos.isEnd;
        }
        boolean res = false;
        // 比较下一个位置
        int tidx = word.charAt(++idx) - 'a';
        if (tidx < 0) {
            for (Trie next : curPos.nexts) {
                res = res || dfs(word, idx, len, next);
            }
        } else {
            res = dfs(word, idx, len, curPos.nexts[tidx]);
        }
        return res;
    }

    /**
     * trie
     */
    private static class Trie {
        Trie[] nexts;

        boolean isEnd;

        Trie() {
            this.nexts = new Trie[26];
            this.isEnd = false;
        }

        /**
         * 添加单词
         *
         * @param word word
         */
        void insert(String word) {
            Trie curPos = this;
            for (int i = 0; i < word.length(); ++i) {
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
