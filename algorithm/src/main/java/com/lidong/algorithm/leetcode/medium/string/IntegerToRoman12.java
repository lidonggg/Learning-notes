package com.lidong.algorithm.leetcode.medium.string;

/**
 * 整数转罗马数字（中等-12）
 * 中文链接：https://leetcode-cn.com/problems/integer-to-roman
 * <p>
 * 问题描述：
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 * <p>
 * 示例 1:
 * 输入: 3
 * 输出: "III"
 * <p>
 * 示例 2:
 * 输入: 4
 * 输出: "IV"
 * <p>
 * 示例 3:
 * 输入: 9
 * 输出: "IX"
 * <p>
 * 示例 4:
 * 输入: 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * <p>
 * 示例 5:
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * @author Ls J
 * @date 2020/7/12 6:37 PM
 */
public class IntegerToRoman12 {

    /**
     * 方法一：贪心
     * <p>
     * 执行用时：5 ms，在所有 Java 提交中击败了 84.65% 的用户
     * 内存消耗：39 MB，在所有 Java 提交中击败了 9.26% 的用户
     * <p>
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     *
     * @param num num
     * @return rm
     */
    public String intToRoman(int num) {
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] rms = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 13; ++i) {
            while (num / nums[i] > 0) {
                sb.append(rms[i]);
                num = num - nums[i];
            }
        }
        return sb.toString();
    }

    /**
     * 方法二：硬编码
     * <p>
     * 执行用时：19 ms，在所有 Java 提交中击败了 6.99% 的用户
     * 内存消耗：39.9 MB，在所有 Java 提交中击败了 5.55% 的用户
     * <p>
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     *
     * @param num num
     * @return rm
     */
    public String intToRoman2(int num) {
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[num / 1000] + hundreds[num % 1000 / 100] + tens[num % 100 / 10] + ones[num % 10];
    }
}
