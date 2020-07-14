package com.lidong.algorithm.leetcode.medium.hash;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 数组大小减半（中等-1338）
 * 中文链接：https://leetcode-cn.com/problems/reduce-array-size-to-the-half
 * <p>
 * 问题描述：
 * 给你一个整数数组 arr。你可以从中选出一个整数集合，并删除这些整数在数组中的每次出现。
 * 返回 至少 能删除数组中的一半整数的整数集合的最小大小。
 * <p>
 * 示例 1：
 * 输入：arr = [3,3,3,3,5,5,5,2,2,7]
 * 输出：2
 * 解释：选择 {3,7} 使得结果数组为 [5,5,5,2,2]、长度为 5（原数组长度的一半）。
 * 大小为 2 的可行集合有 {3,5},{3,2},{5,2}。
 * 选择 {2,7} 是不可行的，它的结果数组为 [3,3,3,3,5,5,5]，新数组长度大于原数组的二分之一。
 * <p>
 * 示例 2：
 * 输入：arr = [7,7,7,7,7,7]
 * 输出：1
 * 解释：我们只能选择集合 {7}，结果数组为空。
 * <p>
 * 示例 3：
 * 输入：arr = [1,9]
 * 输出：1
 * <p>
 * 示例 4：
 * 输入：arr = [1000,1000,3,7]
 * 输出：1
 * <p>
 * 示例 5：
 * 输入：arr = [1,2,3,4,5,6,7,8,9,10]
 * 输出：5
 * <p>
 * 提示：
 * 1 <= arr.length <= 10^5
 * arr.length 为偶数
 * 1 <= arr[i] <= 10^5
 *
 * @author ls J
 * @date 2020/7/14 20:13
 */
public class ReduceArraySizeToTheHalf1338 {

    /**
     * hash + 排序 + 贪心
     * <p>
     * 执行用时：38 ms，在所有 Java 提交中击败了 78.23% 的用户
     * 内存消耗：48.8 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param arr arr
     * @return num
     */
    public int minSetSize(int[] arr) {
        int len = arr.length, res = 0, limit = len >> 1;
        HashMap<Integer, Integer> map = new HashMap<>(len);
        for (int num : arr) {
            map.merge(num, 1, (old, add) -> old + add);
        }
        ArrayList<Integer> list = new ArrayList<>(map.values());
        list.sort(Comparator.comparingInt(item -> -item));
        for (int num : list) {
            ++res;
            if ((len -= num) <= limit) {
                return res;
            }
        }
        return -1;
    }
}
