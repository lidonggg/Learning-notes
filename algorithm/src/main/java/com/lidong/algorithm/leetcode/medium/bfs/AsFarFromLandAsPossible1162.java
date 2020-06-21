package com.lidong.algorithm.leetcode.medium.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 地图分析（中等-1162）
 * 中文链接：https://leetcode-cn.com/problems/as-far-from-land-as-possible/
 * <p>
 * 问题描述：
 * 你现在手里有一份大小为 N x N 的「地图」（网格） grid，上面的每个「区域」（单元格）都用 0 和 1 标记好了。
 * 其中 0 代表海洋，1 代表陆地，请你找出一个海洋区域，这个海洋区域到离它最近的陆地区域的距离是最大的。
 * 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x1| + |y0 - y1| 。
 * 如果我们的地图上只有陆地或者海洋，请返回 -1。
 *
 * @author Ls J
 * @date 2020/6/21 11:37 PM
 */
public class AsFarFromLandAsPossible1162 {

    private static class Pair {
        int x;

        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * bfs（多元最短路径）
     * <p>
     * 执行用时：20 ms，在所有 Java 提交中击败了 41.11% 的用户
     * 内存消耗：40.9 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param grid grid
     * @return res
     */
    public int maxDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<Pair> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        int[][] dist = new int[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    queue.add(new Pair(i, j));
                }
            }
        }
        if (queue.isEmpty() || queue.size() == m * n) {
            return -1;
        }
        int res = 0;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int x = pair.x, y = pair.y;
            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && grid[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    dist[nx][ny] = Math.max(dist[nx][ny], dist[x][y] + 1);
                    res = Math.max(res, dist[nx][ny]);
                    queue.add(new Pair(nx, ny));
                }
            }
        }
        return res;
    }
}
