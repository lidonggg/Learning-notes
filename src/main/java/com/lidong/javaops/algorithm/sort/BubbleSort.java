package com.lidong.javaops.algorithm.sort;

import com.lidong.javaops.algorithm.common.ArrayFactory;
import com.lidong.javaops.algorithm.util.ArrayUtil;
import com.lidong.javaops.algorithm.common.impl.ArrayForSort;

/**
 * @author dlif
 * @version 2019/4/29 15:20
 * 冒泡排序，时间复杂度O(n^2)，稳定，原地排序
 */
public class BubbleSort implements SortFactory {

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
        ArrayFactory arrayFactory = new ArrayForSort();
        int[] arr = arrayFactory.createArray();
        ArrayUtil.printArray(arr);
        bubbleSort(arr);
        System.out.println("排序后的数组为：");
        ArrayUtil.printArray(arr);
    }
}
