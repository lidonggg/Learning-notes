package com.lidong.javaops.algorithm.sort;

/**
 * @author Ls J
 * @date 2019/4/30 9:14 AM
 * 桶排序，时间复杂度O(n)，稳定的，非原地排序
 * 将数据分成n个桶，每个桶代表不同范围，把相应的数据插入到每个桶中，然后每个桶分别排序，最好再合并
 */
public class BucketSort implements SortFactory {

    public void sort(int[] arr) {
        bucketSort(arr);
    }

    private static void bucketSort(int[] arr) {
        int len = arr.length;

        // 获取数组中的最大值和最小值，用于作为划分桶的依据
        int min = arr[0], max = arr[0];
        for (int i = 1; i < len; i++) {
            if (max < arr[i]) {
                max = arr[i];
            } else if (min > arr[i]) {
                min = arr[i];
            }
        }

        
    }

    public static void main(String[] args) {
        SortTest.sortTest(new BucketSort());
    }
}
