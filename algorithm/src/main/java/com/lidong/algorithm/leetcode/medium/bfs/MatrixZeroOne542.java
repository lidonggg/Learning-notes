package com.lidong.algorithm.leetcode.medium.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 0-1 矩阵（中等-542）
 * 中文链接：https://leetcode-cn.com/problems/01-matrix/
 * <p>
 * 问题描述：
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 * 两个相邻元素间的距离为 1 。
 * <p>
 * 示例 1:
 * 输入:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 输出:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * <p>
 * 示例 2:
 * 输入:
 * 0 0 0
 * 0 1 0
 * 1 1 1
 * 输出:
 * 0 0 0
 * 0 1 0
 * 1 2 1
 * <p>
 * 注意:
 * 给定矩阵的元素个数不超过 10000。
 * 给定矩阵中至少有一个元素是 0。
 * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
 *
 * @author Ls J
 * @date 2020/6/21 2:23 PM
 */
public class MatrixZeroOne542 {

    private static class Pair {
        int x;

        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int[] dx = {-1, 0, 1, 0};

    private int[] dy = {0, 1, 0, -1};

    /**
     * 方法一：bfs
     * <p>
     * 执行用时：18 ms，在所有 Java 提交中击败了 45.90% 的用户
     * 内存消耗：43.9 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param matrix matrix
     * @return res[][]
     */
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        Queue<Pair> queue = new LinkedList<>();

        int[][] res = new int[m][n];

        // 从 1-0 的距离和 从 0-1 的距离是一样的
        // 我们从每个 0 开始遍历
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    queue.add(new Pair(i, j));
                }
            }
        }

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            for (int i = 0; i < 4; ++i) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    if (matrix[nx][ny] == 1 && res[nx][ny] == 0) {
                        res[nx][ny] = res[pair.x][pair.y] + 1;
                        queue.add(new Pair(nx, ny));
                    }
                }
            }
        }
        return res;
    }

    /**
     * 方法二：动态规划
     * <p>
     * 执行用时：8 ms，在所有 Java 提交中击败了 92.46% 的用户
     * 内存消耗：42.4 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param matrix matrix
     * @return res[][]
     */
    public int[][] updateMatrix2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // 初始化动态规划的数组，所有的距离值都设置为一个很大的数
        int[][] res = new int[m][n];
        // 如果 (i, j) 的元素为 0，那么距离为 0
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    res[i][j] = 0;
                } else {
                    res[i][j] = Integer.MAX_VALUE - 10000000;
                }
            }
        }
        // 只有 水平向左移动 和 竖直向上移动，注意动态规划的计算顺序
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i - 1 >= 0) {
                    res[i][j] = Math.min(res[i][j], res[i - 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    res[i][j] = Math.min(res[i][j], res[i][j - 1] + 1);
                }
            }
        }
        // 只有 水平向右移动 和 竖直向下移动，注意动态规划的计算顺序
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (i + 1 < m) {
                    res[i][j] = Math.min(res[i][j], res[i + 1][j] + 1);
                }
                if (j + 1 < n) {
                    res[i][j] = Math.min(res[i][j], res[i][j + 1] + 1);
                }
            }
        }
        return res;
    }
}
