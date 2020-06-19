package com.lidong.algorithm.leetcode.medium.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 三数之和（中等-15）
 * 中文链接：https://leetcode-cn.com/problems/3sum/
 * <p>
 * 问题描述：
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
 * 请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 *
 * @author ls J
 * @date 2020/6/19 13:26
 */
public class SumOf3Nums15 {

    /**
     * 双指针
     * <p>
     * 执行用时：25 ms，在所有 Java 提交中击败了 61.69% 的用户
     * 内存消耗：44.4 MB，在所有 Java 提交中击败了 95.60% 的用户
     *
     * @param nums nums
     * @return list
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        // 排序
        Arrays.sort(nums);

        List<List<Integer>> res = new LinkedList<>();

        for (int x = 0; x < n; ++x) {
            // 避免重复结果
            if (x > 0 && nums[x] == nums[x - 1]) {
                continue;
            }
            // z 从后往前遍历
            int z = n - 1;
            // y + z 的目标和
            int target = -nums[x];
            // y 在 x ~ z 之间，且也要排除重复元素
            for (int y = x + 1; y < z; ++y) {
                if (y > x + 1 && nums[y] == nums[y - 1]) {
                    continue;
                }
                // z > y ，并且它们的和大于目标值的时候，才能继续迭代
                while (z > y && nums[z] + nums[y] > target) {
                    z--;
                }
                if (y == z) {
                    break;
                }
                if (nums[z] + nums[y] >= target) {
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[x]);
                    item.add(nums[y]);
                    item.add(nums[z]);
                    res.add(item);
                }
            }
        }

        return res;
    }
}
