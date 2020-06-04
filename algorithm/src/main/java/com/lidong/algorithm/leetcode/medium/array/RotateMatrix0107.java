package com.lidong.algorithm.leetcode.medium.array;

/**
 * 旋转矩阵（中等-面试题01.07）
 * 中文链接：https://leetcode-cn.com/problems/rotate-matrix-lcci/
 * <p>
 * 问题描述：
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * 在原地进行旋转能否做到？
 * <p>
 * 示例 1:
 * 给定 matrix =
 * [
 *  [1,2,3],
 *  [4,5,6],
 *  [7,8,9]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 *  [7,4,1],
 *  [8,5,2],
 *  [9,6,3]
 * ]
 * <p>
 * 示例 2:
 * 给定 matrix =
 * [
 *  [ 5, 1, 9,11],
 *  [ 2, 4, 8,10],
 *  [13, 3, 6, 7],
 *  [15,14,12,16]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 *  [15,13, 2, 5],
 *  [14, 3, 4, 1],
 *  [12, 6, 8, 9],
 *  [16, 7,10,11]
 * ]
 * <p>
 *
 * @author ls J
 * @date 2020/5/9 13:50
 */
public class RotateMatrix0107 {

    /**
     * 原地旋转，找规律，每四次旋转其实就是一个环
     * matrix1[col][n−row−1] = matrix[row][col]
     *
     * @param matrix arr
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = tmp;
            }
        }
    }

    /**
     * 方法二：来自 leetcode 题解：https://leetcode-cn.com/problems/rotate-matrix-lcci/solution/xuan-zhuan-ju-zhen-by-leetcode-solution/
     * 用翻转代替旋转，先通过水平轴进行翻转，再根据主对角线进行翻转
     *
     * @param matrix arr
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        int tmp;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                tmp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = tmp;
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }
}
