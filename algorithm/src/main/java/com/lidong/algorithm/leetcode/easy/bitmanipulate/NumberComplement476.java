package com.lidong.algorithm.leetcode.easy.bitmanipulate;

/**
 * 数字的补数（简单-476）
 * 中文链接：https://leetcode-cn.com/problems/number-complement
 * <p>
 * 问题描述：
 * 给定一个正整数，输出它的补数。补数是对该数的二进制表示取反。
 * <p>
 * 示例 1:
 * 输入: 5
 * 输出: 2
 * 解释: 5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
 * <p>
 * 示例 2:
 * 输入: 1
 * 输出: 0
 * 解释: 1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
 * <p>
 * 注意:
 * 1.给定的整数保证在 32 位带符号整数的范围内。
 * 2.你可以假定二进制数不包含前导零位。
 *
 * @author Ls J
 * @date 2020/8/3 10:25 PM
 */
public class NumberComplement476 {

    /**
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：36.3 MB，在所有 Java 提交中击败了 75.00% 的用户
     *
     * @param num num
     * @return res
     */
    public int findComplement(int num) {
        int res = 0;
        int nn = 0;
        // 从低位开始依次比较
        while (num != 0) {
            if ((num & 1) == 0) {
                res += (1 << nn);
            }
            // 比较下一位
            num >>>= 1;
            ++nn;
        }
        return res;
    }
}
