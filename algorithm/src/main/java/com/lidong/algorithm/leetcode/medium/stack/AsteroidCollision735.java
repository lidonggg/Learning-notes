package com.lidong.algorithm.leetcode.medium.stack;

import java.util.Stack;

/**
 * 行星碰撞（中等-735）
 * 中文链接：https://leetcode-cn.com/problems/asteroid-collision
 * <p>
 * 问题描述：
 * 给定一个整数数组 asteroids，表示在同一行的行星。
 * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
 * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 * <p>
 * 示例 1:
 * 输入:
 * asteroids = [5, 10, -5]
 * 输出: [5, 10]
 * 解释:
 * 10 和 -5 碰撞后只剩下 10。 5 和 10 永远不会发生碰撞。
 * <p>
 * 示例 2:
 * 输入:
 * asteroids = [8, -8]
 * 输出: []
 * 解释:
 * 8 和 -8 碰撞后，两者都发生爆炸。
 * <p>
 * 示例 3:
 * 输入:
 * asteroids = [10, 2, -5]
 * 输出: [10]
 * 解释:
 * 2 和 -5 发生碰撞后剩下 -5。10 和 -5 发生碰撞后剩下 10。
 * <p>
 * 示例 4:
 * 输入:
 * asteroids = [-2, -1, 1, 2]
 * 输出: [-2, -1, 1, 2]
 * 解释:
 * -2 和 -1 向左移动，而 1 和 2 向右移动。
 * 由于移动方向相同的行星不会发生碰撞，所以最终没有行星发生碰撞。
 * <p>
 * 说明:
 * 1.数组 asteroids 的长度不超过 10000。
 * 2.每一颗行星的大小都是非零整数，范围是 [-1000, 1000] 。
 *
 * @author ls J
 * @date 2020/7/8 18:10
 */
public class AsteroidCollision735 {

    /**
     * 执行用时：5 ms，在所有 Java 提交中击败了 89.20% 的用户
     * 内存消耗：40.2 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param asteroids asteroids
     * @return asteroids keeped
     */
    public int[] asteroidCollision(int[] asteroids) {
        // 栈中保存向右移动的行星，或者最左侧向左移动的行星，这样栈中的行星都不会相撞
        Stack<Integer> stack = new Stack<>();
        outer:
        for (int asteroid : asteroids) {
            // 如果当前值大于 0，直接入栈
            if (asteroid > 0) {
                stack.push(asteroid);
            } else {
                // 否则需要循环比较栈顶元素和当前元素的大小
                int ci = -asteroid;
                // 如果栈顶元素大于零，说明会跟当前行星相撞
                while (!stack.isEmpty() && stack.peek() > 0) {
                    int pe = stack.peek();
                    // 如果栈顶元素大于当前的绝对值，那么当前行星爆炸
                    if (pe > ci) {
                        continue outer;
                    }
                    // 否则，栈顶元素爆炸
                    stack.pop();
                    // 如果栈顶元素等于当前绝对值，那么当前行星也要爆炸
                    if (pe == ci) {
                        continue outer;
                    }
                }
                // 能走到这里说明当前行星可以保留，入栈
                stack.push(-ci);
            }
        }
        int size = stack.size();
        int[] res = new int[size];
        int i = size - 1;
        while (!stack.isEmpty()) {
            res[i--] = stack.pop();
        }
        return res;
    }
}
