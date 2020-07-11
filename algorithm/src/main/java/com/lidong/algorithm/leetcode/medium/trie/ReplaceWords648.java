package com.lidong.algorithm.leetcode.medium.trie;

import java.util.List;

/**
 * 单词替换（中等-648）
 * 中文链接：https://leetcode-cn.com/problems/replace-words
 * <p>
 * 问题描述：
 * 在英语中，我们有一个叫做词根(root)的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为继承词(successor)。
 * 例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 * 现在，给定一个由许多词根组成的词典和一个句子。
 * 你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 * 你需要输出替换之后的句子。
 * <p>
 * 示例：
 * 输入：dict(词典) = ["cat", "bat", "rat"] sentence(句子) = "the cattle was rattled by the battery"
 * 输出："the cat was rat by the bat"
 * <p>
 * 提示：
 * 输入只包含小写字母。
 * - 1 <= dict.length <= 1000
 * - 1 <= dict[i].length <= 100
 * - 1 <= 句中词语数 <= 1000
 * - 1 <= 句中词语长度 <= 1000
 *
 * @author ls J
 * @date 2020/7/10 17:05
 */
public class ReplaceWords648 {

    /**
     * 利用 trie 保存前缀，然后针对每个单词分别在 trie 中寻找最短前缀
     * <p>
     * 执行用时：7 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：51.8 MB，在所有 Java 提交中击败了 25.00% 的用户
     *
     * @param dict     dict
     * @param sentence sent
     * @return new sent
     */
    public String replaceWords(List<String> dict, String sentence) {
        String[] words = sentence.split(" ");
        if (words.length <= 1) {
            return sentence;
        }

        Trie troot = new Trie();
        for (String root : dict) {
            troot.insert(root);
        }
        for (int i = 0; i < words.length; ++i) {
            String starts = troot.starts(words[i]);
            if (starts.length() > 0) {
                words[i] = starts;
            }
        }
        StringBuilder sb = new StringBuilder(words[0]);
        for (int i = 1; i < words.length; ++i) {
            sb.append(" ").append(words[i]);
        }
        return sb.toString();
    }

    private static class Trie {

        Trie[] nexts;
        boolean isEnd;

        Trie() {
            this.nexts = new Trie[26];
            this.isEnd = false;
        }

        void insert(String word) {
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
         * 寻找某个单词的前缀
         *
         * @param word word
         * @return root
         */
        String starts(String word) {
            Trie curPos = this;
            int i = 0;
            StringBuilder sb = new StringBuilder();
            while (i < word.length()) {
                int tidx = word.charAt(i) - 'a';
                curPos = curPos.nexts[tidx];
                // 如果当前字符对应的 trie 为 null，说明没有找到对应的前缀，直接返回空字符串
                if (null == curPos) {
                    return "";
                }
                sb.append(word.charAt(i));
                // 如果当前字符是一个结尾字符，说明找到了
                if (curPos.isEnd) {
                    return sb.toString();
                }
                i++;
            }
            return sb.toString();
        }
    }
}
