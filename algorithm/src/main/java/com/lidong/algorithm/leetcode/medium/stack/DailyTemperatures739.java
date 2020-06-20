package com.lidong.algorithm.leetcode.medium.stack;

import java.util.Stack;

/**
 * 每日温度（中等-739）
 * 中文链接：https://leetcode-cn.com/problems/daily-temperatures/
 *
 * 问题描述：
 * 请根据每日气温列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：
 * 气温 列表长度的范围是 [1, 30000]。
 * 每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * @author Ls J
 * @date 2020/6/20 2:14 PM
 */
public class DailyTemperatures739 {

    /**
     * 方法一：单调栈
     * <p>
     * 执行用时： 20 ms，在所有 Java 提交中击败了 70.94% 的用户
     * 内存消耗：47.4 MB，在所有 Java 提交中击败了 6.45% 的用户
     *
     * @param t t
     * @return int[]
     */
    public int[] dailyTemperatures(int[] t) {
        int n = t.length;
        int[] res = new int[n];

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < n; ++i) {
            // 单调递减栈，一旦找到一个比栈顶元素（以栈顶元素为索引的数组元素）大的 i，那么栈内元素依次出栈，直到以栈顶元素为索引的 t[k] < t[i]
            while (!stack.isEmpty() && t[stack.peek()] < t[i]) {
                int k = stack.pop();
                res[k] = i - k;
            }
            stack.push(i);
        }

        return res;
    }

    /**
     * 方法二：双指针
     * <p>
     * 执行用时： 1147 ms，在所有 Java 提交中击败了 7.80% 的用户
     * 内存消耗： 47.9 MB，在所有 Java 提交中击败了 6.45% 的用户
     *
     * @param t t
     * @return int[]
     */
    public int[] dailyTemperatures2(int[] t) {
        int n = t.length;
        int[] res = new int[n];

        int l = 0, r = l + 1;

        while (r < n && l < r) {
            while (r < n && t[r] <= t[l]) {
                r++;
            }
            if (r >= n) {
                res[l] = 0;
            } else {
                res[l] = r - l;
            }
            l++;
            r = l + 1;
        }

        return res;
    }
}
