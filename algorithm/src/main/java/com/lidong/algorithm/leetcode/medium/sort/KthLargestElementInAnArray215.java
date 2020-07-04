package com.lidong.algorithm.leetcode.medium.sort;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * 数组中的第 K 个最大元素（中等-215）
 * 中文链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * <p>
 * 问题描述：
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * <p>
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * @author Ls J
 * @date 2020/7/4 2:21 AM
 */
public class KthLargestElementInAnArray215 {

    Random random = new Random();

    /**
     * 方法一：利用快速排序 partition 思想，找到第 k 个位置的分区点，那么这个值就是所求的目标值（快速选择）
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 99.51% 的用户
     * 内存消耗：40.6 MB，在所有 Java 提交中击败了 6.12% 的用户
     *
     * @param nums nums
     * @param k    k
     * @return target
     */
    public int findKthLargest(int[] nums, int k) {
        // 因为是寻找第 k 大元素，所以就是第 len - k 小的元素
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSelect(int[] nums, int l, int r, int index) {
        int p = randomPartition(nums, l, r);
        if (p == index) {
            return nums[p];
        } else {
            // 如果找到的分区比 index 小，说明目标元素在 nums[q+1...r] 之间
            // 否则在 nums[l...q-1] 之间
            // 注意这里的 index 是 len - k
            return p < index ? quickSelect(nums, p + 1, r, index) : quickSelect(nums, l, p - 1, index);
        }
    }

    private int randomPartition(int[] nums, int l, int r) {
        int i = random.nextInt(r - l + 1) + l;
        swap(nums, i, r);
        return partition(nums, l, r);
    }

    /**
     * 确定分区点
     * 从小到大的顺序排列
     *
     * @param nums nums
     * @param l    left
     * @param r    right
     * @return partition
     */
    private int partition(int[] nums, int l, int r) {
        int x = nums[r], i = l - 1;
        for (int j = l; j < r; ++j) {
            if (nums[j] <= x) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, i + 1, r);
        return i + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 方法二：构造大顶堆
     * 优化：如果 k <= n / 2，构造小顶堆，返回第 n - k 个最小的元素；否则构造大顶堆，返回第 k 个最大元素
     * <p>
     * 执行用时：8 ms，在所有 Java 提交中击败了 36.32% 的用户
     * 内存消耗：40.1 MB，在所有 Java 提交中击败了 6.12% 的用户
     *
     * @param nums nums
     * @param k    k
     * @return target
     */
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();

        for (int n : nums) {
            maxHeap.add(n);
        }

        int n = nums.length;

        for (int i = 0; i < n - k; ++i) {
            maxHeap.poll();
        }

        return maxHeap.poll();
    }
}
