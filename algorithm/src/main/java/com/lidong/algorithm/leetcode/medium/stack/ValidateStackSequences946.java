package com.lidong.algorithm.leetcode.medium.stack;

import java.util.Stack;

/**
 * 验证栈序列（中等-946）
 * 中文链接：https://leetcode-cn.com/problems/validate-stack-sequences/
 * <p>
 * 问题描述：
 * 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * <p>
 * 示例 2：
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 * <p>
 * 提示：
 * 0 <= pushed.length == popped.length <= 1000
 * 0 <= pushed[i], popped[i] < 1000
 * pushed 是 popped 的排列。
 *
 * @author Ls J
 * @date 2020/6/25 3:20 PM
 */
public class ValidateStackSequences946 {

    /**
     * 模拟
     * <p>
     * 执行用时：2 ms，在所有 Java 提交中击败了 93.74% 的用户
     * 内存消耗：39.6 MB，在所有 Java 提交中击败了 90.00% 的用户
     *
     * @param pushed 入栈顺序
     * @param popped 出栈顺序
     * @return true / false
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        Stack<Integer> stack = new Stack<>();

        int j = 0;
        // 入栈
        for (int x : pushed) {
            stack.push(x);
            // 出栈
            while (!stack.isEmpty() && j < n && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }

        return j == n;
    }
}
