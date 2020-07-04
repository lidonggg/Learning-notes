package com.lidong.algorithm.leetcode.medium.priorityqueue;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 根据字符出现频率排序（中等-451）
 * 中文链接：https://leetcode-cn.com/problems/sort-characters-by-frequency
 * <p>
 * 问题描述：
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * <p>
 * 示例 1:
 * 输入:
 * "tree"
 * 输出:
 * "eert"
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * <p>
 * 示例 2:
 * 输入:
 * "cccaaa"
 * 输出:
 * "cccaaa"
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * <p>
 * 示例 3:
 * <p>
 * 输入:
 * "Aabb"
 * 输出:
 * "bbAa"
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 *
 * @author Ls J
 * @date 2020/7/4 2:06 AM
 */
public class SortCharactersByFrequency451 {

    /**
     * 执行用时：16 ms，在所有 Java 提交中击败了 62.55% 的用户
     * 内存消耗：41.1 MB，在所有 Java 提交中击败了 11.11% 的用户
     *
     * @param s s
     * @return string
     */
    public String frequencySort(String s) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (char curChar : s.toCharArray()) {
            countMap.put(curChar, countMap.getOrDefault(curChar, 0) + 1);
        }

        // 大顶堆
        PriorityQueue<Character> maxHeap = new PriorityQueue<>(
                (c1, c2) -> countMap.get(c2) - countMap.get(c1)
        );
        maxHeap.addAll(countMap.keySet());

        StringBuilder sb = new StringBuilder(s.length());
        while (!maxHeap.isEmpty()) {
            char c = maxHeap.poll();
            sb.append(String.valueOf(c).repeat(Math.max(0, countMap.get(c))));
        }
        return sb.toString();
    }
}
