package com.lidong.javaops.algorithm.util;

/**
 * @author Ls J
 * @version 2019/4/29 15:09
 */
public class ArrayUtil {
    /**
     * 数组打印
     *
     * @param arr
     */
    public static void printArray(int[] arr) {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    /**
     * 交换两个元素
     * @param arr 目标数组
     * @param first 第一个元素的位置
     * @param second 第二个元素的位置
     */
    public static void swap(int[] arr, int first, int second) {
        int tmp = arr[first];
        arr[first] = arr[second];
        arr[second] = tmp;
    }
}
