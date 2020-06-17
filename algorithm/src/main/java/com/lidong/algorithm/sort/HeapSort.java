package com.lidong.algorithm.sort;

import com.lidong.algorithm.common.ArrayFactory;
import com.lidong.algorithm.common.impl.ArrayForSort;
import com.lidong.algorithm.util.ArrayUtil;

/**
 * 堆排序
 *
 * @author ls J
 * @date 2020/6/12 16:05
 */
public class HeapSort implements SortFactory<int[]> {

    @Override
    public void sort(int[] arr) {
        heapSort(arr);
    }

    public static void heapSort(int[] arr) {
        // 1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            // 从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            // 将堆顶元素与末尾元素进行交换
            ArrayUtil.swap(arr, 0, j);
            // 重新对堆进行调整
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     *
     * @param arr    arr
     * @param i      当前元素
     * @param length 数组长度
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        // 先取出当前元素i
        int temp = arr[i];
        // 从i结点的左子结点开始，也就是2i+1处开始
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 如果左子结点小于右子结点，k指向右子结点
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            // 如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        // 将temp值放到最终的位置
        arr[i] = temp;
    }

    public static void main(String[] args) {
        ArrayFactory arrayFactory = new ArrayForSort();
        int[] arr = arrayFactory.createArray();
        ArrayUtil.printArray(arr);
        heapSort(arr);
        System.out.println("排序后的数组为：");
        ArrayUtil.printArray(arr);
    }
}
