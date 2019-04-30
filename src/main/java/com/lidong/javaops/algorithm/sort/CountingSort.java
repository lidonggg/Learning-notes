package com.lidong.javaops.algorithm.sort;

/**
 * @author Ls J
 * @date 2019/4/30 8:39 AM
 * 计数排序，时间复杂度O(n)，稳定的，非原地排序
 * 差不多是桶排序的一种特殊情况(桶排序是以某个范围划分，计数排序是以值来划分)
 * 要求：数据的分布范围不是很大，而且所有的元素都应该是非负整数或者说能转变成非负整数，
 * 比如数组中最小值是-1，此时我们可以把每一项分别+1让其变成一个非负整数数组
 */
public class CountingSort implements SortFactory {

    public void sort(int[] arr) {
        countingSort(arr);
    }

    private static void countingSort(int[] arr) {
        int len = arr.length;

        // 查找数组中的最大值
        int max = arr[0];
        for (int i = 1; i < len; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        // 计数数组，counts[i] = m，表示元素组中值为 i 的元素共有 m 个
        int[] counts = new int[max + 1];
        // 每一项初始化为0
        for (int i = 0; i <= max; i++) {
            counts[i] = 0;
        }

        // 计算每个元素出现的次数
        for (int anArr : arr) {
            counts[anArr]++;
        }

        // 依次累加
        // 累加完之后，counts[i]的值代表小于等于 i 的元素个数
        for (int i = 1; i <= max; i++) {
            counts[i] += counts[i - 1];
        }

        // 申请一个临时数组
        int[] tmp = new int[len];
        // 从后往前便利，能够保证计数排序是稳定的
        // 因为插入操作是从后往前进行的，这样能保证相同值的元素的相对位置保持不变
        for (int i = len - 1; i >= 0; i--) {
            // 下标从0开始，所以这里要-1
            int index = counts[arr[i]] - 1;
            tmp[index] = arr[i];
            // 每次插入之后，剩余数组中小于等于arr[i]的元素个数-1，也就是说它之前的插槽少了一个，依此类推
            counts[arr[i]]--;
        }

        // 结果拷贝给原数组
        System.arraycopy(tmp, 0, arr, 0, len);
    }

    public static void main(String[] args) {
        SortTest.sortTest(new CountingSort());
    }
}
