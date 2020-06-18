package com.lidong.algorithm.leetcode.medium.binarysearch;

/**
 * 在 D 天内送达包裹的能力（中等-1011）
 * 中文链接：https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/
 * <p>
 * 问题描述：
 * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 * <p>
 * 示例 1：
 * 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * 输出：15
 * 解释：
 * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
 * 第 1 天：1, 2, 3, 4, 5
 * 第 2 天：6, 7
 * 第 3 天：8
 * 第 4 天：9
 * 第 5 天：10
 * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
 * <p>
 * 示例 2：
 * 输入：weights = [3,2,2,4,1,4], D = 3
 * 输出：6
 * 解释：
 * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
 * 第 1 天：3, 2
 * 第 2 天：2, 4
 * 第 3 天：1, 4
 * <p>
 * 示例 3：
 * 输入：weights = [1,2,3,1,1], D = 4
 * 输出：3
 * 解释：
 * 第 1 天：1
 * 第 2 天：2
 * 第 3 天：3
 * 第 4 天：1, 1
 * <p>
 * 提示：
 * 1 <= D <= weights.length <= 50000
 * 1 <= weights[i] <= 500
 *
 * @author ls J
 * @date 2020/6/18 21:24
 */
public class CapacityToShipPackagesWithinDDays1011 {

    /**
     * 二分查找
     * <p>
     * 执行用时：9 ms，在所有 Java 提交中击败了 95.83% 的用户
     * 内存消耗：43.4 MB，在所有 Java 提交中击败了 50.00% 的用户
     *
     * @param weights weights
     * @param d       d
     * @return 最小运送能力
     */
    public int shipWithinDays(int[] weights, int d) {
        int len = weights.length;
        if (len == 0) {
            return 0;
        }
        int total = 0;
        // 最大需要运送能力肯定不会超过货物重量总和
        for (int w : weights) {
            total += w;
        }

        int l = 1, r = total;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (check(weights, len, mid, d)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    /**
     * check
     *
     * @param weights weights
     * @param len     len of weights
     * @param load    载重能力
     * @param d       运送次数
     * @return true 如果可以
     */
    private boolean check(int[] weights, int len, int load, int d) {
        int dd = 1;
        int ll = 0;
        for (int i = 0; i < len; ++i) {
            if (ll + weights[i] <= load) {
                ll += weights[i];
            } else {
                if (weights[i] > load) {
                    return false;
                }
                ll = weights[i];
                dd++;
            }
        }

        return dd <= d;
    }
}
