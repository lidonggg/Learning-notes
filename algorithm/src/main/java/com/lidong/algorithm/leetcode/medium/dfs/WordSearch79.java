package com.lidong.algorithm.leetcode.medium.dfs;

/**
 * 单词搜索（中等-79）
 * 中文链接：https://leetcode-cn.com/problems/word-search/
 * <p>
 * 问题描述：
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例:
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 * <p>
 * 提示：
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 *
 * @author Ls J
 * @date 2020/6/20 12:09 AM
 */
public class WordSearch79 {

    private String word;

    private char[][] board;

    private boolean[][] marked;

    private int m;

    private int n;

    private int[] dx = {-1, 0, 1, 0};

    private int[] dy = {0, 1, 0, -1};

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        this.m = board.length;
        this.n = board[0].length;
        this.marked = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                boolean res = dfs(i, j, 0);
                if (res) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int x, int y, int curLen) {
        if (curLen == word.length() - 1) {
            return word.charAt(curLen) == board[x][y];
        }
        if (word.charAt(curLen) != board[x][y]) {
            return false;
        }
        marked[x][y] = true;
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && !marked[nx][ny]) {
                if (dfs(nx, ny, curLen + 1)) {
                    return true;
                }
            }
        }
        marked[x][y] = false;
        return false;
    }
}
