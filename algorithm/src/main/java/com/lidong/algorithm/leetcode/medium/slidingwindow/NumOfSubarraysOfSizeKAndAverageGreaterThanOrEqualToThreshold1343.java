package com.lidong.algorithm.leetcode.medium.slidingwindow;

/**
 * 大小为 K 且平均值大于等于阈值的子数组数目（中等-1343）
 * 中文链接：https://leetcode-cn.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold
 * <p>
 * 问题描述：
 * 给你一个整数数组 arr 和两个整数 k 和 threshold 。
 * 请你返回长度为 k 且平均值大于等于 threshold 的子数组数目。
 * <p>
 * 示例 1：
 * 输入：arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
 * 输出：3
 * 解释：子数组 [2,5,5],[5,5,5] 和 [5,5,8] 的平均值分别为 4，5 和 6 。其他长度为 3 的子数组的平均值都小于 4 （threshold 的值)。
 * <p>
 * 示例 2：
 * 输入：arr = [1,1,1,1,1], k = 1, threshold = 0
 * 输出：5
 * <p>
 * 示例 3：
 * 输入：arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
 * 输出：6
 * 解释：前 6 个长度为 3 的子数组平均值都大于 5 。注意平均值不是整数。
 * <p>
 * 示例 4：
 * 输入：arr = [7,7,7,7,7,7,7], k = 7, threshold = 7
 * 输出：1
 * <p>
 * 示例 5：
 * 输入：arr = [4,4,4,4], k = 4, threshold = 1
 * 输出：1
 *  
 * 提示：
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^4
 * 1 <= k <= arr.length
 * 0 <= threshold <= 10^4
 *
 * @author ls J
 * @date 2020/7/13 17:57
 */
public class NumOfSubarraysOfSizeKAndAverageGreaterThanOrEqualToThreshold1343 {

    /**
     * 滑动窗口
     * <p>
     * 执行用时：2 ms，在所有 Java 提交中击败了 100.00%  的用户
     * 内存消耗：48.4 MB，在所有 Java 提交中击败了 100.00% 的用户
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param arr       arr
     * @param k         k
     * @param threshold threshold
     * @return num
     */
    public int numOfSubarrays(int[] arr, int k, int threshold) {

        int n = arr.length;
        if (n < k) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < k; ++i) {
            sum += arr[i];
        }
        // 计算最小和
        int minSum = k * threshold;
        int res = 0;

        if (sum >= minSum) {
            res = 1;
        }
        for (int i = k; i < n; ++i) {
            // 减去第一个数，加上最后一个数
            sum = sum - arr[i - k] + arr[i];
            if (sum >= minSum) {
                res++;
            }
        }
        return res;
    }
}
