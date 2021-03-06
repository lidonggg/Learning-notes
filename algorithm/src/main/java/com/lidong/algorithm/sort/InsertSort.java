package com.lidong.algorithm.sort;

/**
 * 插入排序，时间复杂度O(n^2)，空间复杂度O(n)，稳定，原地排序
 *
 * @author Ls J
 * @version 2019/4/29 15:17
 */
public class InsertSort implements SortFactory<int[]> {

    @Override
    public void sort(int[] arr) {
        insertSort(arr);
    }

    private static void insertSort(int[] arr) {
        int len = arr.length;

        for (int i = 1; i < len; i++) {
            int cur = arr[i];
            int j = i - 1;
            // 查找插入的位置
            // 插入之后，此元素之后的元素相应后移一位
            for (; j >= 0; j--) {
                if (arr[j] > cur) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            // 插入
            arr[j + 1] = cur;
        }
    }

    public static void main(String[] args) {
        SortTest.sortTest(new InsertSort());
    }
}
