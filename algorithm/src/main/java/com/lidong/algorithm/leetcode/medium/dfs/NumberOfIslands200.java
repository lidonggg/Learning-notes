package com.lidong.algorithm.leetcode.medium.dfs;

/**
 * 岛屿数量（中等-200）
 * 中文链接：https://leetcode-cn.com/problems/number-of-islands/
 * <p>
 * 问题描述：
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * 示例 1:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 * <p>
 * 示例 2:
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 *
 * @author Ls J
 * @date 2020/6/20 9:33 PM
 */
public class NumberOfIslands200 {

    private static int[] dx = {-1, 0, 1, 0};

    private static int[] dy = {0, 1, 0, -1};

    /**
     * dfs 解法
     * <p>
     * 执行用时：2 ms，在所有 Java 提交中击败了 97.42% 的用户
     * 内存消耗：42.6 MB，在所有 Java 提交中击败了 6.25% 的用户
     *
     * @param grid grid
     * @return num
     */
    public int numIslands(char[][] grid) {
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
                // 每遇到 1，就开始 dfs
                if (grid[i][j] == '1') {
                    res++;
                    dfs(i, j, m, n, grid);
                }
            }
        }
        return res;
    }

    /**
     * dfs
     *
     * @param x    当前行
     * @param y    当前列
     * @param m    总行
     * @param n    总列
     * @param grid grid
     */
    private void dfs(int x, int y, int m, int n, char[][] grid) {
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '0') {
            return;
        }
        // 将其变成 0，代表当前岛屿连通
        grid[x][y] = '0';
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i], ny = y + dy[i];
            dfs(nx, ny, m, n, grid);
        }
    }
}
