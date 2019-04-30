package com.lidong.javaops.algorithm.search;

import com.lidong.javaops.algorithm.common.ArrayFactory;
import com.lidong.javaops.algorithm.util.ArrayUtil;
import com.lidong.javaops.algorithm.common.impl.ArrayForSearch;

/**
 * @author Ls J
 * @version 2019/4/29 13:57
 * 二分查找的各种变形，时间复杂度O(logn)
 */
public class BinarySearch {

    /**
     * 最普通的二分查找
     *
     * @param arr
     * @param key
     * @return 返回要查找的值所在的位置，如果不存在，则返回-1
     */
    private static int bsCommon(int[] arr, int key) {
        int low = 0, high = arr.length - 1;
        int mid;

        while (high >= low) {
            mid = low + ((high - low) >> 1);
            if (arr[mid] == key) {
                return mid;
            }
            if (arr[mid] > key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    /**
     * 查找第一个与key相等的元素的位置
     *
     * @param arr
     * @param key
     * @return 如果有，则返回第一个相等的，如果没有则返回-1
     */
    private static int bsFirstEqual(int[] arr, int key) {
        int low = 0, high = arr.length - 1;
        int mid;

        while (low <= high) {
            mid = low + ((high - low) >> 1);
            // 如果中间值大于key，则要找的元素如果存在则肯定在中间值之前
            if (arr[mid] > key) {
                high = mid - 1;
            }
            // 如果中间值小于key，则要找的元素如果存在肯定在中间值之后
            else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                // 此时中间值=key
                // 如果是第一个元素则直接返回
                // 如果前一个元素与key不相等，则直接返回mid
                if (mid == 0 || arr[mid - 1] != key) {
                    return mid;
                }
                // 否则中间值等于key，但不是第一个，应该往前寻找，high=mid-1
                high = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 查找最后一个与key相等的元素的位置
     *
     * @param arr
     * @param key
     * @return 如果有，则返回最后相等的，如果没有则返回-1
     */
    private static int bsLastEqual(int[] arr, int key) {
        int low = 0, high = arr.length - 1;
        int mid;

        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (arr[mid] > key) {
                high = mid - 1;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                if (mid == arr.length - 1 || arr[mid + 1] != key) {
                    return mid;
                }
                low = mid + 1;
            }
        }

        return -1;
    }

    /**
     * 查找第一个大于等于某个key的位置
     *
     * @param arr
     * @param key
     * @return 存在则返回该元素的位置，否则返回-1
     */
    private static int bsFirstGe(int[] arr, int key) {
        int low = 0, high = arr.length - 1;
        int mid;

        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (arr[mid] < key) {
                low = mid + 1;
            } else {
                if(mid == 0 || arr[mid - 1] < key){
                    return mid;
                }
                high = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 查找最后一个小于等于key的元素的位置
     * @param arr
     * @param key
     * @return 存在则返回该元素的位置，否则返回-1
     */
    private static int bsLastLe(int[] arr,int key){
        int low = 0, high = arr.length - 1;
        int mid;

        while (low <= high) {
            mid = low + ((high - low) >> 1);
            // 如果中间值比给定值大的话，说明要找的元素在中间值之前，让high=mid-1
            if (arr[mid] > key) {
                high = mid - 1;
            } else {
                // arr[mid] <= key
                // 如果中间值已经是最后一个元素了，则此时就是要找的元素
                // 如果中间值不是最后一个元素但是中间值后一个元素比key大，说明此中间值就是要找的元素
                if(mid == arr.length - 1 || arr[mid + 1] > key){
                    return mid;
                }
                // 否则继续往后寻找
                low = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        ArrayFactory arrayFactory = new ArrayForSearch();
        int[] arr = arrayFactory.createArray();
        ArrayUtil.printArray(arr);

        System.out.println("普通二分查找 ===> " + bsCommon(arr, 12));

        System.out.println("查找第一个值等于某值得元素的位置 ===> " + bsFirstEqual(arr, 10));

        System.out.println("查找最后一个值等于某值得元素的位置 ===> " + bsLastEqual(arr, 5));

        System.out.println("查找第一个大于等于某个值得元素的位置 ===> " + bsFirstGe(arr, 7));

        System.out.println("查找最后一个小于等于某个值得元素的位置 ===> " + bsLastLe(arr, 7));
    }
}
