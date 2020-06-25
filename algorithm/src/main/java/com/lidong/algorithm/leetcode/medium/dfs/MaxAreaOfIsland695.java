package com.lidong.algorithm.leetcode.medium.dfs;

/**
 * 岛屿的最大面积（中等-695）
 * 中文链接：https://leetcode-cn.com/problems/max-area-of-island/
 * <p>
 * 问题描述：
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 * <p>
 * 示例 1:
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 * <p>
 * 示例 2:
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 * <p>
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 *
 * @author Ls J
 * @date 2020/6/26 12:34 AM
 */
public class MaxAreaOfIsland695 {

    private int[] dx = {-1, 0, 1, 0};

    private int[] dy = {0, 1, 0, -1};

    /**
     * 方法一：dfs
     * <p>
     * 执行用时：3 ms，在所有 Java 提交中击败了 69.48% 的用户
     * 内存消耗：40.2 MB，在所有 Java 提交中击败了 91.67% 的用户
     *
     * @param grid grid
     * @return max area
     */
    public int maxAreaOfIsland(int[][] grid) {

        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res = Math.max(res, dfs(i, j, m, n, grid));
            }
        }

        return res;
    }

    /**
     * dfs
     *
     * @param x    x
     * @param y    y
     * @param m    m
     * @param n    n
     * @param grid grid
     * @return cur max
     */
    private int dfs(int x, int y, int m, int n, int[][] grid) {
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
            return 0;
        }
        grid[x][y] = 0;
        int res = 1;
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i], ny = y + dy[i];
            res += dfs(nx, ny, m, n, grid);
        }
        return res;
    }
}
