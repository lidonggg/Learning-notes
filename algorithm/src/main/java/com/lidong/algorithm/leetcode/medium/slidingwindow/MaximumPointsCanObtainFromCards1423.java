package com.lidong.algorithm.leetcode.medium.slidingwindow;

/**
 * 可获得的最大点数（中等-1423）
 * 中文链接：https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards/
 * <p>
 * 问题描述：
 * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
 * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
 * 你的点数就是你拿到手中的所有卡牌的点数之和。
 * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 * <p>
 * 示例 1：
 * 输入：cardPoints = [1,2,3,4,5,6,1], k = 3
 * 输出：12
 * 解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
 * <p>
 * 示例 2：
 * 输入：cardPoints = [2,2,2], k = 2
 * 输出：4
 * 解释：无论你拿起哪两张卡牌，可获得的点数总是 4 。
 * <p>
 * 示例 3：
 * 输入：cardPoints = [9,7,7,9,7,7,9], k = 7
 * 输出：55
 * 解释：你必须拿起所有卡牌，可以获得的点数为所有卡牌的点数之和。
 * <p>
 * 示例 4：
 * 输入：cardPoints = [1,1000,1], k = 1
 * 输出：1
 * 解释：你无法拿到中间那张卡牌，所以可以获得的最大点数为 1 。
 * <p>
 * 示例 5：
 * 输入：cardPoints = [1,79,80,1,1,1,200,1], k = 3
 * 输出：202 
 * <p>
 * 提示：
 * 1 <= cardPoints.length <= 10^5
 * 1 <= cardPoints[i] <= 10^4
 * 1 <= k <= cardPoints.length
 *
 * @author Ls J
 * @date 2020/6/26 5:36 PM
 */
public class MaximumPointsCanObtainFromCards1423 {

    /**
     * 在首尾之间进行滑动窗口比较即可
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：49 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param cardPoints card points
     * @param k          k
     * @return max score
     */
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;

        int s = 0;
        // 先拿最开始 k 张牌
        for (int i = 0; i < k; ++i) {
            s += cardPoints[i];
        }
        int max = s;
        int t = k;
        for (int i = n - 1; i >= n - k; --i) {
            // 前面去掉一个，后面加上一个
            s = s - cardPoints[--t] + cardPoints[i];
            if (s > max) {
                max = s;
            }
        }
        return max;
    }
}
