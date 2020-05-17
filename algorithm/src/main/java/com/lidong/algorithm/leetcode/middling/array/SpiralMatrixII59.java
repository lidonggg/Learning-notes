package com.lidong.algorithm.leetcode.middling.array;

/**
 * 螺旋矩阵 II（中等-59）
 * 中文链接：https://leetcode-cn.com/problems/spiral-matrix-ii
 * <p>
 * 问题描述：
 * 给定一个正整数 n，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * <p>
 * 示例:
 * 输入: 3
 * 输出:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 *
 * @author Ls J
 * @date 2020/5/16 9:32 PM
 */
public class SpiralMatrixII59 {

    /**
     * 方法一：模拟过程：状态机，每次根据当前进行的方向和当前的位置以及当前方向附近数据的情况来判断下一跳的方向
     * 时间复杂度：O(n^2)
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：37.8 MB，在所有 Java 提交中击败了 7.14% 的用户
     *
     * @param n n
     * @return arr[][]
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];

        if (n == 0) {
            return res;
        }

        int x = 0, y = 0;
        // 存放当前走的方向，方向的顺序依次是 右->下->左->上 循环
        int curDirection = 0;

        for (int i = 0; i < n * n; ++i) {
            res[x][y] = i + 1;
            // 当前向右走
            if (curDirection == 0) {
                // 如果不是最后一列，并且当前元素右边的元素 == 0，说明此时应该往右边走
                if (y < n - 1 && res[x][y + 1] == 0) {
                    y++;
                }
                // 否则应该向下走
                else {
                    curDirection++;
                    x++;
                }
            }
            // 当前向下走
            else if (curDirection == 1) {
                // 如果不是最后一行，并且当前元素下边的元素 == 0，说明此时应该往下边走
                if (x < n - 1 && res[y][x + 1] == 0) {
                    x++;
                }
                // 否则应该向左走
                else {
                    curDirection++;
                    y--;
                }
            }
            // 当前向左走
            else if (curDirection == 2) {
                // 如果不是第一列，并且当前元素左边的元素 == 0，说明此时应该往左边走走
                if (y > 0 && res[x][y - 1] == 0) {
                    y--;
                }
                // 否则应该向上走
                else {
                    curDirection++;
                    x--;
                }
            } else {
                // 如果不是第一行，并且当前元素上边的元素 == 0，说明此时应该往上边走
                if (x > 0 && res[x - 1][y] == 0) {
                    x--;
                } else {
                    curDirection = 0;
                    y++;
                }
            }
        }

        return res;
    }

    /**
     * 方法二：
     * 来自 leetcode 题解：https://leetcode-cn.com/problems/spiral-matrix-ii/solution/javati-jie-shi-yong-fang-xiang-xiang-liang-ji-suan
     *
     * @param n n
     * @return arr[][]
     */
    public int[][] generateMatrix2(int n) {
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int[][] res = new int[n][n];
        int num = 1, tar = n * n;
        while (num <= tar) {
            for (int i = l; i <= r; i++) {
                // left to right.
                res[t][i] = num++;
            }
            t++;
            for (int i = t; i <= b; i++) {
                // top to bottom.
                res[i][r] = num++;
            }
            r--;
            for (int i = r; i >= l; i--) {
                // right to left.
                res[b][i] = num++;
            }
            b--;
            for (int i = b; i >= t; i--) {
                // bottom to top.
                res[i][l] = num++;
            }
            l++;
        }
        return res;
    }

    /**
     * 方法三：
     * 来自 leetcode 题解：https://leetcode-cn.com/problems/spiral-matrix-ii/solution/javati-jie-shi-yong-fang-xiang-xiang-liang-ji-suan
     *
     * @param n n
     * @return arr[][]
     */
    public int[][] generateMatrix3(int n) {
        // 右、下、左、上
        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // 当前正在记录的数字
        int i = 1;
        // x、y：当前坐标，dir：当前方向处于方向数组中的下标值
        int x = 0, y = 0, dir = 0;
        int[][] matrix = new int[n][n];
        while (i <= n * n) {
            matrix[x][y] = i;
            int newX = x + direction[dir][0];
            int newY = y + direction[dir][1];
            // 需要改变方向的情况
            if (newX >= n || newX < 0 || newY >= n || newY < 0 || matrix[newX][newY] != 0) {
                dir = (dir == 3) ? 0 : dir + 1;
            }
            x += direction[dir][0];
            y += direction[dir][1];
            i++;
        }
        return matrix;
    }
}
