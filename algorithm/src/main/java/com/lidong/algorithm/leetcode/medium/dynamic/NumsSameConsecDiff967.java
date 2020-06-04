package com.lidong.algorithm.leetcode.medium.dynamic;

import com.lidong.algorithm.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 连续差相同的数字（中等-967）
 * 问题描述：
 * 返回所有长度为 N 且满足其每两个连续位上的数字之间的差的绝对值为 K 的非负整数。
 * 请注意，除了数字 0 本身之外，答案中的每个数字都不能有前导零。例如，01 因为有一个前导零，所以是无效的；但 0 是有效的。
 * 你可以按任何顺序返回答案。
 * <p>
 * 示例 1：
 * 输入：N = 3, K = 7
 * 输出：[181,292,707,818,929]
 * 解释：注意，070 不是一个有效的数字，因为它有前导零。
 * <p>
 * 示例 2：
 * 输入：N = 2, K = 1
 * 输出：[10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 *  
 * 提示：
 * 1 <= N <= 9
 * 0 <= K <= 9
 *
 * @author ls J
 * @date 2019/9/3 8:47 AM
 */
public class NumsSameConsecDiff967 {

    public static int[] numsSameConsecDiff(int n, int k) {
        if (n == 1) {
            return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        }

        List<Integer> total = new ArrayList<>();

        // 首先初始化一位数，后面的情况只要在它们的基础上进行扩展就行
        for (int i = 0; i < 9; ++i) {
            total.add((i + 1));
        }

        // 每次增加一位
        for (int i = 1; i < n; ++i) {
            List<Integer> tmp = new ArrayList<>();
            for (int cur : total) {
                // 获取到当前个位数的值
                int lastPos = cur;
                while (lastPos > 9) {
                    lastPos = lastPos % 10;
                }
                if (k != 0) {
                    // 通过最后一位进行加/减判断
                    if (lastPos + k <= 9) {
                        tmp.add(cur * 10 + (lastPos + k));
                    }
                    if (lastPos - k >= 0) {
                        tmp.add(cur * 10 + (lastPos - k));
                    }
                } else {
                    tmp.add(cur * 10 + lastPos);
                }
            }
            total = tmp;
        }

        int[] res = new int[total.size()];
        for (int i = 0; i < total.size(); ++i) {
            res[i] = total.get(i);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] res = numsSameConsecDiff(3, 7);
        ArrayUtil.printArray(res);
    }
}
