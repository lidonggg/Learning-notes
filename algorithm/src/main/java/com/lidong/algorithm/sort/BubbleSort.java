package com.lidong.algorithm.sort;

import com.lidong.algorithm.util.ArrayUtil;

/**
 * @author Ls J
 * @version 2019/4/29 15:20
 * 冒泡排序，时间复杂度O(n^2)，稳定，原地排序
 */
public class BubbleSort implements SortFactory<int[]> {

    @Override
    public void sort(int[] arr) {
        bubbleSort(arr);
    }

    private static void bubbleSort(int[] arr) {
        int len = arr.length;

        for (int i = 0; i < len; i++) {
            // 提前退出冒泡的标志
            boolean flag = false;

            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    ArrayUtil.swap(arr, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        SortTest.sortTest(new BubbleSort());
    }
}
