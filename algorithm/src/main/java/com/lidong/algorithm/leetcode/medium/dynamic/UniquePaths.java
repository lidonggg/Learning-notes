package com.lidong.algorithm.leetcode.medium.dynamic;

import java.util.Arrays;

/**
 * 不同路径（中等-62）
 * 一个机器人位于一个 m x n 网格的左上角，
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角，
 * 问总共有多少条不同的路径？
 * <br>
 * 说明：m 和 n 的值均不超过 100。
 * <br>
 * 示例 1:
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * <br>
 * 示例 2:
 * 输入: m = 7, n = 3
 * 输出: 28
 *
 * @author ls J
 * @date 2019/7/26 8:49 AM
 */
public class UniquePaths {

    /**
     * 状态转移方程
     * 每次走到 (x,y) 位置的时候，它前面总有有两种可能的状态 (x-1,y)、(x,y-1)，因此可以得到状态转移方程：
     * f(x,y) = f(x-1,y) + f(x,y-1)，函数 f() 代表走到对应位置的所有可能性
     * <br>
     * 状态转移表
     * 可以定义一个 m * n 的状态转移表states[m][n]，states[x][y] 代表走到 (x,y) 时的所有可能性，
     * 由状态转移方程和分析可知，states[x][y] = states[x-1][y] + states[x][y-1]
     * <br>
     * 空间复杂度：o(m*n)
     * 时间复杂度：o(m*n)
     *
     * @param m m
     * @param n n
     * @return uniquePaths
     */
    public int uniquePaths(int m, int n) {

        int[][] states = new int[m][n];
        // 第一行和第一列特殊处理
        for (int i = 0; i < m; ++i) {
            states[i][0] = 1;
        }
        for (int i = 0; i < n; ++i) {
            states[0][i] = 1;
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                // 对应状态转移方程
                states[i][j] = states[i - 1][j] + states[i][j - 1];
            }
        }
        return states[m - 1][n - 1];
    }

    /**
     * 优化：空间复杂度O(2n)
     * 每次只需要记录 states[i-1][j] 和 states[i][j-1]
     *
     * @param m m
     * @param n n
     * @return uniquePaths
     */
    public int uniquePaths1(int m, int n) {
        int[] pre = new int[n];
        int[] cur = new int[n];
        Arrays.fill(pre, 1);
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[j] = cur[j - 1] + pre[j];
            }
            pre = cur.clone();
        }
        return pre[n - 1];
    }

    /**
     * 优化：空间复杂度O(n)
     *
     * @param m m
     * @param n n
     * @return uniquePaths
     */
    public int uniquePaths2(int m, int n) {
        int[] cur = new int[n];
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[j] += cur[j - 1];
            }
        }
        return cur[n - 1];
    }

    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println(uniquePaths.uniquePaths2(7, 3));
    }
}
