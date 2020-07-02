package com.lidong.algorithm.leetcode.medium.binarysearch;

/**
 * 有序矩阵中第K小的元素（中等-378）
 * 中文链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
 * <p>
 * 问题描述：
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
 * <p>
 * 示例：
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 * 返回 13。
 *  
 * 提示：
 * 你可以假设 k 的值永远是有效的，1 ≤ k ≤ n^2 。
 *
 * @author ls J
 * @date 2020/7/2 15:58
 */
public class KthSmallestElementInASortedMatrix378 {

    /**
     * 二分查找
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：45.6 MB，在所有 Java 提交中击败了 7.69% 的用户
     * <p>
     * 时间复杂度：O(nlog(r−l))，二分查找进行次数为 O(log(r−l))，每次操作时间复杂度为 O(n)
     * 空间复杂度：O(1)
     *
     * @param matrix matrix
     * @param k      k
     * @return target
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int l = matrix[0][0], r = matrix[n - 1][n - 1];
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (check(matrix, n, k, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    /**
     * check 函数
     * 所有不比 mid 大的元素一定在数组的左上角
     *
     * @param matrix matrix
     * @param n      n
     * @param k      k
     * @param mid    mid
     * @return count >= k
     */
    private boolean check(int[][] matrix, int n, int k, int mid) {
        int i = n - 1, j = 0, count = 0;
        while (i >= 0 && j < n) {
            // 从最后一行开始看第一列，如果当前元素比 mid 小，则当前列可以贡献出 (i + 1) 个不比 mid 大的元素
            // 如果当前元素比 mid 小的话，由于每一列都是递增的，那么当前元素上方的每个元素都不比 mid 大
            if (matrix[i][j] <= mid) {
                // 每次都加上当前列所有不大于 mid 的元素个数
                count += i + 1;
                // 然后取看下一列
                j++;
            } else {
                // 否则应该去看上一行了
                i--;
            }
        }
        return count >= k;
    }
}
