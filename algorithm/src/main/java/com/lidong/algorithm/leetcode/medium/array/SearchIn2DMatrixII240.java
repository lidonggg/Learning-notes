package com.lidong.algorithm.leetcode.medium.array;

/**
 * 搜索二维矩阵 II（中等-240）
 * 中文链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 * <p>
 * 问题描述：
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * <p>
 * 示例:
 * <p>
 * 现有矩阵 matrix 如下：
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 *
 * @author ls J
 * @date 2020/6/19 13:15
 */
public class SearchIn2DMatrixII240 {

    /**
     * 执行用时：6 ms，在所有 Java 提交中击败了 99.79% 的用户
     * 内存消耗： 45.5 MB，在所有 Java 提交中击败了 14.29% 的用户
     *
     * @param matrix matrix
     * @param target target
     * @return true if exists
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;
        if (n == 0) {
            return false;
        }
        // 从右上角开始搜索
        int x = 0, y = n - 1;
        while (x <= m - 1 && y >= 0) {
            // 如果当前值比目标值大，说明目标元素只能在它前面的一些列中出现
            if (matrix[x][y] > target) {
                y = y - 1;
            }
            // 如果当前值比目标值大，说明目标元素只能在它下面的一些行中出现
            else if (matrix[x][y] < target) {
                x = x + 1;
            } else {
                return true;
            }
        }

        return false;
    }
}
