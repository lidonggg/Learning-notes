package com.lidong.algorithm.leetcode.easy.greedy;

/**
 * 种花问题（简单-605）
 * 中文链接：https://leetcode-cn.com/problems/can-place-flowers
 * <p>
 * 问题描述：
 * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * 给定一个花坛（表示为一个数组包含 0 和 1，其中 0 表示没种植花，1 表示种植了花），和一个数 k 。能否在不打破种植规则的情况下种入 k 朵花？能则返回True，不能则返回False。
 * <p>
 * 示例 1:
 * 输入: flowerbed = [1,0,0,0,1], n = 1
 * 输出: True
 * <p>
 * 示例 2:
 * 输入: flowerbed = [1,0,0,0,1], n = 2
 * 输出: False
 * <p>
 * 注意:
 * - 数组内已种好的花不会违反种植规则。
 * - 输入的数组长度范围为 [1, 20000]。
 * - n 是非负整数，且不会超过输入数组的大小。
 *
 * @author Ls J
 * @date 2020/8/2 12:53 AM
 */
public class CanPlaceFlowers605 {

    /**
     * 贪心算法
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：41.1 MB，在所有 Java 提交中击败了 77.23% 的用户
     *
     * @param flowerbed flowerbed
     * @param k         k
     * @return true / false
     */
    public boolean canPlaceFlowers(int[] flowerbed, int k) {

        int cnt = 0;
        int n = flowerbed.length;
        if (n == 1) {
            return flowerbed[0] == 0 || k == 0;
        }
        // 边界特殊处理
        if (flowerbed[0] == 0 && flowerbed[1] == 0) {
            ++cnt;
            flowerbed[0] = 1;
        }
        for (int i = 1; i < n - 1; ++i) {
            if (flowerbed[i] == 0 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                flowerbed[i] = 1;
                ++cnt;
            }
            if (cnt >= k) {
                return true;
            }
        }
        // 边界特殊处理
        if (flowerbed[n - 1] == 0 && flowerbed[n - 2] == 0) {
            ++cnt;
        }

        return cnt >= k;
    }
}
