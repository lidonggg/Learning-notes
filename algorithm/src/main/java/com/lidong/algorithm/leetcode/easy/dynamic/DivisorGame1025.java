package com.lidong.algorithm.leetcode.easy.dynamic;

/**
 * 除数博弈（简单-1025）
 * 中文链接：https://leetcode-cn.com/problems/divisor-game
 * <p>
 * 问题描述：
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
 * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
 * 用 N - x 替换黑板上的数字 N 。
 * 如果玩家无法执行这些操作，就会输掉游戏。
 * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
 * <p>
 * 示例 1：
 * 输入：2
 * 输出：true
 * 解释：爱丽丝选择 1，鲍勃无法进行操作。
 * <p>
 * 示例 2：
 * 输入：3
 * 输出：false
 * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
 * <p>
 * 提示：
 * 1 <= N <= 1000
 *
 * @author ls J
 * @date 2020/7/24 17:06
 */
public class DivisorGame1025 {

    /**
     * 方法一：动态规划
     * <p>
     * 执行用时：5 ms，在所有 Java 提交中击败了 32.36% 的用户
     * 内存消耗：36.2 MB，在所有 Java 提交中击败了 10.00% 的用户
     * <p>
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     *
     * @param n n
     * @return true / false
     */
    public boolean divisorGame(int n) {
        if (n <= 2) {
            return n == 2;
        }
        // dp[i] 代表在数字 i 的时候先手能否获胜
        boolean[] dp = new boolean[n + 1];

        dp[1] = false;
        dp[2] = true;
        for (int i = 3; i <= n; ++i) {
            dp[i] = false;
            for (int j = 1; j < i; ++j) {
                // 如果在 i 之前找到了一个数字 i - j 使得先手必输，那么此时爱丽丝先手的话必赢
                if (i % j == 0 && !dp[i - j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    /**
     * 方法二：
     * 可以利用数学归纳法证明
     * leetcode 官方题解：https://leetcode-cn.com/problems/divisor-game/solution/chu-shu-bo-yi-by-leetcode-solution/
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：36.2 MB，在所有 Java 提交中击败了 10.00% 的用户
     *
     * @param n n
     * @return true / false
     */
    public boolean divisorGame2(int n) {
        return n % 2 == 0;
    }
}
