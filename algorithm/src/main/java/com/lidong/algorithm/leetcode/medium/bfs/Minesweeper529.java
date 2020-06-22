package com.lidong.algorithm.leetcode.medium.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 扫雷游戏（中等-529）
 * 中文链接：https://leetcode-cn.com/problems/minesweeper/
 * <p>
 * 问题描述：
 * 让我们一起来玩扫雷游戏！
 * 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
 * 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
 * - 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
 * - 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的方块都应该被递归地揭露。
 * - 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
 * - 如果在此次点击中，若无更多方块可被揭露，则返回面板。
 *  
 * 示例 1：
 * 输入:
 * [['E', 'E', 'E', 'E', 'E'],
 * ['E', 'E', 'M', 'E', 'E'],
 * ['E', 'E', 'E', 'E', 'E'],
 * ['E', 'E', 'E', 'E', 'E']]
 * Click : [3,0]
 * 输出:
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'M', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * <p>
 * 示例 2：
 * 输入:
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'M', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * Click : [1,2]
 * 输出:
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'X', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * <p>
 * 注意：
 * 输入矩阵的宽和高的范围为 [1,50]。
 * 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。
 * 输入面板不会是游戏结束的状态（即有地雷已被挖出）。
 * 简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。
 *
 * @author ls J
 * @date 2020/6/22 14:12
 */
public class Minesweeper529 {

    private static class Pair {

        int x;

        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * bfs
     * <p>
     * 执行用时：4 ms，在所有 Java 提交中击败了 27.12% 的用户
     * 内存消耗：40.1 MB，在所有 Java 提交中击败了 75.00% 的用户
     *
     * @param board board
     * @param click click idxs
     * @return board after clicked
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length, n = board[0].length;
        int x = click[0], y = click[1];

        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }
        // 上、下、左、右、右上、右下、左下、左上
        int[] dx = {-1, 0, 1, 0, -1, 1, 1, -1};
        int[] dy = {0, 1, 0, -1, 1, 1, -1, -1};
        boolean[][] added = new boolean[m][n];
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y));
        added[x][y] = true;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            x = pair.x;
            y = pair.y;
            // 首先判断周围有没有雷
            int booms = 0;
            for (int i = 0; i < 8; ++i) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    if (board[nx][ny] == 'M' || board[nx][ny] == 'X') {
                        booms++;
                    }
                }
            }
            // 如果有雷，那么更新当前位置为雷的个数
            if (booms > 0) {
                board[x][y] = (char) (booms + 48);
            } else if (board[x][y] == 'E') {
                // 如果没有雷，那么再循环将周围的元素添加到队列中
                board[x][y] = 'B';
                for (int i = 0; i < 8; ++i) {
                    int nx = x + dx[i], ny = y + dy[i];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && board[nx][ny] == 'E') {
                        if (!added[nx][ny]) {
                            queue.add(new Pair(nx, ny));
                            added[nx][ny] = true;
                        }
                    }
                }
            }
        }

        return board;
    }
}
