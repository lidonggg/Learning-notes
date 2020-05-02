package com.lidong.algorithm.leetcode.middling.dynamic;

/**
 * @author Ls J
 * @date 2020/5/2 12:22 PM
 * 只有两个键的键盘（中等-650）
 * 问题描述：
 * 最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：
 * <p>
 * Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
 * Paste (粘贴) : 你可以粘贴你上一次复制的字符。
 * 给定一个数字 n 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。输出能够打印出 n 个 'A' 的最少操作次数。
 * <p>
 * 示例 1:
 * 输入: 3
 * 输出: 3
 * 解释:
 * 最初, 我们只有一个字符 'A'。
 * 第 1 步, 我们使用 Copy All 操作。
 * 第 2 步, 我们使用 Paste 操作来获得 'AA'。
 * 第 3 步, 我们使用 Paste 操作来获得 'AAA'。
 * <p>
 * 说明:
 * n 的取值范围是 [1, 1000] 。
 */
public class KeyboardWith2Keys650 {

    /**
     * 不管 n 是多少，第一步操作肯定只能是 copy all，最后一步是 paste
     * n 肯定是最后一步 paste 操作的 A 的个数的倍数，所以当 n 是素数的时候，那么 n 个 A 肯定只能是由每步复制一个 A 得到的
     * 如果 n 不是素数，那么可以将 n 分解成 两个数 i，j 的乘积，这里的 j 代表最后连续 paste 的次数，i 代表每次 paste 的 A 的个数
     * 由此可得递推公式：dp[i] = dp[j] + dp[i / j]，这里的 dp[j] 代表要得到 j 个 A 的最少操作步数，dp[i / j] 代表需要多少步，才能通过 j 个 A 得到 i 个 A。
     *
     * @param n n 个 A
     * @return 最少操作步数
     */
    public static int minSteps(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; ++i) {
            dp[i] = i;
            int jMax = (int) Math.sqrt(i);
            for (int j = 2; j <= jMax; ++j) {
                if (i % j == 0) {
                    dp[i] = dp[j] + dp[i / j];
                    break;
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(minSteps(3));
    }
}
