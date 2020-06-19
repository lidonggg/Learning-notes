package com.lidong.algorithm.leetcode.medium.twopointers;

/**
 * 颜色分类（中等-75）
 * 中文链接：https://leetcode-cn.com/problems/sort-colors/
 * <p>
 * 问题描述：
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 * @author ls J
 * @date 2020/6/19 16:29
 */
public class SortColors75 {

    /**
     * 执行用时： 0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：38.4 MB，在所有 Java 提交中击败了 6.67% 的用户
     *
     * @param nums nums
     */
    public void sortColors(int[] nums) {
        int n = nums.length;

        if (n == 0) {
            return;
        }
        // 一头一尾一中间
        int p0 = 0, cur = 0, p2 = n - 1;
        while (cur <= p2) {
            if (nums[cur] == 1) {
                cur++;
            } else if (nums[cur] == 0) {
                // 如果当前是 0 的话，那么与 p0 位置的元素交换位置，然后往后走一位
                nums[cur++] = nums[p0];
                nums[p0++] = 0;
            } else if (nums[cur] == 2) {
                // 如果当前是 0 的话，那么与 p2 位置的元素交换位置
                // 这里之所以不往后走，是因为交换之前的 nums[p2] 我们并不知道它本来的值是多少
                nums[cur] = nums[p2];
                nums[p2--] = 2;
            }
        }
    }
}
