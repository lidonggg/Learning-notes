package com.lidong.algorithm.leetcode.interview.easy;

/**
 * 斐波那契数列第 n 项的值（简单-面试题10-1）
 * 问题描述：
 * 面试题10- I. 斐波那契数列
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 * <p>
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * <p>
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * <p>
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：n = 5
 * 输出：5
 * <p>
 * 提示：
 * 0 <= n <= 100
 *
 * @author ls J
 * @date 2020/4/28 21:28
 */
public class Fibonacci101 {

    /**
     * 基于递归的实现
     *
     * @param n n
     * @return res
     */
    public static int getFibonacciN1(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return (getFibonacciN1(n - 1) + getFibonacciN1(n - 2)) % 1000000007;
    }

    /**
     * 基于动态规划的实现
     *
     * @param n n
     * @return res
     */
    public static int getFibonacciN2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int x = 1, y = 1;
        int res = 1;
        for (int i = 3; i <= n; ++i) {
            res = (x + y) % 1000000007;
            x = y;
            y = res;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(getFibonacciN1(45));
    }
}
