package com.lidong.algorithm.leetcode.medium.sort;

/**
 * 将矩阵按照对角线排序（中等-1329）
 * 中文链接：https://leetcode-cn.com/problems/sort-the-matrix-diagonally
 * <p>
 * 问题描述：
 * 给你一个 m * n 的整数矩阵 mat ，请你将同一条对角线上的元素（从左上到右下）按升序排序后，返回排好序的矩阵。
 * <p>
 * 示例 1：
 * 输入：mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * 输出：[[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 *  
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * 1 <= mat[i][j] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-the-matrix-diagonally
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ls J
 * @date 2020/7/9 16:11
 */
public class SortMatrixDiagonally1329 {

    /**
     * 快速排序
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40.2 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param mat mat
     * @return res
     */
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        if (m == 0) {
            return mat;
        }
        int n = mat[0].length;
        // 一共有 (m + n - 1) 条对角线
        for (int i = 0; i < n; i++) {
            // 上半部分的对角线最右侧的坐标由 i + m - 1 和 n - 1 中的较小值确定
            int right = Math.min(i + m - 1, n - 1);
            int down = right - i;
            diagonallyQuickSort(mat, 0, i, down, right);
        }
        // i = 0 的情况在上一个 for 循环中已经处理过了，这里直接从 i = 1 开始
        for (int i = 1; i < m; i++) {
            // 下半部分的对角线最下面的坐标由 i + n - 1 和 m - 1 中的较小值确定
            int down = Math.min(i + n - 1, m - 1);
            int right = down - i;
            diagonallyQuickSort(mat, i, 0, down, right);
        }
        return mat;
    }

    /**
     * 快速排序
     *
     * @param mat   mat
     * @param up    up idx
     * @param left  left idx
     * @param down  down idx
     * @param right right idx
     */
    private void diagonallyQuickSort(int[][] mat, int up, int left, int down, int right) {
        // (up, left) 是左上角元素的坐标，(down, right) 是右下角元素的坐标
        if (left > right) {
            return;
        }
        // 选取左上角第一个元素值作为区间分界值
        int pivot = mat[up][left];
        int x1 = up, i = left, x2 = down, j = right;
        while (i != j) {
            while (mat[x2][j] >= pivot && j > i) {
                x2--;
                j--;
            }
            while (mat[x1][i] <= pivot && j > i) {
                x1++;
                i++;
            }
            if (i < j) {
                swap(mat, x1, i, x2, j);
            }
        }
        swap(mat, up, left, x1, i);
        diagonallyQuickSort(mat, up, left, x1 - 1, i - 1);
        diagonallyQuickSort(mat, x1 + 1, i + 1, down, right);
    }

    private void swap(int[][] a, int up, int left, int down, int right) {
        int t = a[up][left];
        a[up][left] = a[down][right];
        a[down][right] = t;
    }
}
