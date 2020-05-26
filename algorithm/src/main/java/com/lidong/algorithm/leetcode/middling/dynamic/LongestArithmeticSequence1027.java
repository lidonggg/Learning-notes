package com.lidong.algorithm.leetcode.middling.dynamic;

/**
 * 最长等差序列（中等-1027）
 * 中文链接：https://leetcode-cn.com/problems/longest-arithmetic-sequence/
 * <p>
 * 问题描述：
 * 给定一个整数数组 A，返回 A 中最长等差子序列的长度。
 * 回想一下，A 的子序列 B 是列表 A[i_1], A[i_2], ..., A[i_k] 其中 0 <= i_1 < i_2 < ... < i_k <= A.length - 1。
 * 并且如果 B[i+1] - B[i]( 0 <= i < B.length - 1) 的值都相同，那么序列 A 是等差的。
 * <p>
 * 示例 1：
 * 输入：[3,6,9,12]
 * 输出：4
 * 解释：
 * 整个数组是公差为 3 的等差数列。
 * <p>
 * 示例 2：
 * 输入：[9,4,7,2,10]
 * 输出：3
 * 解释：
 * 最长的等差子序列是 [4,7,10]。
 * <p>
 * 示例 3：
 * 输入：[20,1,15,3,10,5,8]
 * 输出：4
 * 解释：
 * 最长的等差子序列是 [20,15,10,5]。
 * <p>
 * 提示：
 * 2 <= A.length <= 2000
 * 0 <= A[i] <= 10000
 *
 * @author dlif
 * @date 2020/5/25 21:22
 */
public class LongestArithmeticSequence1027 {

    /**
     * 方法一：暴力 dp 求解，元素差 dp
     * 根据每两个元素之差枚举当前公差所能出现的最大个数
     * <p>
     * leetcode 测试超出时间范围，通过测试用例 34/35，最后一个超时
     *
     * @param arr arr
     * @return res
     */
    public int longestArithSeqLength(int[] arr) {
        int len;
        if ((len = arr.length) <= 2) {
            return len;
        }
        // tmp 数组中有一多半的空间是没有用到的
        int[][] tmp = new int[len][len];
        for (int i = 0; i < len; ++i) {
            for (int j = i + 1; j < len; ++j) {
                // 保存第 j 个位置与第 i 个位置的差
                tmp[j][i] = arr[i] - arr[j];
            }
        }

        // 接下来遍历 tmp 数组，找到出现次数最多的公差
        // 由于 tmp 有一半的空间是浪费的，所以我们可以回填 tmp 的左下角，tmp[j][i] 被映射成 tmp[i][j]
        // tmp[i][j] = 1 代表当前元素 tmp[j][i] 已经被遍历过了，下次可以不必再去看它
        // dp[i] 保存当前到第 i 个元素为止，最长的等差数列的元素个数
        int res = 2;
        for (int i = 0; i < len; ++i) {
            for (int j = i + 1; j < len; ++j) {
                if (tmp[i][j] == 1) {
                    continue;
                }
                // 最开始应该是 2
                int total = 2;
                tmp[i][j] = 1;
                int curSeq = tmp[j][i];
                // 如果要和当前元素构成等差数列，那么 tmp 中下一列应该是当前的下标 j
                // 比如当前元素是 tmp[x][y]，y 代表前一个元素，那么要寻找下一个的话，应该在 tmp[x+1,x+2,..][x] 中找
                int nextI = j, nextJ = nextI + 1;

                while (nextJ < len) {
                    // 每找到一个，都将 total + 1，然后继续去找下一个
                    if (tmp[nextJ][nextI] == curSeq) {
                        total++;
                        tmp[nextI][nextJ] = 1;
                        nextI = nextJ;
                        nextJ = nextI + 1;
                    } else {
                        nextJ++;
                    }
                }
                res = Math.max(res, total);
            }
        }
        return res;
    }

    /**
     * 方法二：动态规划
     * 对每两个元素之差的所有可能情况 [-10000, 10000] 做 dp
     * <p>
     * 执行用时：118 ms，在所有 Java 提交中击败了 80.72% 的用户
     * 内存消耗：219.3 MB，在所有 Java 提交中击败了 33.33% 的用户
     *
     * @param arr arr
     * @return res
     */
    public int longestArithSeqLength2(int[] arr) {
        // 差的最小值是 -10000，最大值是 10000
        // 如果想节省空间复杂度，也可以先遍历一遍数组，然后取最大最小值，然后确定 dp 数组的大小
        // dp[i][j] 表示到子数组 [0..i] 差值为 j 的子数组(该子数组以 A[i] 元素结尾)的最长长度。
        int[][] dp = new int[arr.length][20001];
        // 差偏移量，+10000 之后就能保证全部是从 0 开始，从而可以用下标来表示
        int offset = 10000;
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                int seq = arr[i] - arr[j] + offset;
                // 注意等号右边是 dp[j][seq]
                // 这样 i 、j 、j - seq ... 构成了等差数列
                dp[i][seq] = dp[j][seq] + 1;
                res = Math.max(res, dp[i][seq]);
            }
        }
        return res + 1;
    }

    public static void main(String[] args) {
//        int[] arr = {1, 124, 1512, 125, 3, 122, 125, 141, 6, 4, 4, 4, 9, 22, 11, 1, 11, 11, 12, 1, 15, 2, 2, 2, 18, 1, 1, 1, 21, 7, 7, 7};
        int[] arr = {22, 8, 57, 41, 36, 46, 42, 28, 42, 14, 9, 43, 27, 51, 0, 0, 38, 50, 31, 60, 29, 31, 20, 23, 37, 53, 27, 1, 47, 42, 28, 31, 10, 35, 39, 12, 15, 6, 35, 31, 45, 21, 30, 19, 5, 5, 4, 18, 38, 51, 10, 7, 20, 38, 28, 53, 15, 55, 60, 56, 43, 48, 34, 53, 54, 55, 14, 9, 56, 52
        };
        LongestArithmeticSequence1027 las = new LongestArithmeticSequence1027();
        System.out.println(las.longestArithSeqLength(arr));
    }
}
