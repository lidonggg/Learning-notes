package com.lidong.algorithm.leetcode.middling.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵（中等-54）
 * 中文链接：https://leetcode-cn.com/problems/spiral-matrix/
 * <p>
 * 问题描述：
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例 1:
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * <p>
 * 示例 2:
 * 输入:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * @author Ls J
 * @date 2020/5/17 8:27 PM
 */
public class SpiralMatrix54 {

    /**
     * 方法一：按路径走，记录当前位置和当前层数，然后判断下一步的方向和层数
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：37.5 MB，在所有 Java 提交中击败了 5.72% 的用户
     *
     * @param matrix matrix
     * @return res
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0) {
            return res;
        }
        int m = matrix.length, n = matrix[0].length;
        // 右、下、左、上
        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // x、y：当前坐标，dir：当前方向处于方向数组中的下标值
        int x = 0, y = 0, dir = 0;
        // i 存放当前已遍历的个数，level 存放当前遍历到的层数，因为我们可以把整个遍历过程看作是按照层次进行遍历（从最外层到最里层）
        // 比如示例2，最外层就是 1 2 3 4 8 12 11 10 9 5，第二层就是 6 7
        // curLevelIdx 存放当前层已遍历的个数
        int i = 0, level = 0, curLevelIdx = 0;
        while (i < m * n) {
            res.add(matrix[x][y]);
            curLevelIdx++;
            // 当前层遍历完成，当前层需要遍历的个数：2*((m-level)+(n-level))-2*2
            if (curLevelIdx == 2 * (m + n - 4 * level - 2)) {
                dir = 0;
                level++;
                curLevelIdx = 0;
            } else {
                // 按照原路径的下一个 x y
                int newX = x + direction[dir][0];
                int newY = y + direction[dir][1];
                // 确保当前的 x y 刚好在当前层上面
                if (newX >= m - level || newX < level || newY >= n - level || newY < level) {
                    dir++;
                }
            }

            x += direction[dir][0];
            y += direction[dir][1];
            i++;
        }
        return res;
    }

    /**
     * 方法二：按层遍历，也用到了上述类似 level 的思想
     * 来自 leetcode 官方题解：https://leetcode-cn.com/problems/spiral-matrix/solution/luo-xuan-ju-zhen-by-leetcode/
     *
     * @param matrix matrix
     * @return res
     */
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0) {
            return res;
        }
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            // 遍历当前层上方
            for (int c = c1; c <= c2; c++) {
                res.add(matrix[r1][c]);
            }
            // 遍历当前层右方
            for (int r = r1 + 1; r <= r2; r++) {
                res.add(matrix[r][c2]);
            }
            if (r1 < r2 && c1 < c2) {
                // 遍历当前层下方
                for (int c = c2 - 1; c > c1; c--) {
                    res.add(matrix[r2][c]);
                }
                // 遍历当前层左方
                for (int r = r2; r > r1; r--) {
                    res.add(matrix[r][c1]);
                }
            }
            // 开始下一层
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return res;
    }


    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}};
        System.out.println(spiralOrder(arr));
    }
}
