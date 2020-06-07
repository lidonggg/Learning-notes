package com.lidong.algorithm.solution;

/**
 * @author Ls J
 * @date 2020/6/6 9:44 PM
 */
public class Solution {

    /**
     * 将输入的十进制数字转换为对应的二进制字符串和十六进制字符串
     *
     * @param number string字符串 十进制数字字符串
     * @return string字符串
     */
    public static String changeFormatNumber(String number) {
        // 0
        if (number.length() == 0) {
            return "INPUTERROR";
        }
        boolean flag = false;
        if (!number.startsWith("-")) {
            number = "+" + number;
            flag = true;
        }
        int len = number.length();

        long longNum = number.charAt(1) - 48;
        // 非法
        if (longNum <= 0 || longNum > 9) {
            return "INPUTERROR";
        }
        longNum = flag ? longNum : -longNum;
        // 字符串转换成数字
        for (int i = 2; i < len; ++i) {
            int curNum = number.charAt(i) - 48;
            // 非法
            if (curNum < 0 || curNum > 9) {
                return "INPUTERROR";
            }
            // 越界
            if (longNum > 65535 || longNum < -65534) {
                return "NODATA";
            }
            longNum = longNum * 10 + curNum;
        }
        int num = (int) longNum;
        System.out.println(num);
        String binaryStr = flag ? "0" : "1", hexByte = Integer.toHexString(num).toUpperCase();

        System.out.println(Integer.toBinaryString(num));

        return String.format("%s,%s", addInPre(binaryStr, 16, flag), addInPre(hexByte, 4, true));
    }

    private static String addInPre(String s, int maxLen, boolean flag) {
        int len = s.length();
        if (len > maxLen) {
            return s.substring(0, maxLen);
        }
        int toAdd = maxLen - len;
        StringBuilder sb = new StringBuilder();
        if (!flag) {
            sb.append("1");
        } else {
            sb.append("0");
        }
        sb.append("0".repeat(Math.max(0, toAdd - 1)));
        sb.append(s);
        return sb.toString();
    }

    public static void s(String s) {
        s = "world";
    }

    public static void main(String[] args) {
        System.out.println(changeFormatNumber("32767"));

        System.out.println(addInPre("1111111111111111111111111111", 16, false));

        System.out.println(Integer.toHexString(-1));

        String s1 ="hello";
        String s2 = "hello";
        char[] c = new char[]{'h','e','l','l','o'};
        // false
        System.out.println(s1.equals(c));
        s(s1);
        System.out.println(s1);
    }
}
