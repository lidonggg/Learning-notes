package com.lidong.algorithm.leetcode.medium.dynamic;

/**
 * 最大正方形（中等-221）
 * 中文链接：https://leetcode-cn.com/problems/maximal-square/
 * <p>
 * 问题描述：
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * <p>
 * 示例:
 * 输入:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * 输出: 4
 *
 * @author Ls J
 * @date 2020/6/27 12:53 PM
 */
public class MaximalSquare221 {

    /**
     * dp
     *
     * 执行用时：6 ms，在所有 Java 提交中击败了 82.19% 的用户
     * 内存消耗：42.6 MB，在所有 Java 提交中击败了 68.75% 的用户
     *
     * @param matrix matrix
     * @return max area
     */
    public int maximalSquare(char[][] matrix) {
        int m, n;
        if (null == matrix || (m = matrix.length) == 0 || (n = matrix[0].length) == 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        int maxSide = 0;
        // 边界处理
        for (int i = 0; i < m; ++i) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                maxSide = Math.max(maxSide, 1);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (matrix[0][i] == '1') {
                dp[0][i] = 1;
                maxSide = Math.max(maxSide, 1);
            }
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][j] == '1') {
                    // dp[i,j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }
}
