package com.lidong.algorithm.interview.tencent;

import java.util.Stack;

/**
 * @author ls J
 * @date 2020/5/7 16:53
 * <p>
 * 逛街
 * 问题描述：
 * 小Q在周末的时候和他的小伙伴来到大城市逛街，一条步行街上有很多高楼，共有 n 座高楼排成一行。
 * 小Q从第一栋一直走到了最后一栋，小Q从来都没有见到这么多的楼，所以他想知道他在每栋楼的位置处能看到多少栋楼呢？（当前面的楼的高度大于等于后面的楼时，后面的楼将被挡住）
 * <p>
 * 示例1
 * 输入
 * 6
 * 5 3 8 3 2 5
 * 输出
 * 3 3 5 4 4 4
 * 说明
 * 当小Q处于位置 3 时，他可以向前看到位置 2,1 处的楼，向后看到位置 4,6 处的楼，加上第3栋楼，共可看到 5 栋楼。
 * 当小Q处于位置 4 时，他可以向前看到位置 3 处的楼，向后看到位置 5,6 处的楼，加上第4栋楼，共可看到 4 栋楼。
 */
public class ShoppingTencent {

    /**
     * 从前往后和从后往前分别利用单调栈
     *
     * @param arr arr
     * @return res arr
     */
    public int[] shopping(int[] arr) {
        int len = arr.length;
        int[] res = new int[len];

        Stack<Integer> stack = new Stack<>();

        // 从前往后的单调递减栈
        for (int i = 0; i < len; ++i) {
            // 自己算一个
            res[i] += 1;
            // 前面能看到的楼数
            res[i] += stack.size();
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            stack.push(arr[i]);
        }

        for (int i = len - 1; i >= 0; ++i) {
            // 后面能看到的楼数
            res[i] += stack.size();
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            stack.push(arr[i]);
        }

        return res;
    }
}
