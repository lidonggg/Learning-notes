package com.lidong.algorithm.leetcode.middling.dynamic;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 最长数对链（中等-646）
 * 中文链接：https://leetcode-cn.com/problems/maximum-length-of-pair-chain/
 * <p>
 * 问题描述：
 * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
 * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
 * 给定一个对数集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 * <p>
 * 示例 :
 * 输入: [[1,2], [2,3], [3,4]]
 * 输出: 2
 * 解释: 最长的数对链是 [1,2] -> [3,4]
 * <p>
 * 注意：
 * 给出数对的个数在 [1, 1000] 范围内。
 *
 * @author ls J
 * @date 2020/5/29 14:52
 */
public class MaximumLengthOfPairChain646 {

    /**
     * 动态规划
     * <p>
     * 状态转移方程：
     * dp[i] = Math.max(dp[j] + 1, dp[i-1])
     * 首先对 pairs 按照 pair[0] 进行排序
     * 其中 dp[i] 代表前 i 个 pair 能构造出的最长数对链，j 是 pairs[i] 前面离它最近并且 pairs[j][1] < pairs[i][0] 的索引
     * <p>
     * 执行用时：18 ms，在所有 Java 提交中击败了 67.65% 的用户
     * 内存消耗：39.8 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param pairs pairs
     * @return max
     */
    public static int findLongestChain(int[][] pairs) {
        int len;
        if ((len = pairs.length) <= 1) {
            return len;
        }
        // 根据数对的第一个元素排序
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));

        int[] dp = new int[len];
        dp[0] = 1;

        for (int i = 1; i < len; ++i) {
            // 从后往前找到第一个数对的第二个数比当前数对的第一个数小的元素
            for (int j = i - 1; j >= 0; --j) {
                dp[i] = dp[i - 1];
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    break;
                }
            }
        }

        return dp[len - 1];
    }


    /**
     * 方法二：贪心算法
     * 来自 leetcode 官方题解：https://leetcode-cn.com/problems/maximum-length-of-pair-chain/solution/zui-chang-shu-dui-lian-by-leetcode/
     * <p>
     * 使用贪心思想扩展数对链，在所有可作为下一个数对的集合中选择第二个数最小的数对添加到数对链。
     * 按照数对第二个数的升序序列遍历所有数对，如果当前数对可以加入链，则加入。
     *
     * @param pairs pairs
     * @return max
     */
    public int findLongestChain2(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
        int cur = Integer.MIN_VALUE, res = 0;
        for (int[] pair : pairs) {
            if (cur < pair[0]) {
                cur = pair[1];
                res++;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[][] pairs = {
                {1, 2},
                {2, 3},
                {3, 4},
                {5, 6},
                {100, 101}
        };
        System.out.println(findLongestChain(pairs));
        for (int[] pair : pairs) {
            System.out.println(pair[0] + " " + pair[1]);
        }
    }
}
