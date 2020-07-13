package com.lidong.algorithm.leetcode.medium.bfs;

import java.util.*;

/**
 * 太平洋大西洋水流问题（中等-417）
 * 中文链接：https://leetcode-cn.com/problems/pacific-atlantic-water-flow
 * <p>
 * 问题描述：
 * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
 * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 * <p>
 * 提示：
 * 1.输出坐标的顺序不重要
 * 2.m 和 n 都小于150
 * <p>
 * 示例：
 * <p>
 * 给定下面的 5x5 矩阵:
 * 太平洋 ~   ~   ~   ~   ~
 * <p>~  1   2   2   3  (5) *
 * <p>~  3   2   3  (4) (4) *
 * <p>~  2   4  (5)  3   1  *
 * <p>~ (6) (7)  1   4   5  *
 * <p>~ (5)  1   1   2   4  *
 * <p>*   *   *   *   * 大西洋
 * 返回:
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
 *
 * @author ls J
 * @date 2020/7/13 15:30
 */
public class PacificAtlanticWaterFlow417 {

    private int[] dx = {-1, 0, 1, 0};

    private int[] dy = {0, 1, 0, -1};

    /**
     * 方法一：从两部分分别 bfs，然后查找能流到每个大洋的交集
     * <p>
     * 执行用时：16 ms，在所有 Java 提交中击败了 17.31% 的用户
     * 内存消耗：40.8 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param matrix matrix
     * @return list
     */
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) {
            return res;
        }
        int n = matrix[0].length;
        if (n == 0) {
            return res;
        }

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        int[][] pflag = new int[m][n];
        for (int i = 0; i < m; ++i) {
            queue.offer(new int[]{i, 0});
            pflag[i][0] = 1;
        }
        for (int i = 1; i < n; ++i) {
            queue.offer(new int[]{0, i});
            pflag[0][i] = 1;
        }
        // Pacific
        bfs(matrix, m, n, visited, queue, pflag);
        visited = new boolean[m][n];
        int[][] aflag = new int[m][n];
        for (int i = 0; i < m - 1; ++i) {
            queue.offer(new int[]{i, n - 1});
            aflag[i][n - 1] = 1;
        }
        for (int i = 0; i < n; ++i) {
            queue.offer(new int[]{m - 1, i});
            aflag[m - 1][i] = 1;
        }
        // Atlantic
        bfs(matrix, m, n, visited, queue, aflag);
        // 找交集
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (pflag[i][j] == 1 && aflag[i][j] == 1) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void bfs(int[][] matrix, int m, int n, boolean[][] visited, Queue<int[]> queue, int[][] pflag) {
        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            int x = curPos[0], y = curPos[1];
            visited[x][y] = true;
            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    if (matrix[nx][ny] >= matrix[x][y]) {
                        if (pflag[nx][ny] != 1) {
                            pflag[nx][ny] = 1;
                        }
                        if (!visited[nx][ny]) {
                            queue.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
        }
    }

    /**
     * 方法二：一次性 bfs，通过一个状态表来更新
     * <p>
     * 执行用时：9 ms，在所有 Java 提交中击败了 25.70% 的用户
     * 内存消耗：40.5 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param matrix matrix
     * @return list of res
     */
    public List<List<Integer>> pacificAtlantic2(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) {
            return res;
        }
        int n = matrix[0].length;
        if (n == 0) {
            return res;
        }
        Queue<Pair> queue = new LinkedList<>();
        int[][] status = new int[m][n];
        for (int i = 0; i < m; ++i) {
            queue.offer(new Pair(i, 0));
            status[i][0] |= 1;
            queue.offer(new Pair(i, n - 1));
            status[i][n - 1] |= 2;
        }
        for (int i = 0; i < n; ++i) {
            queue.offer(new Pair(0, i));
            status[0][i] |= 1;
            queue.offer(new Pair(m - 1, i));
            status[m - 1][i] |= 2;
        }

        while (!queue.isEmpty()) {
            Pair curPos = queue.poll();
            int x = curPos.x, y = curPos.y;
            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && matrix[nx][ny] >= matrix[x][y]) {
                    if (status[nx][ny] != status[x][y]) {
                        status[nx][ny] |= status[x][y];
                        queue.offer(new Pair(nx, ny));
                    }
                }
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (status[i][j] == 3) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private static class Pair {

        int x;

        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
