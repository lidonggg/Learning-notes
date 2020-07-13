package com.lidong.algorithm.leetcode.medium.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 所有可能的满二叉树（中等-894）
 * 中文链接：https://leetcode-cn.com/problems/all-possible-full-binary-trees
 * <p>
 * 问题描述：
 * 满二叉树是一类二叉树，其中每个结点恰好有 0 或 2 个子结点。
 * 返回包含 N 个结点的所有可能满二叉树的列表。 答案的每个元素都是一个可能树的根结点。
 * 答案中每个树的每个结点都必须有 node.val=0。
 * 你可以按任何顺序返回树的最终列表。
 * <p>
 * 示例：
 * 输入：7
 * 输出：[[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
 * 解释：
 * <p>
 * 提示：
 * 1 <= N <= 20
 *
 * @author ls J
 * @date 2020/7/13 19:25
 */
public class AllPossibleFullBinaryTrees894 {

    Map<Integer, List<TreeNode>> map = new HashMap<>();

    /**
     * dfs
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：42.5 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param n n
     * @return list
     */
    public List<TreeNode> allPossibleFBT(int n) {
        // 满二叉树一定有奇数个节点
        if (n % 2 == 0) {
            return new ArrayList<>();
        }
        List<TreeNode> res = new ArrayList<>();
        if (n == 1) {
            res.add(new TreeNode(0));
            return res;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        // 步长为 2 保证左右两侧各奇数个节点
        for (int i = 1; i < n; i += 2) {
            List<TreeNode> lefts = allPossibleFBT(i);
            List<TreeNode> rights = allPossibleFBT(n - i - 1);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        map.put(n, res);
        return res;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
