package com.lidong.algorithm.leetcode.medium.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 括号生成（中等-22）
 * 中文链接：
 * <p>
 * 问题描述：
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 示例：
 * 输入：n = 3
 * 输出：[
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 *
 * @author Ls J
 * @date 2020/6/26 3:58 PM
 */
public class GenerateParentheses22 {

    /**
     * 方法一：回溯算法
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 97.29% 的用户
     * 内存消耗：40.2 MB，在所有 Java 提交中击败了 5.26% 的用户
     *
     * @param n n
     * @return list
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        backtrack(0, 0, n, new StringBuilder(), res);
        return res;
    }

    /**
     * 回溯
     *
     * @param left  左括号个数
     * @param right 右括号个数
     * @param n     n
     * @param sb    sb
     * @param res   res
     */
    private void backtrack(int left, int right, int n, StringBuilder sb, List<String> res) {
        if (sb.length() == n * 2) {
            res.add(sb.toString());
            return;
        }
        // 如果左括号个数小于 n，那么可以继续追加左括号
        if (left < n) {
            sb.append("(");
            backtrack(left + 1, right, n, sb, res);
            // 回溯
            sb.deleteCharAt(sb.length() - 1);
        }
        // 如果右括号个数小于左括号个数，那么可以继续追加右括号
        if (left > right) {
            sb.append(")");
            backtrack(left, right + 1, n, sb, res);
            // 回溯
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private ArrayList[] cache = new ArrayList[100];

    public List<String> generate(int n) {
        if (cache[n] != null) {
            return cache[n];
        }
        ArrayList<String> ans = new ArrayList<>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c) {
                for (String left : generate(c)) {
                    for (String right : generate(n - 1 - c))
                        ans.add("(" + left + ")" + right);
                }
            }
        }
        cache[n] = ans;
        return ans;
    }

    /**
     * 方法二
     * 思路就是 (a)b，寻找每种 a，b 的情况
     *
     * 来自 leetcode 官方题解：https://leetcode-cn.com/problems/generate-parentheses/solution/gua-hao-sheng-cheng-by-leetcode-solution/
     *
     * @param n n
     * @return list
     */
    public List<String> generateParenthesis2(int n) {
        return generate(n);
    }

}
