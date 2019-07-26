package com.lidong.algorithm.leetcode.middling.dynamic;

import java.util.List;

/**
 * @author ls J
 * @date 2019/7/25 8:34 AM
 * 三角形最小路径和（中等-120）
 * 问题描述：
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <br>
 * 例如，给定三角形：
 * <br>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 说明：
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class MinimumTotal120 {

    private int result = Integer.MAX_VALUE;

    private List<List<Integer>> triangle;

    /**
     * 按照题目的意思，上面的一层走第 i 个元素，则下面的一层只能走第 i 和第 i + 1 个元素（相邻节点），
     * 这样的话，下面的一层永远不会越界
     * <br>
     * 方法一：从最下层往上进行动态规划（最快）
     *
     * @param triangle 原数组
     * @return result
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size(), c;
        Integer[] res = triangle.get(size - 1).toArray(new Integer[0]);

        for (int i = size - 2; i >= 0; i--) {
            c = 0;
            for (Integer num : triangle.get(i)) {
                // 比较上一层相邻的两个数，然后与它本身进行相加
                res[c] = num + Math.min(res[c], res[c + 1]);
                c++;
            }
        }

        return res[0];
    }

    /**
     * 直接在原数组上进行操作，从上往下进行（比较耗时）
     *
     * @param triangle 原数组
     * @return result
     */
    public int minimumTotal1(List<List<Integer>> triangle) {
        int deep = triangle.size();
        // 首先判断数组长度是否符合规则
        for (int i = 0; i < deep; ++i) {
            if (triangle.get(i).size() != i + 1) {
                return 0;
            }
        }

        // 在原数组上进行更改
        for (int i = 1; i < deep; ++i) {
            // 首尾进行特殊处理
            triangle.get(i).set(0, triangle.get(i).get(0) + triangle.get(i - 1).get(0));
            triangle.get(i).set(i, triangle.get(i).get(i) + triangle.get(i - 1).get(i - 1));
            for (int j = 1; j < i; ++j) {
                // 中间的部分，分别计算两条路径，取其中较短的一条
                int left = triangle.get(i).get(j) + triangle.get(i - 1).get(j - 1);
                int right = triangle.get(i).get(j) + triangle.get(i - 1).get(j);
                triangle.get(i).set(j, Math.min(left,right));
            }
        }

        // 取最后一行中最小的值即可
        int result = triangle.get(deep - 1).get(0);
        for (int i = 1; i < deep; ++i) {
            if (result > triangle.get(deep - 1).get(i)) {
                result = triangle.get(deep - 1).get(i);
            }
        }
        return result;
    }

    /**
     * 普通 dfs 递归，每次遍历一层，有两个路径可选（leetcode上测试超出时间范围），可以进行剪枝优化
     *
     * @param cDeep  当前深度
     * @param cIndex 上一层走的位置
     * @param tmpRes 存放临时结果
     */
    private void commonRecursive(int cDeep, int cIndex, int tmpRes) {
        // 最后一层已经遍历结束
        if (cDeep == triangle.size()) {
            if (result > tmpRes) {
                result = tmpRes;
            }
            return;
        }
        int resLeft = tmpRes + triangle.get(cDeep).get(cIndex);
        commonRecursive(cDeep + 1, cIndex, resLeft);
        int resRight = tmpRes + triangle.get(cDeep).get(cIndex + 1);
        commonRecursive(cDeep + 1, cIndex + 1, resRight);
    }
}
