package com.lidong.algorithm.leetcode.easy.array;

/**
 * 移动零（简单-283）
 * 中文链接：https://leetcode-cn.com/problems/move-zeroes/
 * <p>
 * 问题描述：
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * <p>
 * 说明:
 * 1.必须在原数组上操作，不能拷贝额外的数组。
 * 2.尽量减少操作次数。
 *
 * @author Ls J
 * @date 2020/6/27 11:03 AM
 */
public class MoveZeroes283 {

    /**
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40.1 MB，所有 Java 提交中击败了 5.62% 的用户
     *
     * @param nums nums
     */
    public void moveZeroes(int[] nums) {
        // 移动指针
        int pr = -1;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            // 数组追加
            if (nums[i] != 0) {
                nums[++pr] = nums[i];
            }
        }
        // 剩余的补 0
        for (int i = pr + 1; i < n; ++i) {
            nums[i] = 0;
        }
    }
}
