package com.lidong.algorithm.leetcode.hard.dynamic;

import java.util.Arrays;

/**
 * 地下城游戏（困难-174）
 * 中文链接：https://leetcode-cn.com/problems/dungeon-game
 * <p>
 * 问题描述：
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 * <p>
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 * <p>
 * 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
 * -2 (K)	-3	3
 * -5	-10	1
 * 10	30	-5 (P)
 *  
 * 说明:
 * 1.骑士的健康点数没有上限。
 * 2.任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 *
 * @author Ls J
 * @date 2020/7/12 6:03 PM
 */
public class DungeonGame174 {

    /**
     * 方法一：二分+动态规划
     * <p>
     * 执行用时：2 ms，在所有 Java 提交中击败了 96.34% 的用户
     * 内存消耗：39.6 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param dungeon dungeon
     * @return min hp
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        if (m == 0) {
            return 0;
        }
        int n = dungeon[0].length;
        if (n == 0) {
            return 0;
        }
        int hp = 1;
        for (int[] ints : dungeon) {
            hp += Math.abs(ints[0]);
        }
        for (int i = 1; i < n; ++i) {
            hp += Math.abs(dungeon[m - 1][i]);
        }

        int l = 1, r = hp;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (check(mid, dungeon, m, n)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    private boolean check(int hp, int[][] dungeon, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                dp[i][j] = Integer.MIN_VALUE / 10;
            }
        }
        dp[0][1] = dp[1][0] = hp;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int val = Math.max(dp[i - 1][j], dp[i][j - 1]) + dungeon[i - 1][j - 1];
                //没血了，别走了
                if (val <= 0) {
                    continue;
                }
                dp[i][j] = val;
            }
        }
        //还有血吗
        return dp[m][n] > 0;
    }

    /**
     * 方法二：直接 dp，从后往前
     * <p>
     * 来自 leetcode 官方题解：https://leetcode-cn.com/problems/dungeon-game/solution/di-xia-cheng-you-xi-by-leetcode-solution/
     * <p>
     * * 执行用时：2 ms，在所有 Java 提交中击败了 96.34% 的用户
     * 内存消耗：39.6 MB，在所有 Java 提交中击败了 100.00% 的用户
     * <p>
     * 时间复杂度：O(m * n)
     * 空间复杂度：O(m * n)
     *
     * @param dungeon dungeon
     * @return min hp
     */
    public int calculateMinimumHP2(int[][] dungeon) {
        int n = dungeon.length, m = dungeon[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[n][m - 1] = dp[n - 1][m] = 1;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {
                int minn = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(minn - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }
}
