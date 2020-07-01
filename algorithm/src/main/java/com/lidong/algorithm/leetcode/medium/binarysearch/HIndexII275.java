package com.lidong.algorithm.leetcode.medium.binarysearch;

/**
 * H 指数 II（中等-275）
 * 中文链接：https://leetcode-cn.com/problems/h-index-ii/
 * <p>
 * 问题描述：
 * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数），数组已经按照升序排列。编写一个方法，计算出研究者的 h 指数。
 * h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数不多于 h 次。）" 
 * <p>
 * 示例:
 * 输入: citations = [0,1,3,5,6]
 * 输出: 3
 * 解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
 *      由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。
 *  
 * 说明:
 * 如果 h 有多有种可能的值 ，h 指数是其中最大的那个。
 * <p>
 * 进阶：
 * 这是 H指数 的延伸题目，本题中的 citations 数组是保证有序的。
 * 你可以优化你的算法到对数时间复杂度吗？
 *
 * @author ls J
 * @date 2020/7/1 11:05
 */
public class HIndexII275 {

    /**
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：47.2 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param citations citations
     * @return h index
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            // [mid, n] 之间的元素个数
            int ri = n - mid;
            // 元素值与 ri 进行比较
            if (citations[mid] < ri) {
                l = mid + 1;
            } else if (citations[mid] > ri) {
                r = mid - 1;
            } else {
                return ri;
            }
        }
        return n - l;
    }
}
