package com.lidong.algorithm.leetcode.medium.dynamic;

import com.lidong.algorithm.util.ArrayUtil;

/**
 * 比特位计数（中等-338）
 * 中文链接：https://leetcode-cn.com/problems/counting-bits/
 * <p>
 * 问题描述：
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * <p>
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 * <p>
 * 示例 2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * <p>
 * 进阶:
 * 给出时间复杂度为 O(n*sizeof(integer)) 的解答非常容易。但你可以在线性时间 O(n) 内用一趟扫描做到吗？
 * 要求算法的空间复杂度为 O(n)。
 * 你能进一步完善解法吗？要求在 C++ 或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 * @author ls J
 * @date 2020/5/25 9:53
 */
public class CountingBits338 {

    /**
     * 使用 Integer.toBinaryString(i) 很容易求解，这里直接使用动态规划
     * <p>
     * 动态规划：
     * 1.如果当前数字 i 能被 2 整除，则说明当前数字 i 只是数字 i/2 左移一位来的，1 的个数不变；
     * 2.如果当前数字 i 不能被整除，那么说明当前数字 i 是 i/2 左移一位，然后最后一位置为 1 得到的
     * <p>
     * <p>
     * 执行用时：2 ms，在所有 Java 提交中击败了 80.27% 的用户
     * 内存消耗：44 MB，在所有 Java 提交中击败了 5.88% 的用户
     *
     * @param num n
     * @return dp[]
     */
    public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 0;
        for (int i = 1; i <= num; ++i) {
            // 如果能被 2 整除，则说明当前数字 i 只是数字 i/2 左移一位来的，1 的个数不变
            if (i % 2 == 0) {
                dp[i] = dp[i >> 1];
            } else {
                // 如果不能被整除，那么说明当前数字 i 是 i/2 左移一位，然后最后一位置为 1 得到的
                dp[i] = dp[i >> 1] + 1;
            }
        }
        return dp;
    }

    /**
     * 方法二：利用位运算简化上述操作
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 99.91% 的用户
     * 内存消耗：43.6 MB，在所有 Java 提交中击败了 5.88% 的用户
     *
     * @param num n
     * @return dp
     */
    public int[] countBits2(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 0;
        for (int i = 1; i <= num; ++i) {
            // x / 2 => x >> 1， x % 2 => x & 1
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }

    public static void main(String[] args) {
        CountingBits338 cb = new CountingBits338();
        ArrayUtil.printArray(cb.countBits(100));
    }
}
