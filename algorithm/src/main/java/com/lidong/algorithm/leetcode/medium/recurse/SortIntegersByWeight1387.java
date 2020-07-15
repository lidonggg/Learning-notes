package com.lidong.algorithm.leetcode.medium.recurse;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 将整数按权重排序（中等-1387）
 * 中文链接：https://leetcode-cn.com/problems/sort-integers-by-the-power-value
 * <p>
 * 问题描述：
 * 我们将整数 x 的 权重 定义为按照下述规则将 x 变成 1 所需要的步数：
 * 1.如果 x 是偶数，那么 x = x / 2
 * 2.如果 x 是奇数，那么 x = 3 * x + 1
 * 比方说，x=3 的权重为 7 。因为 3 需要 7 步变成 1 （3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1）。
 * 给你三个整数 lo， hi 和 k 。你的任务是将区间 [lo, hi] 之间的整数按照它们的权重 升序排序 ，如果大于等于 2 个整数有 相同 的权重，那么按照数字自身的数值 升序排序 。
 * 请你返回区间 [lo, hi] 之间的整数按权重排序后的第 k 个数。
 * 注意，题目保证对于任意整数 x （lo <= x <= hi） ，它变成 1 所需要的步数是一个 32 位有符号整数。
 * <p>
 * 示例 1：
 * 输入：lo = 12, hi = 15, k = 2
 * 输出：13
 * 解释：12 的权重为 9（12 --> 6 --> 3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1）
 * 13 的权重为 9
 * 14 的权重为 17
 * 15 的权重为 17
 * 区间内的数按权重排序以后的结果为 [12,13,14,15] 。对于 k = 2 ，答案是第二个整数也就是 13 。
 * 注意，12 和 13 有相同的权重，所以我们按照它们本身升序排序。14 和 15 同理。
 * <p>
 * 示例 2：
 * 输入：lo = 1, hi = 1, k = 1
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：lo = 7, hi = 11, k = 4
 * 输出：7
 * 解释：区间内整数 [7, 8, 9, 10, 11] 对应的权重为 [16, 3, 19, 6, 14] 。
 * 按权重排序后得到的结果为 [8, 10, 11, 7, 9] 。
 * 排序后数组中第 4 个数字为 7 。
 * <p>
 * 示例 4：
 * 输入：lo = 10, hi = 20, k = 5
 * 输出：13
 * <p>
 * 示例 5：
 * 输入：lo = 1, hi = 1000, k = 777
 * 输出：570
 * <p>
 * 提示：
 * 1 <= lo <= hi <= 1000
 * 1 <= k <= hi - lo + 1
 *
 * @author ls J
 * @date 2020/7/15 21:43
 */
public class SortIntegersByWeight1387 {

    private Map<Integer, Integer> memo;

    /**
     * 递归 + 记忆化 + 大顶堆
     * <p>
     * 执行用时：54 ms，在所有 Java 提交中击败了 77.16% 的用户
     * 内存消耗：39.8 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param lo lo
     * @param hi hi
     * @param k  k
     * @return kth num
     */
    public int getKth(int lo, int hi, int k) {
        memo = new HashMap<>();
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> a[1] == b[1] ? b[0] - a[0] : b[1] - a[1]
        );
        for (int i = lo; i <= hi; ++i) {
            maxHeap.offer(new int[]{i, getW(i)});
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek()[0];
    }

    /**
     * 记忆化递归
     *
     * @param num num
     * @return w
     */
    private int getW(int num) {
        if (num == 1) {
            return 0;
        }
        if (memo.containsKey(num)) {
            return memo.get(num);
        }
        int cnt = (num & 1) == 0 ? getW(num >> 1) : getW(3 * num + 1);
        ++cnt;
        memo.put(num, cnt);
        return cnt;
    }
}
