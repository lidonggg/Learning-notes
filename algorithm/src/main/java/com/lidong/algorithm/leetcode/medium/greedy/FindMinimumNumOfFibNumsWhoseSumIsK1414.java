package com.lidong.algorithm.leetcode.medium.greedy;

/**
 * 和为 K 的最少斐波那契数字数目（中等-1414）
 * 中文链接：https://leetcode-cn.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k
 * <p>
 * 问题描述：
 * 给你数字 k ，请你返回和为 k 的斐波那契数字的最少数目，其中，每个斐波那契数字都可以被使用多次。
 * 斐波那契数字定义为：
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 ， 其中 n > 2 。
 * 数据保证对于给定的 k ，一定能找到可行解。
 * <p>
 * 示例 1：
 * 输入：k = 7
 * 输出：2
 * 解释：斐波那契数字为：1，1，2，3，5，8，13，……
 * 对于 k = 7 ，我们可以得到 2 + 5 = 7 。
 * <p>
 * 示例 2：
 * 输入：k = 10
 * 输出：2
 * 解释：对于 k = 10 ，我们可以得到 2 + 8 = 10 。
 * <p>
 * 示例 3：
 * 输入：k = 19
 * 输出：3
 * 解释：对于 k = 19 ，我们可以得到 1 + 5 + 13 = 19 。
 * <p>
 * 提示：
 * 1 <= k <= 10^9
 *
 * @author Ls J
 * @date 2020/7/18 5:51 PM
 */
public class FindMinimumNumOfFibNumsWhoseSumIsK1414 {

    /**
     * 贪心算法
     * 每次都用 k 减去不大于它的最大的斐波那契数
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 99.69% 的用户
     * 内存消耗：37.2 MB，在所有 Java 提交中击败了 100.00% 的用户
     * <p>
     * 时间复杂度：O(44)，可能的斐波那契数字不大于 44 个
     * 空间复杂度：O(44)
     *
     * @param k k
     * @return num
     */
    public int findMinFibonacciNumbers(int k) {
        int[] nums = new int[45];
        int pre = 1, cur = 1, tmp = 0, i = 0;
        while (cur <= k) {
            nums[i++] = cur;
            tmp = pre;
            pre = cur;
            cur = tmp + pre;
        }
        int res = 0;
        while (k > 0) {
            for (int j = i - 1; j >= 0; --j) {
                if (nums[j] <= k) {
                    k -= nums[j];
                    ++res;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 方法二：原理同上，但是基于了一些数学证明
     * 来自 leetcode 官方题解：https://leetcode-cn.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/solution/he-wei-k-de-zui-shao-fei-bo-na-qi-shu-zi-shu-mu-by/
     * 1.不会选取连续的两个斐波那契数字，因为这两个数字总是能够被它们的下一个数字代替；
     * 2.不会选取同一个数字两次
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 99.69% 的用户
     * 内存消耗：37.2 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param k k
     * @return num
     */
    public int findMinFibonacciNumbers2(int k) {
        int[] nums = new int[45];
        int pre = 1, cur = 1, tmp = 0, i = 0;
        while (cur <= k) {
            nums[i++] = cur;
            tmp = pre;
            pre = cur;
            cur = tmp + pre;
        }
        int res = 0;
        // 这里不同
        for (int j = i - 1; j >= 0; --j) {
            if (nums[j] <= k) {
                k -= nums[j];
                ++res;
            }
        }

        return res;
    }
}
