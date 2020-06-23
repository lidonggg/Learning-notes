package com.lidong.algorithm.leetcode.medium.binarysearch;

/**
 * 搜索二维矩阵（中等-74）
 * 中文链接：https://leetcode-cn.com/problems/search-a-2d-matrix/
 * <p>
 * 问题描述：
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * <p>
 * 示例 1:
 * 输入:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 * <p>
 * 示例 2:
 * 输入:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 *
 * @author ls J
 * @date 2020/6/23 10:15
 */
public class Search2DMatrix74 {

    /**
     * 二分搜索
     * 这里二维可以当成一维来看
     *
     * @param matrix 原矩阵
     * @param target 目标值
     * @return true if found
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
        // 当成一维
        int l = 0, r = m * n - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            // 查找对应的行列值
            int row = mid / n, col = mid % n;
            if (matrix[row][col] < target) {
                l = mid + 1;
            } else if (matrix[row][col] > target) {
                r = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
