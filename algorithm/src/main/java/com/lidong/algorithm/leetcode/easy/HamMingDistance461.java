package com.lidong.algorithm.leetcode.easy;

/**
 * @author ls J
 * @date 2019/7/11 1:23 PM
 * 汉明距离（简单-461）
 * 问题描述：
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * <p>
 * 注意：
 * 0 ≤ x, y < 2^31.
 */
public class HamMingDistance461 {

    /**
     * 使用异或，得到的结果就是所有对应二进位不同的时候变为1所形成的十进制数字
     *
     * @param x x
     * @param y y
     * @return int
     */
    public static int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    public static void main(String[] args) {
        System.out.print(hammingDistance(4, 1));
    }
}
