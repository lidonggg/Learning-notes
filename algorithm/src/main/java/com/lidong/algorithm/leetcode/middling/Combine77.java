package com.lidong.algorithm.leetcode.middling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合
 * 问题描述：
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <br>
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 *
 * @author ls J
 * @date 2019/7/15 1:52 PM
 */
public class Combine77 {

    /**
     * 存放结果集
     */
    private List<List<Integer>> result = new LinkedList<>();

    /**
     * n
     */
    private int n;

    /**
     * k
     */
    private int k;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        dfs(new ArrayList<>(), 1);
        return result;
    }

    /**
     * 使用dfs进行深度遍历，每次都有两个分支：当前元素插入或者不插入
     *
     * @param tmp tmp
     * @param cur 当前元素
     */
    private void dfs(List<Integer> tmp, int cur) {
        // 不足以构造出一个长度为k的数组，这里进行一次剪枝
        if (k - tmp.size() > n - cur + 1) {
            return;
        }
        if (tmp.size() == k) {
            result.add(tmp);
            return;
        }
        tmp = new ArrayList<>(tmp);
        // 当前元素不插入数组
        dfs(tmp, cur + 1);
        // 当前元素插入数组
        tmp.add(cur);
        dfs(tmp, cur + 1);
    }

    /**
     * 方法2，leetcode提供的方法，字典序（二进制排序）组合
     *
     * @param n n
     * @param k k
     * @return result
     */
    public List<List<Integer>> combine2(int n, int k) {
        // init first combination
        LinkedList<Integer> nums = new LinkedList<>();
        for (int i = 1; i < k + 1; ++i) {
            nums.add(i);
        }

        nums.add(n + 1);

        int j = 0;
        while (j < k) {
            // add current combination
            result.add(new LinkedList<>(nums.subList(0, k)));
            // increase first nums[j] by one
            // if nums[j] + 1 != nums[j + 1]
            j = 0;
            while ((j < k) && (nums.get(j + 1) == nums.get(j) + 1)) {
                nums.set(j, j++ + 1);
            }

            nums.set(j, nums.get(j) + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 6, k = 4;
        Combine77 combine77 = new Combine77();
        System.out.println(combine77.combine(n, k));
    }
}
