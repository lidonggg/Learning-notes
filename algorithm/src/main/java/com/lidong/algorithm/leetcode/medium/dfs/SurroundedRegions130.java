package com.lidong.algorithm.leetcode.medium.dfs;

/**
 * 被围绕的区域（中等-130）
 * 中文链接：https://leetcode-cn.com/problems/surrounded-regions/
 * <p>
 * 问题描述：
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * <p>
 * 示例:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * @author ls J
 * @date 2020/6/19 9:50
 */
public class SurroundedRegions130 {

    private final static int[] DX = {-1, 0, 1, 0};

    private final static int[] DY = {0, 1, 0, -1};

    /**
     * dfs
     * <p>
     * 执行用时：2 ms，在所有 Java 提交中击败了 98.07% 的用户
     * 内存消耗：42.2 MB，在所有 Java 提交中击败了 56.25% 的用户
     *
     * @param board board
     */
    public void solve(char[][] board) {
        int m = board.length;
        if (m == 0) {
            return;
        }
        int n = board[0].length;
        if (n == 0) {
            return;
        }
        // 从四个边界的 'O' 开始遍历，将所有可达的 'O' 都变成 'Y'，代表此位置可以被遍历到
        for (int i = 0; i < m; ++i) {
            if (board[i][0] == 'O') {
                dfs(i, 0, m, n, board);
            }
            if (board[i][n - 1] == 'O') {
                dfs(i, n - 1, m, n, board);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (board[0][i] == 'O') {
                dfs(0, i, m, n, board);
            }
            if (board[m - 1][i] == 'O') {
                dfs(m - 1, i, m, n, board);
            }
        }
        // 数组恢复
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'Y') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    /**
     * dfs 遍历
     * 遍历从四条边所有可达的 'O'，这样遍历结束之后，剩下的 'O' 就是被 'X' 包围的
     *
     * @param x     x
     * @param y     y
     * @param m     m
     * @param n     n
     * @param board board
     */
    private void dfs(int x, int y, int m, int n, char[][] board) {
        if (x < 0 || y < 0 || x >= m || y >= n || board[x][y] != 'O') {
            return;
        }

        if (board[x][y] == 'O') {
            board[x][y] = 'Y';
        }

        for (int i = 0; i < 4; ++i) {
            int nx = x + DX[i], ny = y + DY[i];
            dfs(nx, ny, m, n, board);
        }
    }
}
