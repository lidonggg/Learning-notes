package com.lidong.javaops.algorithm.sort;

/**
 * @author Ls J
 * @date 2019/4/30 9:14 AM
 * 桶排序，时间复杂度O(n)，稳定的，非原地排序
 * 将数据分成n个部分，每部分分别排序，然后再合并
 */
public class BucketSort implements SortFactory {

    public void sort(int[] arr) {
        bucketSort(arr);
    }

    private static void bucketSort(int[] arr){

    }

    public static void main(String[] args) {
        SortTest.sortTest(new BucketSort());
    }
}
