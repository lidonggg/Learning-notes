package com.lidong.algorithm.leetcode.easy;

/**
 * @author ls J
 * @date 2019/7/3 4:05 PM
 * 转换成小写字母（简单-709）
 * 问题描述：实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。
 */
public class ToLowerCase709 {

    public static String toLowerCase(String str) {
        char[] chars = str.toCharArray();

        for(int i = 0 ; i < chars.length; i++){
            if(chars[i] >= 'A' && chars[i] <= 'Z'){
                chars[i] += 32;
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(toLowerCase("AAcA"));
    }
}
