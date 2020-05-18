package com.lidong.algorithm.leetcode.easy.array;

/**
 * 将数组分成和相等的三个部分（中等-1013）
 * 中文链接：https://leetcode-cn.com/problems/partition-array-into-three-parts-with-equal-sum/
 * <p>
 * 问题描述：
 * 给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。
 * 形式上，如果可以找出索引 i+1 < j 且满足 (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1]) 就可以将数组三等分。
 * <p>
 * 示例 1：
 * 输入：[0,2,1,-6,6,-7,9,1,2,0,1]
 * 输出：true
 * 解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 * <p>
 * 示例 2：
 * 输入：[0,2,1,-6,6,7,9,-1,2,0,1]
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：[3,3,6,5,-2,2,5,1,-9,4]
 * 输出：true
 * 解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
 *  
 * 提示：
 * 3 <= A.length <= 50000
 * -10^4 <= A[i] <= 10^4
 *
 * @author ls J
 * @date 2020/5/18 14:57
 */
public class PartitionArrayIntoThreePartsWithEqualSum1013 {

    /**
     * 分两次遍历，第一次从头到尾计算数组总和，如果能被 3 整除，那么就有可能别分成三等份
     * 第二次遍历，遍历到 nums[i] 的时候，将当前元素设置为它与之前所有元素的和，
     * 然后判断当前元素的值是否为 slice 的 （count+1） 倍（count 代表已经找到的分组的个数）
     * 比如说，count 最开始设置为 0，那么要找的第一个前 k 个元素和应该是 slice * 1(count+1)，然后 count++，第二个则为 slice * 2(count+1)
     * 按照这个思路，这里的 3 可以是任意数字
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：47.3 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param nums num arr
     * @return ture if success
     */
    public static boolean canThreePartsEqualSum(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return false;
        }
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        if (total % 3 != 0) {
            return false;
        }
        int slice = total / 3;
        // 记录到已找到的分组的个数
        int count = slice == nums[0] ? 1 : 0;
        for (int i = 1; i < len; ++i) {
            // 将当前元素设置为它和它之前所有元素的和
            nums[i] += nums[i - 1];
            // 如果当前的和等于 slice 的 count+1 倍，则找到了一组切片
            if (nums[i] == slice * (count + 1)) {
                count++;
                // 如果已经找到了两个并且当前不是最后一个，那么代表可以分成三组，直接返回 true
                if (count == 2 && i < len - 1) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr = {0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1};
        System.out.println(canThreePartsEqualSum(arr));
    }
}
