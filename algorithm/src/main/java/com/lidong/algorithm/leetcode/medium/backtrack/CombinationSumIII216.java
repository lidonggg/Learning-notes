package com.lidong.algorithm.leetcode.medium.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合总和 III（中等-216）
 * 中文链接：https://leetcode-cn.com/problems/combination-sum-iii
 * <p>
 * 问题描述：
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 说明：
 * 1.所有数字都是正整数。
 * 2.解集不能包含重复的组合。
 * <p>
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * <p>
 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * @author ls J
 * @date 2020/7/28 10:08
 */
public class CombinationSumIII216 {

    private List<List<Integer>> result = new ArrayList<>();

    /**
     * dfs + backtrack
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 71.16% 的用户
     * 内存消耗：36.4 MB，在所有 Java 提交中击败了 9.09% 的用户
     *
     * @param k k
     * @param n n
     * @return list
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(n, k, new ArrayList<>());
        return result;
    }

    private void dfs(int n, int k, List<Integer> item) {
        int sz = item.size();
        if (n == 0 && sz == k) {
            result.add(new ArrayList<>(item));
        }

        if (n == 0 && sz != k) {
            return;
        }
        if (n != 0 && sz == k) {
            return;
        }

        // 按照从小到大的顺序找
        int min = sz == 0 ? 1 : item.get(sz - 1) + 1;
        for (int i = min; i <= 9; ++i) {
            int lf = n - i;
            if (lf < 0) {
                break;
            }
            item.add(i);
            dfs(lf, k, item);
            item.remove(sz);
        }
    }
}
