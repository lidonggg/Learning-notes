package com.lidong.algorithm.leetcode.medium.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 搜索推荐系统（中等-1268）
 * 中文链接：https://leetcode-cn.com/problems/search-suggestions-system
 * <p>
 * 问题描述：
 * 给你一个产品数组 products 和一个字符串 searchWord ，products  数组中每个产品都是一个字符串。
 * 请你设计一个推荐系统，在依次输入单词 searchWord 的每一个字母后，推荐 products 数组中前缀与 searchWord 相同的最多三个产品。如果前缀相同的可推荐产品超过三个，请按字典序返回最小的三个。
 * 请你以二维列表的形式，返回在输入 searchWord 每个字母后相应的推荐产品的列表。
 * <p>
 * 示例 1：
 * 输入：products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * 输出：[
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * 解释：按字典序排序后的产品列表是 ["mobile","moneypot","monitor","mouse","mousepad"]
 * 输入 m 和 mo，由于所有产品的前缀都相同，所以系统返回字典序最小的三个产品 ["mobile","moneypot","monitor"]
 * 输入 mou， mous 和 mouse 后系统都返回 ["mouse","mousepad"]
 * <p>
 * 示例 2：
 * 输入：products = ["havana"], searchWord = "havana"
 * 输出：[["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * <p>
 * 示例 3：
 * 输入：products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 * 输出：[["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 * <p>
 * 示例 4：
 * 输入：products = ["havana"], searchWord = "tatiana"
 * 输出：[[],[],[],[],[],[],[]] 
 * <p>
 * 提示：
 * - 1 <= products.length <= 1000
 * - 1 <= Σ products[i].length <= 2 * 10^4
 * - products[i] 中所有的字符都是小写英文字母。
 * - 1 <= searchWord.length <= 1000
 * - searchWord 中所有字符都是小写英文字母。
 *
 * @author ls J
 * @date 2020/7/30 13:09
 */
public class SearchSuggestionsSystem1268 {

    /**
     * trie + priorityQueue
     * <p>
     * 执行用时：27 ms，在所有 Java 提交中击败了 70.29% 的用户
     * 内存消耗：45.5 MB，在所有 Java 提交中击败了 50.00% 的用户
     *
     * @param products   prods
     * @param searchWord word
     * @return list res
     */
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie root = new Trie();
        for (String word : products) {
            root.insert(word);
        }

        List<List<String>> res = new ArrayList<>();
        Trie curPos = root;
        boolean emptyFlag = false;
        for (char c : searchWord.toCharArray()) {
            int idx = c - 'a';
            // 表明当到达特定位置之后，trie 中就没有特定模式的前缀词了，那么后面的列表就都是空的了
            if (emptyFlag || null == curPos.children[idx]) {
                res.add(new ArrayList<>());
                if (!emptyFlag) {
                    emptyFlag = true;
                }
                continue;
            }
            curPos = curPos.children[idx];
            List<String> items = new ArrayList<>();
            // 只最多添加三个
            while (!curPos.words.isEmpty() && items.size() < 3) {
                items.add(curPos.words.poll());
            }
            res.add(items);
        }

        return res;
    }

    private static class Trie {
        Trie[] children;

        /**
         * 保存能够到达本 trie 的单词，按照字符串字典序构造小顶堆
         */
        PriorityQueue<String> words;

        Trie() {
            this.children = new Trie[26];
            this.words = new PriorityQueue<>();
        }

        void insert(String word) {
            Trie curPos = this;
            for (int i = 0; i < word.length(); ++i) {
                int idx = word.charAt(i) - 'a';
                if (null == curPos.children[idx]) {
                    curPos.children[idx] = new Trie();
                }
                curPos = curPos.children[idx];
                curPos.words.offer(word);
            }
        }
    }
}
