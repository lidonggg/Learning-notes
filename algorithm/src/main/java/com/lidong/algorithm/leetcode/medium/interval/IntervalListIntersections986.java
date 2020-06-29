package com.lidong.algorithm.leetcode.medium.interval;

import java.util.LinkedList;
import java.util.List;

/**
 * 区间列表的交集（中等-986）
 * 中文链接：https://leetcode-cn.com/problems/interval-list-intersections/
 * <p>
 * 问题描述：
 * 给定两个由一些 闭区间 组成的列表，每个区间列表都是成对不相交的，并且已经排序。
 * 返回这两个区间列表的交集。
 * （形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b。
 * 两个闭区间的交集是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3]。）
 * <p>
 * 示例：
 * 输入：A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
 * 输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 *  
 * 提示：
 * 0 <= A.length < 1000
 * 0 <= B.length < 1000
 * 0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
 *
 * @author ls J
 * @date 2020/6/29 13:35
 */
public class IntervalListIntersections986 {

    /**
     * 通过画图可以直观的看出不同的情况
     * <p>
     * 执行用时：4 ms，在所有 Java 提交中击败了 93.56% 的用户
     * 内存消耗：40.3 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param a a
     * @param b b
     * @return res
     */
    public int[][] intervalIntersection(int[][] a, int[][] b) {
        List<int[]> resList = new LinkedList<>();
        int m = a.length, n = b.length;

        int i = 0, j = 0;
        while (i < m && j < n) {
            int a1 = a[i][0], a2 = a[i][1];
            int b1 = b[j][0], b2 = b[j][1];
            // 这个时候两个区间是相交的
            if (a1 <= b2 && a2 >= b1) {
                int[] item = new int[2];
                item[0] = Math.max(a1, b1);
                item[1] = Math.min(a2, b2);
                resList.add(item);
            }
            // 哪个区间的左端点靠前，则此区间列表需要继续去下一个区间与另外一个区间列表的当前区间作比较
            if (b2 < a2) {
                j++;
            } else {
                i++;
            }
        }

        return resList.toArray(new int[resList.size()][]);
    }
}
