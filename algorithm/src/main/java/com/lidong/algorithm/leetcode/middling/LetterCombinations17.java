package com.lidong.algorithm.leetcode.middling;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ls J
 * @date 2019/7/19 9:41 AM
 * 电话号码的字母组合（中等-17）
 * 问题描述：
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母:
 * 2-abc
 * 3-def
 * 4-ghi
 * 5-jkl
 * 6-mno
 * 7-pqrs
 * 8-tuv
 * 9-wxyz
 * <p>
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * <p>
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 * <p>
 */
public class LetterCombinations17 {

    private List<String> result = new LinkedList<>();

    private int[] intDigits;

    private int digitsLength;

    private static char[][] letters = {
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };

    private static Pattern pattern = Pattern.compile("^[2-9]+$");

    public List<String> letterCombinations(String digits) {
        Matcher matcher = pattern.matcher(digits);
        if (!matcher.find() || digits.length() == 0) {
            return result;
        }
        digitsLength = digits.length();
        intDigits = new int[digitsLength];
        for (int i = 0; i < digitsLength; ++i) {
            // 映射到数组中，由于是从2开始的，所以这里根据ASCII码减50
            intDigits[i] = digits.charAt(i) - 50;
        }

        for (int i = 0; i < letters[intDigits[0]].length; ++i) {
            doRecursive("", 0, i);
        }

        return result;
    }

    /**
     * 递归
     *
     * @param tmp     临时字符串
     * @param cdIndex 当前的字符在原字符串中的位置
     * @param clIndex 当前数字对应的某个字符
     */
    public void doRecursive(String tmp, int cdIndex, int clIndex) {
        tmp += letters[intDigits[cdIndex]][clIndex];
        if (tmp.length() == digitsLength) {
            // 代表找出了一组，添加至结果列表中
            result.add(tmp);
            return;
        }

        if (cdIndex < digitsLength - 1) {
            ++cdIndex;
            for (int i = 0; i < letters[intDigits[cdIndex]].length; ++i) {
                doRecursive(tmp, cdIndex, i);
            }
        }
    }

    public static void main(String[] args) {
        LetterCombinations17 lc = new LetterCombinations17();
        System.out.println(lc.letterCombinations(""));
    }
}
