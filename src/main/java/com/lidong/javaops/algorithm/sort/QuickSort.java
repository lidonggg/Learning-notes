package com.lidong.javaops.algorithm.sort;

import com.lidong.javaops.algorithm.common.ArrayFactory;
import com.lidong.javaops.algorithm.common.impl.ArrayForSort;
import com.lidong.javaops.algorithm.util.ArrayUtil;

/**
 * @author dlif
 * @version 2019/4/29 16:00
 * 快速排序，时间复杂度O(nlogn)，不稳定，可以代码控制为原地排序
 */
public class QuickSort implements SortFactory {

    public void sort(int[] arr) {

    }

    private static void quickSort(int[] arr, int start, int end) {
        if (start > end) {
            return;
        }

        // 获取分区点
        // 获取之后，start ~ p 的元素都小于等于分区点的值，p+1 ~ end 的元素都大于等于分区点的值
        int p = partition(arr, start, end);
        quickSort(arr, start, p - 1);
        quickSort(arr, p + 1, end);
    }

    /**
     * 获取分区点
     * 选择一个元素作为分区，此元素左边的值都小于等于它，右边的值都大于等于它
     * 分区点的选择要尽量能时两边元素保存相对均衡，避免快速排序退化
     * 考虑到空间消耗，我们使用了一种特殊的分区方法
     * 否则就需要申请两个临时数组来分别存储两部分的元素了
     *
     * @param arr   待排序数组
     * @param start 开始位置
     * @param end   结束位置
     * @return 分区点
     */
    private static int partition(int[] arr, int start, int end) {
        // p用来记录区间中间值所在的位置作为返回值
        int p = start;
        // 选取最后一个元素作为区间值，这是比较简单的做法
        // 最好能够选取的合理一些，比如三选一(第一个、中间和最后一个元素选其中间值)等等
        int pivot = arr[end];

        // 通过游标p把 arr[start...end]分成两部分
        // arr[start..p-1]都是小于pivot的，是已处理区间，arr[p+1...end]是未处理区间
        // 每次从未处理区间中取一个元素和pivot对比，如果小于它，则将其放到已处理区间尾部也就是arr[p]的位置
        for (int j = start; j < end; j++) {
            if (arr[j] < pivot) {
                if (p == j) {
                    p++;
                } else {
                    // 每次交换，都使得左边的元素个数+1，因此p要后移一位
                    ArrayUtil.swap(arr, p, j);
                    p++;
                }
            }
        }

        // 交换完成后，arr[p]存储的就是区间中间值，则p就是该中间值的位置，直接返回
        ArrayUtil.swap(arr, p, end);

        return p;
    }

    public static void main(String[] args) {
        ArrayFactory arrayFactory = new ArrayForSort();
        int[] arr = arrayFactory.createArray();
        ArrayUtil.printArray(arr);
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后的数组为：");
        ArrayUtil.printArray(arr);
    }
}
