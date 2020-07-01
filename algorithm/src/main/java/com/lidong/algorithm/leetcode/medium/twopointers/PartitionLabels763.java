package com.lidong.algorithm.leetcode.medium.twopointers;

import java.util.ArrayList;
import java.util.List;

/**
 * 划分字母区间（中等-763）
 * 中文链接：https://leetcode-cn.com/problems/partition-labels
 * <p>
 * 问题描述：
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 * <p>
 * 示例 1：
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *  
 * 提示：
 * 1.S的长度在[1, 500]之间。
 * 2.S只包含小写字母 'a' 到 'z' 。
 *
 * @author Ls J
 * @date 2020/7/1 11:08 PM
 */
public class PartitionLabels763 {

    /**
     * 执行用时：3 ms，在所有 Java 提交中击败了 97.23% 的用户
     * 内存消耗：38.2 MB，在所有 Java 提交中击败了 25.00% 的用户
     *
     * @param s s
     * @return res list
     */
    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        List<Integer> res = new ArrayList<>();
        // 保存每个字母最后一次出现的位置
        int[] lastIdxs = new int[26];
        for (int i = 0; i < n; ++i) {
            lastIdxs[s.charAt(i) - 'a'] = i;
        }

        // j -- 移动指针
        // lastEnd -- 上一个区间的最后一个元素位置
        int j = 0, lastEnd = 0;
        for (int i = 0; i < n; ++i) {
            j = Math.max(j, lastIdxs[s.charAt(i) - 'a']);
            // 如果 找到了 j == i，说明找到了某个字母最后一次出现的位置，那么可以把当前区间加到结果中
            if (j == i) {
                res.add(j - lastEnd);
                // 移动指针，寻找下一个区间
                j = i + 1;
                lastEnd = j;
            }
        }

        return res;
    }
}
