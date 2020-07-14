package com.lidong.algorithm.leetcode.medium.array;

/**
 * 交换一次的先前排列（中等-1053）
 * 中文链接：https://leetcode-cn.com/problems/previous-permutation-with-one-swap
 * <p>
 * 问题描述：
 * 给你一个正整数的数组 A（其中的元素不一定完全不同），请你返回可在 一次交换（交换两数字 A[i] 和 A[j] 的位置）后得到的、按字典序排列小于 A 的最大可能排列。
 * 如果无法这么操作，就请返回原数组。
 * <p>
 * 示例 1：
 * 输入：[3,2,1]
 * 输出：[3,1,2]
 * 解释：
 * 交换 2 和 1
 *  
 * 示例 2：
 * 输入：[1,1,5]
 * 输出：[1,1,5]
 * 解释：
 * 这已经是最小排列
 *  
 * 示例 3：
 * 输入：[1,9,4,6,7]
 * 输出：[1,7,4,6,9]
 * 解释：
 * 交换 9 和 7
 *  
 * 示例 4：
 * 输入：[3,1,1,3]
 * 输出：[1,3,1,3]
 * 解释：
 * 交换 1 和 3
 *  
 * 提示：
 * 1 <= A.length <= 10000
 * 1 <= A[i] <= 10000
 *
 * @author ls J
 * @date 2020/7/14 15:22
 */
public class PreviousPermutationWithOneSwap1053 {

    /**
     * 方法一：while 迭代，先从后往前找第一个最大的数，然后从当前位置往后找最后一个比当前数小的第一个数
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40.8 MB，在所有 Java 提交中击败了 33.33% 的用户
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param arr arr
     * @return new arr
     */
    public int[] prevPermOpt1(int[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return arr;
        }
        int x = n - 2;
        // 从后往前寻找第一个逆序的数字(arr[x] > arr[x + 1])
        while (x >= 0 && arr[x] <= arr[x + 1]) {
            --x;
        }
        // 如果出界了，代表当前已经是最小的数字了
        if (x == -1) {
            return arr;
        }
        // 从第 x + 1 位置开始往后找第一个比 arr[x] 小的元素
        int y = x + 1;
        while (y < n && arr[y] < arr[x]) {
            ++y;
        }
        // y - 1 之后才是找到的数
        --y;
        // 找重复数字的第一个元素
        while (y > x && arr[y] == arr[y - 1]) {
            --y;
        }
        if (y <= x) {
            return arr;
        }
        // 交换
        swap(arr, x, y);
        return arr;
    }

    /**
     * 方法二：for 迭代，思想与方法一基本一致
     * 对于第 i 个元素，查找靠右的剩下元素中既比 i 元素小，又尽量最大的元素，如果存在这样的元素，交换即可得到结果。
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40.8 MB，在所有 Java 提交中击败了 33.33% 的用户
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param arr arr
     * @return new arr
     */
    public int[] prevPermOpt12(int[] arr) {
        int len = arr.length;
        for (int i = len - 2; i >= 0; --i) {
            int max = i + 1;
            for (int j = i + 1; j < len; ++j) {
                if (arr[j] > arr[max] && arr[j] < arr[i]) {
                    max = j;
                }
            }

            if (arr[i] > arr[max]) {
                swap(arr, i, max);
                return arr;
            }
        }
        return arr;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
