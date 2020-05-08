package com.lidong.algorithm.leetcode.interview.easy;

/**
 * 二维数组的查找（简单-04）
 * 问题描述：
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * @author ls J
 * @date 2020/4/27 10:37
 */
public class TwoDimArraySearch4 {

    public int[] twoDimArraySearch(int[][] arr, int target) {
        if (arr.length == 0 || arr[0].length == 0) {
            return new int[]{-1, -1};
        }

        int trow = arr.length, tcol = arr[0].length;
        // 从第一行开始
        int crow = 0, ccol = tcol - 1;

        while (crow < trow && ccol >= 0) {
            // 如果当前元素大于目标值，说明它下面的所有元素都大于目标值，那么查找前一列
            if (arr[crow][ccol] > target) {
                ccol--;
            } else if (arr[crow][ccol] < target) {
                // 如果当前元素小于目标值，说明它左边所有元素都小于目标值，那么查找下一行
                crow++;
            } else {
                return new int[]{crow, ccol};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 法二，针对每一行/列使用二分查找，如果行数大于列数，则进行行二分查找；如果列数大于行数，则进行列二分查找
     * 以下假设列数>行数
     */
    public int[] twoDimArraySearch1(int[][] arr, int target) {
        if (arr.length == 0 || arr[0].length == 0) {
            return new int[]{-1, -1};
        }
        int rows = arr.length;
        for (int i = 0; i < rows; ++i) {
            int theCol = binarySearch(arr[i], target);
            if (theCol > -1) {
                return new int[]{i, theCol};
            }
        }
        return new int[]{-1, -1};
    }

    private int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int mid;

        while (high >= low) {
            mid = low + ((high - low) >> 1);
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }
}
