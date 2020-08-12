package com.lidong.algorithm.leetcode.medium.string;

/**
 * 字符串相乘（中等-43）
 * 中文链接：https://leetcode-cn.com/problems/multiply-strings
 * <p>
 * 问题描述：
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * <p>
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * <p>
 * 说明：
 * 1. num1 和 num2 的长度小于110。
 * 2. num1 和 num2 只包含数字 0-9。
 * 3. num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 4. 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * @author Ls J
 * @date 8/13/20 12:59 AM
 */
public class MultiplyStrings43 {

    /**
     * 执行用时：13 ms，在所有 Java 提交中击败了 35.23% 的用户
     * 内存消耗：40 MB，在所有 Java 提交中击败了 41.50% 的用户
     *
     * @param num1 num1
     * @param num2 num2
     * @return num str
     */
    public static String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        String[] its = new String[n];
        for (int i = n - 1; i >= 0; --i) {
            its[i] = calcOneBit(num1, m, n, num2.charAt(i) - '0', i);
        }
        // 加法进位
        int left = 0;
        StringBuilder sb = new StringBuilder();
        // 分别将每个数字上的每一位进行相加
        for (int i = 0; i <= m + n - 1; ++i) {
            int sum = left;
            for (int j = n - 1; j >= 0; --j) {
                sum += its[j].charAt(i) - '0' ;
            }
            left = sum / 10;
            sb.append(sum % 10);
        }
        // 反转
        sb.reverse();
        // 去掉开头的 0
        while (sb.charAt(0) == '0' && sb.length() > 1) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    /**
     * num2 的某一位与 num1 相乘，得到结果
     * 为了处理方便，这里进行加 0 对齐
     * 由于一个 m 位的数与一个 n 位的数相乘最终的结果的长度不会大于 m + n，所以这里默认补齐 m + n 位
     *
     * @param num1 num1
     * @param m    num1.length()
     * @param k    num2 中第 idx 位置的值
     * @param idx  idx
     * @return num str
     */
    private static String calcOneBit(String num1, int m, int n, int k, int idx) {
        StringBuilder sb = new StringBuilder();
        if (k == 0) {
            sb.append("0".repeat(m + n));
            return sb.toString();
        }
        // 乘法进位
        int left = 0;

        sb.append("0".repeat(Math.max(0, n - 1 - idx)));
        for (int i = m - 1; i >= 0; --i) {
            int cn = num1.charAt(i) - '0';
            int mult = cn * k + left;
            left = mult / 10;
            sb.append(mult % 10);
        }
        if (left != 0) {
            sb.append(left);
        }
        // 使对应位对齐
        // 比如：123 * 456
        // 如果不对齐，此方法的返回结果分别是:
        // 123 * 6 = 738
        // 123 * 5 = 615
        // 123 * 4 = 492
        // 这样的话，在做加法的时候处理起来比较麻烦
        // 添加对齐之后，可以变成如下的样子：
        // 000738
        // 00615
        // 0492
        // 这样在做加法的时候，从后往前分别将每个字符串对应位置相加就行了
        //
        // m + idx = m + n - (n - idx)
        while (sb.length() < m + n) {
            sb.append(0);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(multiply("123", "456"));
    }
}
