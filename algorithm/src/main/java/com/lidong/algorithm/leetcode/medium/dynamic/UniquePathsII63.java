package com.lidong.algorithm.leetcode.medium.dynamic;

/**
 * 不同路径 II（中等-63）
 * 中文链接：https://leetcode-cn.com/problems/unique-paths-ii/
 * <p>
 * 问题描述：
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * 说明：m 和 n 的值均不超过 100。
 * <p>
 * 示例 1:
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 * @author ls J
 * @date 2020/7/6 10:18
 */
public class UniquePathsII63 {

    /**
     * 方法一：二维 dp 数组
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.1 MB，在所有 Java 提交中击败了 48.15% 的用户
     * <p>
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(m*n)
     *
     * @param obstacleGrid og
     * @return unique path num
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        int m = obstacleGrid.length, n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        // 边界处理
        for (int i = 1; i < m; ++i) {
            // 如果没有出现 1，那么都初始化为 1，因为在边界上到达指定位置只可能有一种情况
            if (obstacleGrid[i][0] == 0) {
                dp[i][0] = 1;
            } else {
                // 一旦遇到 1，就停止
                break;
            }
        }
        // 同理
        for (int i = 1; i < n; ++i) {
            if (obstacleGrid[0][i] == 0) {
                dp[0][i] = 1;
            } else {
                break;
            }
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * 方法二：一维 dp 滚动数组
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：37.9 MB，在所有 Java 提交中击败了 70.37% 的用户
     *
     * @param obstacleGrid og
     * @return unique path num
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid[0].length;
        int[] dp = new int[m];

        dp[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int[] ints : obstacleGrid) {
            for (int j = 0; j < m; ++j) {
                if (ints[j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                if (j - 1 >= 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[m - 1];
    }
}
