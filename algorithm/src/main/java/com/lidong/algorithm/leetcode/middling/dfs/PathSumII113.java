package com.lidong.algorithm.leetcode.middling.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 路径总和 II（中等-113）
 * 中文链接：https://leetcode-cn.com/problems/path-sum-ii
 * <p>
 * 问题描述：
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>              5
 * <p>             / \
 * <p>            4   8
 * <p>           /   / \
 * <p>          11  13  4
 * <p>         /  \    / \
 * <p>        7    2  5   1
 * 返回:
 * [
 * <p>   [5,4,11,2],
 * <p>   [5,8,4,5]
 * ]
 *
 * @author ls J
 * @date 2020/5/12 9:43
 */
public class PathSumII113 {

    private List<List<Integer>> result = new ArrayList<>();

    private int sumRes;

    /**
     * 方法一：DFS
     *
     * @param root root
     * @param sum  target sum
     * @return list
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (null == root) {
            return result;
        }
        this.sumRes = sum;
        List<Integer> ls = new ArrayList<>();
        ls.add(root.val);
        recurse(root, ls, root.val);
        return result;
    }

    /**
     * DFS
     * 针对每个节点的左右节点都分别去遍历，要注意 list 的指针引用的问题
     *
     * @param node    当前节点
     * @param curList 当前遍历的数组
     * @param curSum  当前 sum
     */
    private void recurse(TreeNode node, List<Integer> curList, int curSum) {
        if (null == node.left && null == node.right) {
            if (curSum == sumRes) {
                result.add(curList);
            }
            return;
        }
        // 处理左右节点都存在的情况，这个时候需要拷贝一份 list
        if (null != node.left && null != node.right) {
            List<Integer> cloneLeftList = new ArrayList<>(curList);
            cloneLeftList.add(node.left.val);
            recurse(node.left, cloneLeftList, curSum + node.left.val);

            curList.add(node.right.val);
            recurse(node.right, curList, curSum + node.right.val);
            return;
        }
        if (null != node.left) {
            curList.add(node.left.val);
            recurse(node.left, curList, curSum + node.left.val);
        }
        if (null != node.right) {
            curList.add(node.right.val);
            recurse(node.right, curList, curSum + node.right.val);
        }
    }

    /**
     * 方法二：回溯
     * 来自：https://leetcode-cn.com/problems/path-sum-ii/solution/113java-hui-su-xiang-jie-da-bai-9998-by-ustcyyw
     *
     * @param root root
     * @param sum  sum
     * @return list
     */
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        if (root == null) {
            return result;
        }
        this.sumRes = sum;
        backTrack(root, 0, new ArrayList<>());
        return result;
    }

    private void backTrack(TreeNode x, int curSum, List<Integer> vals) {
        vals.add(x.val);
        curSum += x.val;
        if (x.left == null && x.right == null) {
            if (curSum == sumRes) {
                // 要 new 一个 list
                result.add(new ArrayList<>(vals));
            }
            // 进行回溯
            vals.remove(vals.size() - 1);
            return;
        }
        if (x.left != null) {
            backTrack(x.left, curSum, vals);
        }
        if (x.right != null) {
            backTrack(x.right, curSum, vals);
        }
        // 进行回溯
        vals.remove(vals.size() - 1);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

