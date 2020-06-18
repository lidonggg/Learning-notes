package com.lidong.algorithm.leetcode.medium.binarysearch;

/**
 * 寻找重复数（中等-287）
 * 中文链接：https://leetcode-cn.com/problems/find-the-duplicate-number/
 * <p>
 * 问题描述：
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * <p>
 * 示例 1:
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * <p>
 * 说明：
 * 1.不能更改原数组（假设数组是只读的）。
 * 2.只能使用额外的 O(1) 的空间。
 * 3.时间复杂度小于 O(n2) 。
 * 4.数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 * @author ls J
 * @date 2020/6/18 20:22
 */
public class FindDuplicateNumber287 {

    /**
     * 二分法：鸽巢原理
     * <p>
     * 执行用时：3 ms，有 Java 提交中击败了 60.71% 的用户
     * 内存消耗：39.7 MB，在所有 Java 提交中击败了 6.67% 的用户
     *
     * @param nums nums
     * @return duplicate number
     */
    public static int findDuplicate(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return -1;
        }
        int l = 0, r = len - 1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            // 鸽巢原理
            int count = 0;
            for (int n : nums) {
                if (n <= mid) {
                    count++;
                }
            }
            if (count > mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return r;
    }

    /**
     * 方法二：快慢指针
     * <p>
     * 来自官方题解：https://leetcode-cn.com/problems/find-the-duplicate-number/solution/xun-zhao-zhong-fu-shu-by-leetcode-solution/
     *
     * @param nums nums
     * @return duplicate number
     */
    public int findDuplicate2(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 4, 5, 2, 1};
        System.out.print(findDuplicate(nums));
    }
}
