package com.lidong.algorithm.leetcode.middling.dynamic;

/**
 * 下降路径最小和（中等-931）
 * 问题描述：
 * 给定一个方形整数数组 A，我们想要得到通过 A 的下降路径的最小和。
 * 下降路径可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列。
 * <p>
 * 示例：
 * 输入：[[1,2,3],[4,5,6],[7,8,9]]
 * 输出：12
 * 解释：
 * 可能的下降路径有：
 * [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
 * [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
 * [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
 * 和最小的下降路径是 [1,4,7]，所以答案是 12。
 * <p>
 * 提示：
 * 1 <= A.length == A[0].length <= 100
 * -100 <= A[i][j] <= 100
 *
 * @author ls J
 * @date 2019/9/2 5:46 PM
 */
public class MinFallingPathSum931 {

    public static int minFallingPathSum(int[][] arr) {
        int len = arr[0].length;
        // 原地
        for (int i = 1; i < len; ++i) {
            // 左边界特殊处理
            arr[i][0] = Math.min(arr[i - 1][0], arr[i - 1][1]) + arr[i][0];
            for (int j = 1; j < len - 1; ++j) {
                // 状态转移方程
                // 当前位置由两三个地方转换过来，arr[i-1][j-1]，arr[i][j-1], arr[i+1][j-1]
                arr[i][j] = Math.min(arr[i - 1][j - 1], Math.min(arr[i - 1][j], arr[i - 1][j + 1])) + arr[i][j];
            }
            // 右边界特殊处理
            arr[i][len - 1] = Math.min(arr[i - 1][len - 2], arr[i - 1][len - 1]) + arr[i][len - 1];
        }
        int res = arr[len - 1][0];
        for (int i = 1; i < len; ++i) {
            res = res > arr[len - 1][i] ? arr[len - 1][i] : res;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(minFallingPathSum(arr));
    }
}
