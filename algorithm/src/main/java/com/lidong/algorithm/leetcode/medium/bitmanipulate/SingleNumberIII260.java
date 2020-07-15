package com.lidong.algorithm.leetcode.medium.bitmanipulate;

/**
 * 只出现一次的数字 III（中等-260）
 * 中文链接：https://leetcode-cn.com/problems/single-number-iii
 * <p>
 * 问题描述：
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 * <p>
 * 示例 :
 * 输入: [1,2,1,3,2,5]
 * 输出: [3,5]
 * <p>
 * 注意：
 * 1.结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
 * 2.你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 *
 * @author ls J
 * @date 2020/7/15 9:55
 */
public class SingleNumberIII260 {

    /**
     * 性质：a ^ a = 0，a ^= b != 0 (a != b)
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40.1 MB，在所有 Java 提交中击败了 16.67% 的用户
     *
     * @param nums nums
     * @return a, b
     */
    public int[] singleNumber(int[] nums) {

        int xorSum = 0;
        for (int num : nums) {
            xorSum ^= num;
        }
        // 找第一个 1 出现的位置，要找的两个数在这个位置肯定一个是 1，另外一个是 0
        int fir1Idx = findFir1Idx(xorSum);
        int a = 0, b = 0;
        for (int num : nums) {
            // 根据 fir1Idx 对数组进行分类，分别进行异或
            if (isBit1(num, fir1Idx)) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }

    /**
     * 寻找第一个 1 所在的位置
     *
     * @param num num
     * @return idx
     */
    private int findFir1Idx(int num) {
        int idx = 0;
        while ((num & 1) != 1 && idx < 32) {
            num >>>= 1;
            ++idx;
        }
        return idx;
    }

    /**
     * 判断某个数的比特位在某个位置是否为 1
     *
     * @param num num
     * @param idx idx
     * @return true / false
     */
    private boolean isBit1(int num, int idx) {
        return ((num >> idx) & 1) == 1;
    }
}
