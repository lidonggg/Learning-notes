package com.lidong.algorithm.leetcode.middling.dynamic;

/**
 * @author ls J
 * @date 2020/4/30 20:04
 * 统计全为 1 的正方形子矩阵（中等-1277）
 * 问题描述：
 * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的正方形子矩阵的个数。
 * <p>
 * 示例 1：
 * 输入：matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * 输出：15
 * 解释：
 * 边长为 1 的正方形有 10 个。
 * 边长为 2 的正方形有 4 个。
 * 边长为 3 的正方形有 1 个。
 * 正方形的总数 = 10 + 4 + 1 = 15.
 * <p>
 * 示例 2：
 * 输入：matrix =
 * [
 *   [1,0,1],
 *   [1,1,0],
 *   [1,1,0]
 * ]
 * 输出：7
 * 解释：
 * 边长为 1 的正方形有 6 个。
 * 边长为 2 的正方形有 1 个。
 * 正方形的总数 = 6 + 1 = 7.
 */
public class CountSquares1277 {

    /**
     * 从左上角开始看以 a[x][y] 为左上角的子矩阵的最大全为 1 的矩阵，假设最大边长为 k，这样以该点为左上顶点的目标矩阵一共有 k 个
     *
     * @param matrix 原矩阵
     * @return res
     */
    public static int countSquares(int[][] matrix) {
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        int res = 0;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                res += countMaxSquaresWithLtx(matrix, i, j, m, n);
            }
        }
        return res;
    }

    /**
     * 检查以当前顶点为左上角的最大的全为 1 的矩阵，这样，以该顶点为左上角的目标矩阵一共有这么多个
     *
     * @param matrix 原矩阵
     * @param x      当前点所在行数
     * @param y      当前点所在列数
     * @param m      矩阵总行数
     * @param n      矩阵总列数
     * @return 目标矩阵个数
     */
    private static int countMaxSquaresWithLtx(int[][] matrix, int x, int y, int m, int n) {
        if (matrix[x][y] == 0) {
            return 0;
        }
        if (x == m || y == n) {
            return matrix[x][y] == 1 ? 1 : 0;
        }
        // 最大勘测深度
        // 找出以当前点为左上顶点所能构成的最大正方形
        int maxLen = Math.min(m - x, n - y);
        int res = 1;
        // 检查边长为 i 时，最外层是否全为 1 ，如果全是 1 ，那么边长加一，继续检查最外层
        // 否则，结束检查，返回已找到的边长最大值
        for (int i = 1; i <= maxLen; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (matrix[x + i][y + j] == 0 || matrix[x + j][y + i] == 0) {
                    return res;
                }
            }
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 0, 1},
                {1, 1, 0},
                {1, 1, 0}
        };

        System.out.println(countSquares(arr));
    }
}
