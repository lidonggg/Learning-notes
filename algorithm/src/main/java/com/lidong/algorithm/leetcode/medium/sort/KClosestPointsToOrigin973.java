package com.lidong.algorithm.leetcode.medium.sort;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 最接近原点的 K 个点（中等-973）
 * 中文链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
 * <p>
 * 问题描述：
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * <p>
 * 示例 1：
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 * <p>
 * 示例 2：
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 *  
 * 提示：
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 *
 * @author Ls J
 * @date 2020/7/4 2:18 PM
 */
public class KClosestPointsToOrigin973 {

    /**
     * 方法一：构造大顶堆（可以根据 k 与 n/2 比较的大小来决定大顶堆还是小顶堆，以减少左/右旋成本）
     * <p>
     * 执行用时：84 ms，在所有 Java 提交中击败了 9.66% 的用户
     * 内存消耗：48.6 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param points points
     * @param k      k
     * @return int[][]
     */
    public int[][] kClosest(int[][] points, int k) {
        int n = points.length;

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> Double.compare(Math.pow(b[0], 2) + Math.pow(b[1], 2), Math.pow(a[0], 2) + Math.pow(a[1], 2))
        );

        maxHeap.addAll(Arrays.asList(points));

        for (int i = 0; i < n - k; ++i) {
            maxHeap.poll();
        }

        int[][] res = new int[k][2];
        for (int i = 0; i < k; ++i) {
            res[i] = maxHeap.poll();
        }

        return res;
    }

    private int[][] points;

    /**
     * 方法二：快速选择（快速排序思想）
     * <p>
     * 执行用时：5 ms，在所有 Java 提交中击败了 96.45% 的用户
     * 内存消耗：49.1 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param points points
     * @param k      k
     * @return int[][]
     */
    public int[][] kClosest2(int[][] points, int k) {
        // 最终的结果就是分区之后的前 k 个元素
        this.points = points;
        quickSelect(0, points.length - 1, k);
        return Arrays.copyOfRange(points, 0, k);
    }

    private void quickSelect(int l, int r, int k) {
        if (l >= r) {
            return;
        }
        int p = partition(l, r);
        // 如果 p 的索引刚好是 k，则它之前的所有元素就是所需的结果列表
        if (p == k) {
            return;
        }
        if (p > k) {
            quickSelect(l, p - 1, k);
        } else {
            quickSelect(p + 1, r, k);
        }
    }

    /**
     * 分区
     *
     * @param l 左边界
     * @param r 右边界
     * @return index
     */
    private int partition(int l, int r) {
        // 随机选取，这里为了方便，选取最后一个作为分区值
        int pivot = dist(r);

        int p = l;

        for (int i = l; i < r; ++i) {
            if (dist(i) <= pivot) {
                if (p != i) {
                    swap(p, i);
                }
                p++;
            }
        }
        swap(p, l);
        return p;
    }

    private void swap(int i, int j) {
        int t0 = points[i][0], t1 = points[i][1];
        points[i][0] = points[j][0];
        points[i][1] = points[j][1];
        points[j][0] = t0;
        points[j][1] = t1;
    }

    public int dist(int i) {
        return points[i][0] * points[i][0] + points[i][1] * points[i][1];
    }
}
