package com.lidong.algorithm.sort;

/**
 * 归并排序，时间复杂度O(nlogn)，空间复杂度O(nlogn)，稳定，非原地排序
 * 递推公式：merge_sort(p...r) = merge(merge_sort(p...q),merge_sort(q...r))
 *
 * @author Ls J
 * @version 2019/4/29 15:01
 */
public class MergeSort implements SortFactory<int[]> {

    @Override
    public void sort(int[] arr) {
        int[] tmp = new int[arr.length];
        mergeSort(arr, 0, arr.length, tmp);
    }

    /**
     * 归并排序
     *
     * @param arr   待排序数组
     * @param start 子数组开始位置
     * @param end   子数组结束位置
     * @param tmp   临时空间，防止频繁开辟新的临时空间
     */
    private static void mergeSort(int[] arr, int start, int end, int[] tmp) {
        if (start < end) {
            int mid = start + ((end - start) >> 1);
            mergeSort(arr, start, mid, tmp);
            mergeSort(arr, mid + 1, end, tmp);
            merge(arr, start, mid, end, tmp);
        }
    }

    /**
     * 合并
     *
     * @param arr   待排序数组
     * @param start 左起始位置
     * @param mid   分界位置
     * @param end   右结束位置
     * @param tmp   临时数组
     */
    private static void merge(int[] arr, int start, int mid, int end, int[] tmp) {
        int i = start, j = mid + 1;
        // 临时数组的起始位置
        int t = 0;

        // 比较左右两部分数组，依次将较小的元素放进tmp数组中
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                tmp[t++] = arr[i++];
            } else {
                tmp[t++] = arr[j++];
            }
        }
        // 将左边剩余元素填充进tmp中
        while (i <= mid) {
            tmp[t++] = arr[i++];
        }
        // 将右边剩余元素填充进tmp中
        while (j <= end) {
            tmp[t++] = arr[j++];
        }

        t = 0;
        // 将tmp中的元素全部拷贝到原数组中
        while (start <= end) {
            arr[start++] = tmp[t++];
        }
    }

    public static void main(String[] args) {
        SortTest.sortTest(new MergeSort());
    }
}
