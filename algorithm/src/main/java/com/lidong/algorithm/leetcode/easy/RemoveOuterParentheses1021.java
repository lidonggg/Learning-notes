package com.lidong.algorithm.leetcode.easy;

/**
 * @author ls J
 * @date 2019/7/3 3:20 PM
 * 删除最外层的括号（简单-1021）
 * 问题描述：
 * 有效括号字符串为空 ("")、"(" + A + ")" 或 A + B，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
 * 如果有效字符串 S 非空，且不存在将其拆分为 S = A+B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。
 * 给出一个非空有效字符串 S，考虑将其进行原语化分解，使得：S = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。
 * 对 S 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 S 。
 * <br>
 * 示例：
 * 输入："(()())(())"
 * 输出："()()()"
 */
public class RemoveOuterParentheses1021 {

    /**
     * 用一个计数器进行左右括号的匹配，每匹配一次，计数器减一
     * 当遇见左括号时，如果计数器大于零，代表它不是最外层的括号，存到返回值中
     * 当遇见右括号时，计数器要减一，如果此时计数器大于零，代表它不是最外层的括号，存到返回值中
     *
     * @param S 原字符串
     * @return 目标字符串
     */
    public static String removeOuterParentheses(String S) {
        if (S == null || "".equals(S)) {
            return "";
        }
        int count = 0;
        char[] inputs = S.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char currentChar : inputs) {
            if (currentChar == '(') {
                if (count > 0) {
                    sb.append(currentChar);
                }
                count++;
            } else {
                count--;
                if (count > 0) {
                    sb.append(currentChar);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeOuterParentheses("(()())(())"));
    }
}
