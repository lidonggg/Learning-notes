package com.lidong.algorithm.leetcode.hard.binarysearch;

/**
 * 寻找旋转排序数组中的最小值 II（中等-154）
 * 中文链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii
 * <p>
 * 问题描述：
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 请找出其中最小的元素。
 * 注意数组中可能存在重复的元素。
 * <p>
 * 示例 1：
 * 输入: [1,3,5]
 * 输出: 1
 * <p>
 * 示例 2：
 * 输入: [2,2,2,0,1]
 * 输出: 0
 * <p>
 * 说明：
 * 这道题是 寻找旋转排序数组中的最小值 的延伸题目。
 * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 *
 * @author ls J
 * @date 2020/7/22 13:33
 */
public class FindMinimumInRotatedSortedArrayII154 {

    /**
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.8 MB，在所有 Java 提交中击败了 60.00% 的用户
     * <p>
     * 时间复杂度：O(logN)
     * 空间复杂度：O(N)
     *
     * @param numbers nums
     * @return min num
     */
    public int findMin(int[] numbers) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            // 如果当前元素比最右侧元素大，说明它是左半部分数组，需要移动 l
            if (numbers[mid] > numbers[r]) {
                l = mid + 1;
            }
            // 如果当前元素比最右侧元素小，说明它是右半部分，移动 r
            else if (numbers[mid] < numbers[r]) {
                r = mid;
            }
            // 如果当前元素与最右侧元素相等，我们并不能确定最小值在 mid 左侧还是右侧，
            // 但我们可以确定最小值在 r 左侧，所以可以让 r - 1
            // 形如 2 2 2 0 1 2 , mid = 2, r = 5
            else {
                r--;
            }
        }
        return numbers[l];
    }
}
