package com.lidong.algorithm.leetcode.easy.array;

import java.util.*;

/**
 * 总持续时间可被 60 整除的歌曲（简单-1010）
 * 中文链接：https://leetcode-cn.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
 * 问题描述：
 * 在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。
 * 返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，我们希望索引的数字 i 和 j 满足  i < j 且有 (time[i] + time[j]) % 60 == 0。
 * <p>
 * 示例 1：
 * 输入：[30,20,150,100,40]
 * 输出：3
 * 解释：这三对的总持续时间可被 60 整数：
 * (time[0] = 30, time[2] = 150): 总持续时间 180
 * (time[1] = 20, time[3] = 100): 总持续时间 120
 * (time[1] = 20, time[4] = 40): 总持续时间 60
 * <p>
 * 示例 2：
 * 输入：[60,60,60]
 * 输出：3
 * 解释：所有三对的总持续时间都是 120，可以被 60 整数。
 * <p>
 * 提示：
 * 1 <= time.length <= 60000
 * 1 <= time[i] <= 500
 *
 * @author Ls J
 * @date 2020/5/10 9:16 PM
 */
public class PairsOfSongsWithTotalDurationsDivisibleby601010 {

    private static Map<Integer, Integer> cache = new HashMap<>();

    static {
        cache.put(60, 1);
        cache.put(120, 1);
        cache.put(180, 1);
        cache.put(240, 1);
        cache.put(300, 1);
        cache.put(360, 1);
        cache.put(420, 1);
        cache.put(480, 1);
        cache.put(540, 1);
        cache.put(600, 1);
        cache.put(660, 1);
        cache.put(720, 1);
        cache.put(780, 1);
        cache.put(840, 1);
        cache.put(900, 1);
        cache.put(960, 1);
    }

    /**
     * 方法1：双层循环，暴力求解
     *
     * @param time time arr
     * @return total res
     */
    public int numPairsDivisibleBy60(int[] time) {
        int res = 0;

        int len = time.length;
        for (int i = 0; i < len - 1; ++i) {
            for (int j = i + 1; j < len; ++j) {
                if (cache.containsKey(time[i] + time[j])) {
                    res++;
                }
            }
        }

        return res;
    }

    /**
     * 方法2：一层遍历
     * 1. 整数对60取模，可能有60种余数。故初始化一个长度为60的数组，统计各余数出现的次数。
     * 2. 遍历time数组，每个值对60取模，并统计每个余数值（0-59）出现的个数。因为余数部分需要找到合适的cp组合起来能被60整除。
     * 3. 余数为0的情况，只能同余数为0的情况组合（如60s、120s等等）。0的情况出现k次，则只能在k中任选两次进行两两组合。本题解单独写了个求组合数的方法，也可以用k * (k - 1) / 2表示。
     * 4. 余数为30的情况同上。
     * 5. 其余1与59组合，2与58组合，故使用双指针分别从1和59两头向中间遍历。1的情况出现m次，59的情况出现n次，则总共有m*n种组合。
     *
     * @param time time arr
     * @return total res
     */
    public int numPairsDivisibleBy601(int[] time) {
        int count = 0;
        // 保存余数
        int[] mods = new int[60];
        for (int t : time) {
            mods[t % 60] += 1;
        }
        // 余数为 0 和 30 的情况，只能同自己组合
        count += combination(mods[30], 2);
        count += combination(mods[0], 2);
        int i = 1, j = 59;
        while (i < j) {
            count += mods[i++] * mods[j--];
        }
        return count;
    }

    /**
     * 求组合数
     *
     * @param n 一共 n 个数
     * @param k k 个数组合
     * @return res
     */
    private int combination(int n, int k) {
        long result = 1;
        for (int i = 1; i <= k; i++) {
            result = result * (n - i + 1) / i;
        }
        return (int) result;
    }
}
