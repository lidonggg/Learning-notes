package com.lidong.algorithm.leetcode.medium.priorityqueue;

import java.util.*;

/**
 * 前 k 个高频单词（中等-692）
 * 中文链接：https://leetcode-cn.com/problems/top-k-frequent-words
 * <p>
 * 问题描述：
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 * <p>
 * 示例 1：
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 * 注意，按字母顺序 "i" 在 "love" 之前。
 *  
 * 示例 2：
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * 输出: ["the", "is", "sunny", "day"]
 * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
 * 出现次数依次为 4, 3, 2 和 1 次。
 * <p>
 * 注意：
 * 1.假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
 * 2.输入的单词均由小写字母组成。
 *  
 * 扩展练习：
 * 尝试以 O(nlogk) 时间复杂度和 O(n) 空间复杂度解决。
 *
 * @author ls J
 * @date 2020/7/7 13:26
 */
public class TopKFrequentWords692 {

    /**
     * 构造小顶堆
     * <p>
     * 执行用时：8 ms，在所有 Java 提交中击败了 86.68% 的用户
     * 内存消耗：39.8 MB，在所有 Java 提交中击败了 25.00% 的用户
     * <p>
     * 时间复杂度：O(nlogk)
     * 空间复杂度：O(n)
     *
     * @param words words
     * @param k     k
     * @return res list
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> cntMap = new HashMap<>();
        for (String word : words) {
            cntMap.put(word, cntMap.getOrDefault(word, 0) + 1);
        }
        // 根据出现的频率构造小顶堆
        // 如果频率相同，那么比较字符串的字典序
        // 默认情况下，PriorityQueue 用的是自然排序法，例如数字从小到大、字符串顺序从前往后
        PriorityQueue<String> minHeap = new PriorityQueue<>(
                (w1, w2) -> cntMap.get(w1).equals(cntMap.get(w2)) ?
                        // 这里要用 w2.compareTo(w1)，才能保证频率相同的元素，排在前面的字符串顺序反而靠后
                        w2.compareTo(w1) : cntMap.get(w1) - cntMap.get(w2)
        );

        for (String word : cntMap.keySet()) {
            minHeap.offer(word);
            // 保持堆中最多有 k 个元素
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        List<String> res = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            res.add(minHeap.poll());
        }
        // 出来的结果是反序的，这里进行一次翻转
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("aa", 2);
        map.put("bb", 2);
        System.out.println("aa".compareTo("bb"));
        System.out.println(map.get("aa").equals(map.get("bb")) ?
                "bb".compareTo("aa") : map.get("aa") - map.get("bb"));
    }
}
