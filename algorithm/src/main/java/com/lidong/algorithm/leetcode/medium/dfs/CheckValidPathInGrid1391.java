package com.lidong.algorithm.leetcode.medium.dfs;

/**
 * 检查网格中的有效路径
 * 中文链接：https://leetcode-cn.com/problems/check-if-there-is-a-valid-path-in-a-grid/
 * <p>
 * 问题描述：
 * 给你一个 m x n 的网格 grid。网格里的每个单元都代表一条街道。grid[i][j] 的街道可以是：
 * - 1 表示连接左单元格和右单元格的街道。
 * - 2 表示连接上单元格和下单元格的街道。
 * - 3 表示连接左单元格和下单元格的街道。
 * - 4 表示连接右单元格和下单元格的街道。
 * - 5 表示连接左单元格和上单元格的街道。
 * - 6 表示连接右单元格和上单元格的街道。
 * <p>
 * 你最开始从左上角的单元格 (0,0) 开始出发，网格中的「有效路径」是指从左上方的单元格 (0,0) 开始、一直到右下方的 (m-1,n-1) 结束的路径。该路径必须只沿着街道走。
 * 注意：你不能变更街道。
 * <p>
 * 如果网格中存在有效的路径，则返回 true，否则返回 false 。
 * <p>
 * 示例 1：
 * 输入：grid = [[1,1,2]]
 * 输出：false
 * 解释：你会停在 (0, 1)，而且无法到达 (0, 2) 。
 * <p>
 * 示例 2：
 * 输入：grid = [[1,1,1,1,1,1,3]]
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：grid = [[2],[2],[2],[2],[2],[2],[6]]
 * 输出：true
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * 1 <= grid[i][j] <= 6
 *
 * @author ls J
 * @date 2020/6/18 9:57
 */
public class CheckValidPathInGrid1391 {

    private final static int[] DX = {-1, 0, 1, 0};

    private final static int[] DY = {0, 1, 0, -1};

    /**
     * 转换成 0-1 数组，然后求 1 的连通性就行了
     * 最后一个测试用例超出时间限制，但我觉得这种方法很简洁
     *
     * 执行用时：68 ms，在所有 Java 提交中击败了 10.44% 的用户
     * 内存消耗：79.7 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param grid grid
     * @return true if has a path
     */
    public static boolean hasValidPath(int[][] grid) {
        int m;
        if ((m = grid.length) == 0) {
            return true;
        }
        int n = grid[0].length;
        if (n == 0) {
            return true;
        }

        int mm = 3 * m, nn = 3 * n;
        // 转换成含有 1 的数组，这样的话，每个格子需要用 *3 倍的格子代替
        // 然后只要求 1 的联通性就行了
        //       0 1 0
        // 1 - > 0 1 0
        //       0 1 0
        int[][] nGrid = new int[m * 3][n * 3];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                nGrid[i * 3 + 1][j * 3 + 1] = 1;
                if (grid[i][j] == 1) {
                    nGrid[i * 3 + 1][j * 3] = 1;
                    nGrid[i * 3 + 1][j * 3 + 2] = 1;
                } else if (grid[i][j] == 2) {
                    nGrid[i * 3][j * 3 + 1] = 1;
                    nGrid[i * 3 + 2][j * 3 + 1] = 1;
                } else if (grid[i][j] == 3) {
                    nGrid[i * 3 + 1][j * 3] = 1;
                    nGrid[i * 3 + 2][j * 3 + 1] = 1;
                } else if (grid[i][j] == 4) {
                    nGrid[i * 3 + 2][j * 3 + 1] = 1;
                    nGrid[i * 3 + 1][j * 3 + 2] = 1;
                } else if (grid[i][j] == 5) {
                    nGrid[i * 3][j * 3 + 1] = 1;
                    nGrid[i * 3 + 1][j * 3] = 1;
                } else if (grid[i][j] == 6) {
                    nGrid[i * 3][j * 3 + 1] = 1;
                    nGrid[i * 3 + 1][j * 3 + 2] = 1;
                }
            }
        }
        dfs1(1, 1, mm, nn, nGrid);
        return nGrid[mm - 2][nn - 2] == 0;
    }

    /**
     * dfs
     *
     * @param x     x
     * @param y     y
     * @param m     m
     * @param n     n
     * @param nGrid nGrid
     */
    private static void dfs1(int x, int y, int m, int n, int[][] nGrid) {
        nGrid[x][y] = 0;

        for (int i = 0; i < 4; ++i) {
            int nx = x + DX[i], ny = y + DY[i];
            if (nx > 0 && nx <= m - 1 && ny > 0 && ny <= n - 1 && nGrid[nx][ny] == 1) {
                dfs1(nx, ny, m, n, nGrid);
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                {4, 1, 3},
                {6, 1, 2}
        };
        System.out.println(hasValidPath(grid));
    }
}
