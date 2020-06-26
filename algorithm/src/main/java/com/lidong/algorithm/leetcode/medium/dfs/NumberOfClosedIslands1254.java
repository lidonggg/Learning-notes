package com.lidong.algorithm.leetcode.medium.dfs;

/**
 * 统计孤岛的数量（中等-1254）
 * 中文链接：https://leetcode-cn.com/problems/number-of-closed-islands/
 * <p>
 * 问题描述：
 * 有一个二维矩阵 grid ，每个位置要么是陆地（记号为 0），要么是水域（记号为 1）。
 * 我们从一块陆地出发，每次可以往上下左右 4 个方向相邻区域走，能走到的所有陆地区域，我们将其称为一座「岛屿」。
 * 如果一座岛屿 完全 由水域包围，即陆地边缘上下左右所有相邻区域都是水域，那么我们将其称为 「封闭岛屿」。
 * 请返回封闭岛屿的数目。
 * <p>
 * 提示：
 * 1）1 <= grid.length, grid[0].length <= 100
 * 2）0 <= grid[i][j] <=1
 *
 * @author ls J
 * @date 2020/6/17 15:05
 */
public class NumberOfClosedIslands1254 {

    private int[][] grid;

    /**
     * 执行用时：2 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40.2 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param grid grid
     * @return res
     */
    public int closedIsland(int[][] grid) {
        int m;
        if ((m = grid.length) == 0) {
            return 0;
        }
        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }
        this.grid = grid;
        int res = 0;
        // 上下左右边界其实不用考虑了
        for (int i = 1; i < m - 1; ++i) {
            for (int j = 1; j < n - 1; ++j) {
                if (grid[i][j] == 0 && dfs(i, j, m, n)) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * dfs
     *
     * @param x 横坐标
     * @param y 纵坐标
     * @param m m
     * @param n n
     * @return true if found one
     */
    private boolean dfs(int x, int y, int m, int n) {
        // 一旦找到了一个出口，就返回 false
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return false;
        }
        if (grid[x][y] == 1) {
            return true;
        }
        // 避免对同一个点重复访问（将相连的岛屿都染色）
        grid[x][y] = 1;
        // 之所以不直接 return 四个 dfs 的 &&
        // 是因为这里要确保所有相连的岛屿都能被遍历到
        boolean up = dfs(x - 1, y, m, n);
        boolean down = dfs(x + 1, y, m, n);
        boolean left = dfs(x, y - 1, m, n);
        boolean right = dfs(x, y + 1, m, n);

        // 如果上下左右都找到了 1（也就是从某个点开始走不到边界），那么说明是一个孤岛
        return up && down && left && right;
    }

    public static void main(String[] args) {
        NumberOfClosedIslands1254 noi = new NumberOfClosedIslands1254();
        int[][] grid = {
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {1, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 0}
        };
        System.out.println(noi.closedIsland(grid));
    }
}
