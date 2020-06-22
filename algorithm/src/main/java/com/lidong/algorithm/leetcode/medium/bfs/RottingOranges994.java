package com.lidong.algorithm.leetcode.medium.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 腐烂的橘子（中等-994）
 * 中文链接：
 * <p>
 * 问题描述：
 * 在给定的网格中，每个单元格可以有以下三个值之一：
 * 0.值 0 代表空单元格；
 * 1.值 1 代表新鲜橘子；
 * 2.值 2 代表腐烂的橘子。
 * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
 * <p>
 * 示例 1：
 * 输入：[[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * <p>
 * 示例 2：
 * 输入：[[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
 * <p>
 * 示例 3：
 * 输入：[[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 * <p>
 * 提示：
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * grid[i][j] 仅为 0、1 或 2
 *
 * @author ls J
 * @date 2020/6/22 10:04
 */
public class RottingOranges994 {

    private static class Pair {

        int x;

        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * bfs，从每个腐烂的橘子开始 bfs，寻找所有可达的新鲜的橘子，每一层循环，时间都 +1
     * 如果最后还剩下新鲜的橘子，则返回 -1
     * <p>
     * 执行用时：3 ms，在所有 Java 提交中击败了 89.09% 的用户
     * 内存消耗：39.4 MB，在所有 Java 提交中击败了 33.33% 的用户
     *
     * @param grid grid
     * @return minutes
     */
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        Queue<Pair> queue = new LinkedList<>();
        int fresh = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 2) {
                    queue.add(new Pair(i, j));
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        if (fresh == 0) {
            return 0;
        }
        if (queue.isEmpty()) {
            return -1;
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        boolean[][] added = new boolean[m][n];
        int res = 0;
        while (!queue.isEmpty()) {
            int len = queue.size();
            // 判断本层遍历是否能遍历到新鲜的橘子
            boolean flag = false;
            for (int i = 0; i < len; ++i) {
                Pair pair = queue.poll();
                int x = pair.x, y = pair.y;
                for (int j = 0; j < 4; ++j) {
                    int nx = x + dx[j], ny = y + dy[j];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1) {
                        if (!flag) {
                            flag = true;
                        }
                        // 新鲜橘子数 -1
                        fresh--;
                        grid[nx][ny] = 2;
                        if (!added[nx][ny]) {
                            added[nx][ny] = true;
                            queue.add(new Pair(nx, ny));
                        }
                    }
                }
            }
            // 如果没有新鲜橘子剩下了，直接返回
            if (fresh == 0) {
                return ++res;
            }
            // 如果本层遍历一遍之后没有遍历到一次新鲜橘子并且还有剩下的新鲜橘子，说明还有一些橘子不可达
            // 因为本层遍历到的都是已有的腐烂橘子，继续遍历下去也不会再有新的新鲜橘子变腐烂了
            if (!flag) {
                return -1;
            }
            res++;
        }
        // 如果最后还有剩下的，则返回 -1
        if (fresh > 0) {
            return -1;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.print((char) 49);
    }
}
