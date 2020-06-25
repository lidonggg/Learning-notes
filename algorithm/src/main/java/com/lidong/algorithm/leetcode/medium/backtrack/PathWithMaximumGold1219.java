package com.lidong.algorithm.leetcode.medium.backtrack;

/**
 * 黄金矿工（中等-1219）
 * 中文链接：https://leetcode-cn.com/problems/path-with-maximum-gold/
 * <p>
 * 问题描述：
 * 你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。
 * 为了使收益最大化，矿工需要按以下规则来开采黄金：
 * 1.每当矿工进入一个单元，就会收集该单元格中的所有黄金。
 * 2.矿工每次可以从当前位置向上下左右四个方向走。
 * 3.每个单元格只能被开采（进入）一次。
 * 4.不得开采（进入）黄金数目为 0 的单元格。
 * 5.矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。
 * <p>
 * 示例 1：
 * 输入：grid = [[0,6,0],[5,8,7],[0,9,0]]
 * 输出：24
 * 解释：
 * [[0,6,0],
 * [5,8,7],
 * [0,9,0]]
 * 一种收集最多黄金的路线是：9 -> 8 -> 7。
 * <p>
 * 示例 2：
 * 输入：grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
 * 输出：28
 * 解释：
 * [[1,0,7],
 * [2,0,6],
 * [3,4,5],
 * [0,3,0],
 * [9,0,20]]
 * 一种收集最多黄金的路线是：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7。
 *  
 * 提示：
 * 1 <= grid.length, grid[i].length <= 15
 * 0 <= grid[i][j] <= 100
 * 最多 25 个单元格中有黄金。
 *
 * @author Ls J
 * @date 2020/6/25 6:01 PM
 */
public class PathWithMaximumGold1219 {

    private int[] dx = {-1, 0, 1, 0};

    private int[] dy = {0, 1, 0, -1};

    /**
     * 很基本的 dfs + 回溯
     * 执行用时：24 ms，在所有 Java 提交中击败了 49.90% 的用户
     * 内存消耗：37.5 MB，在所有 Java 提交中击败了 16.67% 的用户
     *
     * @param grid grid
     * @return res
     */
    public int getMaximumGold(int[][] grid) {
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
                if (grid[i][j] != 0) {
                    res = Math.max(res, backtrack(i, j, m, n, grid));
                }
            }
        }

        return res;
    }

    /**
     * dfs + backtrack
     *
     * @param x    x
     * @param y    y
     * @param m    m
     * @param n    n
     * @param grid grid
     * @return cur max
     */
    private int backtrack(int x, int y, int m, int n, int[][] grid) {
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
            return 0;
        }
        int num = grid[x][y];
        grid[x][y] = 0;
        int res = 0;
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i], ny = y + dy[i];
            res = Math.max(res, num + backtrack(nx, ny, m, n, grid));
        }
        grid[x][y] = num;
        return res;
    }
}
