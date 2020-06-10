package com.lidong.algorithm.interview.bytedance;

/**
 * 问题描述：
 * 给定一个规则S_0 = {1} S_1={1,2,1} S_2 = {1,2,1,3,1,2,1} S_n = {S_n-1 , n + 1, S_n-1}。
 * 第一个问题是他们的个数有什么关系(1 3 7 15... 2 的 n 次方-1,用位运算解决)。
 * 第二个问题是给定数组个数下标 n 和索引 k，让我们求出 S_n(k) 所指的数,例如 S_2(2) = 1
 *
 * @author ls J
 * @date 2020/6/5 17:05
 */
public class SnFindKthValue {

    public static void main(String[] args) {
        System.out.println((2 << 3) - 1);

        System.out.println(1110 & 1);
        System.out.println(findVal(4, 15));
    }

    public static int findVal(int n, int k) {
        int tmp = n - 1;
        if ((k & 1) == 0) {
            return 1;
        }
        if (k >= (2 << tmp) - 1) {
            return -1;
        }
        while (tmp != 0) {
            int slice = 2 << (tmp - 1);
            if ((k << 1) > (2 << tmp)) {
                k = k - slice;
            } else if (k == slice - 1) {
                return tmp + 1;
            }
            tmp = tmp - 1;
        }

        return 1;
    }
}
