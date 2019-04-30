package com.lidong.javaops.algorithm.sort;

import com.lidong.javaops.algorithm.util.ArrayUtil;

/**
 * @author Ls J
 * @version 2019/4/29 16:26
 * 选择排序，时间复杂度O(n^2)，空间复杂度O(1)，不稳定，原地排序
 */
public class SelectSort implements SortFactory {

    @Override
    public void sort(int[] arr) {
        selectSort(arr);
    }

    private static void selectSort(int[] arr) {
        int len = arr.length;

        for (int i = 0; i < len - 1; i++) {
            // 记录未排序数组中最小元素的位置
            int tmp = i;

            // 找到未排序数组中的最小元素的位置
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[tmp]) {
                    tmp = j;
                }
            }

            // 将未排序的第一个元素与未排序中最小的元素交换
            // 此时已排序数组长度+1
            // for example:
            // 5  2  8  4  9  1
            // |
            // 1  2  8  4  9  5
            // |
            // 1  2  8  4  9  5
            // |
            // 1  2  4  8  9  5
            // |
            // 1  2  4  5  9  8
            // |
            // 1  2  4  5  8  9
            if (i != tmp) {
                ArrayUtil.swap(arr, i, tmp);
            }
        }
    }

    public static void main(String[] args) {
        SortTest.sortTest(new SelectSort());
    }
}
