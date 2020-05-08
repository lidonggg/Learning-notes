package com.lidong.algorithm.leetcode.easy.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 有效的括号（简单-20）
 * 问题描述：
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * 输入: "()"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 * <p>
 * 示例 3:
 * 输入: "(]"
 * 输出: false
 * <p>
 * 示例 4:
 * 输入: "([)]"
 * 输出: false
 * <p>
 * 示例 5:
 * 输入: "{[]}"
 * 输出: true
 *
 * @author Ls J
 * @date 2020/4/19 3:25 PM
 */
public class ValidParentheses20 {

    private static final Map<Character, Character> PAIRS = new HashMap<>();

    static {
        PAIRS.put(')', '(');
        PAIRS.put('}', '{');
        PAIRS.put(']', '[');
    }

    public boolean isValid(String s) {
        if (null == s) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (!PAIRS.containsKey(c)) {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && stack.peek().equals(PAIRS.get(c))) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "(]]";
        ValidParentheses20 v = new ValidParentheses20();
        System.out.println(v.isValid(s));
    }
}
