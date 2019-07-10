package com.lidong.algorithm.leetcode.easy;

/**
 * @author ls J
 * @date 2019/7/10 3:01 PM
 * 翻转图像（简单-832）
 * 问题描述：
 * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 * <p>
 * 示例：
 * 输入：[[1,1,0],[1,0,1],[0,0,0]]
 * 输出：[[1,0,0],[0,1,0],[1,1,1]]
 * 说明：水平翻转：[[0,1,1],[1,0,1],[0,0,0]]；
 * 反转：[[1,0,0],[0,1,0],[1,1,1]]
 */
public class FlipAndInvertImage832 {

    public static int[][] flipAndInvertImage(int[][] A) {
        for (int[] line : A) {
            flip(line);
            invert(line);
        }

        return A;
    }

    /**
     * 翻转，原地交换，没有增加额外的数组
     *
     * @param arr array
     */
    private static void flip(int[] arr) {
        int len = arr.length;

        for (int i = 0; i < len / 2; ++i) {
            int tmp = arr[i];
            arr[i] = arr[len - i - 1];
            arr[len - i - 1] = tmp;
        }
    }

    /**
     * 反转 0->1，1->0，使用异或
     *
     * @param arr array
     */
    private static void invert(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = arr[i] ^ 1;
        }
    }

    public static void main(String[] args) {
        flipAndInvertImage(new int[][]{{1, 1, 0}, {1, 0, 1}, {0, 0, 0}});
    }
}
