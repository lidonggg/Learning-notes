package com.lidong.algorithm.leetcode.medium.stack;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 移除无效的括号（中等-1249）
 * 中文链接：https://leetcode-cn.com/problems/minimum-remove-to-make-valid-parentheses
 * <p>
 * 问题描述：
 * 给你一个由 '('、')' 和小写字母组成的字符串 s。
 * 你需要从字符串中删除最少数目的 '(' 或者 ')' （可以删除任意位置的括号)，使得剩下的「括号字符串」有效。
 * 请返回任意一个合法字符串。
 * 有效「括号字符串」应当符合以下 任意一条 要求：
 * 1.空字符串或只包含小写字母的字符串
 * 2.可以被写作 AB（A 连接 B）的字符串，其中 A 和 B 都是有效「括号字符串」
 * 3.可以被写作 (A) 的字符串，其中 A 是一个有效的「括号字符串」
 * <p>
 * 示例 1：
 * 输入：s = "lee(t(c)o)de)"
 * 输出："lee(t(c)o)de"
 * 解释："lee(t(co)de)" , "lee(t(c)ode)" 也是一个可行答案。
 * <p>
 * 示例 2：
 * 输入：s = "a)b(c)d"
 * 输出："ab(c)d"
 * <p>
 * 示例 3：
 * 输入：s = "))(("
 * 输出：""
 * 解释：空字符串也是有效的
 * <p>
 * 示例 4：
 * 输入：s = "(a(b(c)d)"
 * 输出："a(b(c)d)"
 *  
 * 提示：
 * 1 <= s.length <= 10^5
 * s[i] 可能是 '('、')' 或英文小写字母
 *
 * @author ls J
 * @date 2020/7/8 15:25
 */
public class MinimumRemoveToMakeValidParentheses1249 {

    /**
     * 方法一：Stack + StringBuilder
     * <p>
     * 执行用时：413 ms，在所有 Java 提交中击败了 12.95% 的用户
     * 内存消耗：40.4 MB，在所有 Java 提交中击败了 16.67% 的用户
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param s s
     * @return new str
     */
    public String minRemoveToMakeValid(String s) {
        int n;
        if (null == s || (n = s.length()) == 0) {
            return s;
        }

        Stack<Integer> stack = new Stack<>();
        List<Integer> removes = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    removes.add(i);
                } else {
                    stack.pop();
                }
            }
        }

        while (!stack.isEmpty()) {
            removes.add(stack.pop());
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; ++i) {
            if (removes.contains(i)) {
                removes.remove(new Integer(i));
                continue;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    /**
     * 方法二：两个 StringBuilder
     * <p>
     * 执行用时：20 ms，在所有 Java 提交中击败了 81.60% 的用户
     * 内存消耗：40.4 MB，在所有 Java 提交中击败了 16.67% 的用户
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param s s
     * @return new str
     */
    public String minRemoveToMakeValid2(String s) {

        // 第一步，移除所有无效的右括号
        StringBuilder sb = new StringBuilder();
        // 保存所有的左括号个数
        int openSeen = 0;
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openSeen++;
                balance++;
            }
            if (c == ')') {
                // 如果这之前已经平衡了，说明当前右括号的是多余的，移除它
                if (balance == 0) {
                    continue;
                }
                balance--;
            }
            sb.append(c);
        }

        // 第二步：根据左括号多的个数，移除最靠右侧的 balance 个括号，这样一定能保证左右括号匹配
        StringBuilder result = new StringBuilder();
        int openToKeep = openSeen - balance;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') {
                // 如果还需要保留的左括号的个数不大于 0 了，说明所有的左括号都已经被添加了，直接跳过当前左括号
                if (openToKeep <= 0) {
                    continue;
                }
                openToKeep--;
            }
            result.append(c);
        }

        return result.toString();
    }
}
