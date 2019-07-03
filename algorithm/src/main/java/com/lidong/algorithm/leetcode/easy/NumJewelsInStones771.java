package com.lidong.algorithm.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ls J
 * @date 2019/7/3 3:03 PM
 * 宝石与石头（简单-771）
 * 问题描述
 * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 * 示例：
 * 输入：J = "aA", S = "aAAbbbb"
 * 输出：3
 */
public class NumJewelsInStones771 {

    /**
     * 解法很简单，用set存放宝石，然后遍历所有的石头，如果在set中存在，则total+1
     *
     * @param J 宝石
     * @param S 石头
     * @return 宝石数
     */
    public static int numJewelsInStones(String J, String S) {
        if (null == J || null == S) {
            return 0;
        }
        Set<Character> charSet = new HashSet<>();
        int total = 0;
        for (int i = 0; i < J.length(); ++i) {
            charSet.add(J.charAt(i));
        }
        for (int i = 0; i < S.length(); i++) {
            if (charSet.contains(S.charAt(i))) {
                total++;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(numJewelsInStones("Aa", "aaaAcAcva"));
    }
}
