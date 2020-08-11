package com.lidong.algorithm.leetcode.easy.bitmanipulate;

/**
 * 数字转换为十六进制数（简单-405）
 * 中文链接：https://leetcode-cn.com/problems/convert-a-number-to-hexadecimal
 * <p>
 * 问题描述：
 * 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
 * <p>
 * 注意:
 * 1. 十六进制中所有字母(a-f)都必须是小写。
 * 2. 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
 * 3. 给定的数确保在32位有符号整数范围内。
 * 4. 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
 * <p>
 * 示例 1：
 * 输入:
 * 26
 * 输出:
 * "1a"
 * <p>
 * 示例 2：
 * 输入:
 * -1
 * 输出:
 * "ffffffff"
 *
 * @author Ls J
 * @date 8/12/20 12:17 AM
 */
public class ConvertNumberToHexadecimal405 {

    /**
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：37 MB，在所有 Java 提交中击败了 56.00% 的用户
     *
     * @param num num
     * @return str
     */
    public String toHex(int num) {
        char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        StringBuilder sb = new StringBuilder();

        while (num != 0) {
            int ln = num & 15;
            sb.append(hex[ln]);

            num >>>= 4;
        }
        if (sb.length() == 0) {
            return "0";
        }
        return sb.reverse().toString();
    }
}
