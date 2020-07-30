package com.lidong.algorithm.leetcode.easy.array;

/**
 * 和为零的 N 个唯一整数（中等-1304）
 * 中文链接：https://leetcode-cn.com/problems/find-n-unique-integers-sum-up-to-zero
 * <p>
 * 问题描述：
 * 给你一个整数 n，请你返回 任意 一个由 n 个 各不相同 的整数组成的数组，并且这 n 个数相加和为 0 。
 * <p>
 * 示例 1：
 * 输入：n = 5
 * 输出：[-7,-1,1,3,4]
 * 解释：这些数组也是正确的 [-5,-1,1,2,3]，[-3,-1,2,-2,4]。
 * <p>
 * 示例 2：
 * 输入：n = 3
 * 输出：[-1,0,1]
 * <p>
 * 示例 3：
 * 输入：n = 1
 * 输出：[0]
 *  
 * 提示：
 * 1 <= n <= 1000
 *
 * @author ls J
 * @date 2020/7/30 15:44
 */
public class FindNUniqueIntegersSumUpToZero1304 {

    /**
     * 增量生成数组元素
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：37.9 MB，在所有 Java 提交中击败了 12.50% 的用户
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param n n
     * @return res array
     */
    public int[] sumZero(int n) {
        int[] res = new int[n];
        int idx = 0;
        // 如果 n 是奇数，那么补个 0 就好了
        if (n % 2 != 0) {
            res[idx++] = 0;
            n--;
        }
        // 按照递增的方式来生成数组
        for (int i = 0; i < n / 2; ++i) {
            res[idx++] = i + 1;
            res[idx++] = -i - 1;
        }

        return res;
    }
}
