package com.lidong.algorithm.leetcode.middling.dynamic;

/**
 * 只有两个键的键盘（中等-650）
 * 中文链接：https://leetcode-cn.com/problems/2-keys-keyboard/
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
 *
 * @author Ls J
 * @date 2020/5/2 12:22 PM
 */
public class KeyboardWith2Keys650 {

    /**
     * 不管 n 是多少，第一步操作肯定只能是 copy all，最后一步是 paste
     * n 肯定是最后一步 paste 操作的 A 的个数的倍数，所以当 n 是素数的时候，那么 n 个 A 肯定只能是由每步复制一个 A 得到的
     * 如果 n 不是素数，那么可以将 n 分解成两个数 i，j 的乘积，这里的 j 代表最后连续 paste 的次数，i 代表每次 paste 的 A 的个数
     * 对于 n 是素数的情况，上述也成立，只是 i 变成了 1
     * 由此可得递推公式：dp[i] = dp[j] + dp[i / j]，这里的 dp[j] 代表要得到 j 个 A 的最少操作步数，dp[i / j] 代表需要多少步，才能通过 j 个 A 得到 i 个 A。
     * <p>
     * 时间复杂度：O(n*sqrt(n))
     * 空间复杂度：O(n)
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
                    // 这样，i / j 最终表示为 i 的最大因子，这个时候也是最优解
                    // 此时 dp[j] 代表最后要连续 paste 的次数，dp[i / j] 代表每次 paste 的 A 的个数
                    dp[i] = dp[j] + dp[i / j];
                    break;
                }
            }
        }

        return dp[n];
    }

    /**
     * leetcode 官方题解
     * 将所有操作分成以 copy 为首的多组，形如 (copy, paste, ..., paste)，再使用 C 代表 copy，P 代表 paste。例如操作 CPPCPPPPCP 可以分为 [CPP][CPPPP][CP] 三组。
     * 假设每组的长度为 g_1, g_2, ...。完成第一组操作后，字符串有 g_1 个 A，完成第二组操作后字符串有 g_1 * g_2 个 A。当完成所有操作时，共有 g_1 * g_2 * ... * g_n 个 'A'。
     * 我们最终想要 N = g_1 * g_2 * ... * g_n 个 A。如果 g_i 是合数，存在 g_i = p * q，那么这组操作可以分解为两组，第一组包含 1 个 C 和 p-1 个 P，第二组包含 1 个 C 和 q-1 个 P。
     * 现在证明这种分割方式使用的操作最少。原本需要 pq 步操作，分解后需要 p+q 步。因为 p+q <= pq，等价于 1 <= (p-1)(q-1)，当 p >= 2 且 q >= 2 时上式永远成立。
     * <p>
     * 时间复杂度：O(sqrt(n))
     * 空间复杂度：O(1)
     *
     * @param n n 个 A
     * @return 最少操作步数
     */
    public static int minSteps1(int n) {
        int ans = 0, d = 2;
        while (n > 1) {
            while (n % d == 0) {
                ans += d;
                n /= d;
            }
            d++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(minSteps(3));
    }
}
