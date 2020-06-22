package com.lidong.algorithm.leetcode.medium.dfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 删点成林（中等-1110）
 * 中文链接：https://leetcode-cn.com/problems/delete-nodes-and-return-forest/
 * <p>
 * 问题描述：
 * 给出二叉树的根节点 root，树上每个节点都有一个不同的值。
 * 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 * 返回森林中的每棵树。你可以按任意顺序组织答案。
 * <p>
 * 示例：
 * 输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * 输出：[[1,2,null,4],[6],[7]]
 * <p>
 * 提示：
 * - 树中的节点数最大为 1000。
 * - 每个节点都有一个介于 1 到 1000 之间的值，且各不相同。
 * - to_delete.length <= 1000
 * - to_delete 包含一些从 1 到 1000、各不相同的值。
 *
 * @author ls J
 * @date 2020/6/22 16:34
 */
public class DeleteNodesAndReturnForest1110 {

    private List<TreeNode> result = new LinkedList<>();

    private Set<Integer> deleteSet = new HashSet<>();

    /**
     * dfs 做法
     * <p>
     * 执行用时：2 ms，在所有 Java 提交中击败了 89.35% 的用户
     * 内存消耗：40.3 MB，在所有 Java 提交中击败了 50.00% 的用户
     *
     * @param root     根节点
     * @param toDelete 要删除的点
     * @return 林
     */
    public List<TreeNode> delNodes(TreeNode root, int[] toDelete) {
        if (null == root) {
            return result;
        }
        for (int num : toDelete) {
            deleteSet.add(num);
        }
        if (!deleteSet.contains(root.val)) {
            result.add(root);
        }
        dfs(root, root.left, true);
        dfs(root, root.right, false);
        return result;
    }

    /**
     * dfs
     *
     * @param parent 父节点
     * @param child  子节点
     * @param left   子节点如果为父节点的左子节点，那么此值为 true，否则为 false
     */
    private void dfs(TreeNode parent, TreeNode child, boolean left) {
        if (null == child) {
            return;
        }
        // 如果子节点是要被删除的节点，那么需要将父节点对应的子节点变为 null
        if (deleteSet.contains(child.val)) {
            if (left) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else {
            // 如果父节点是被删除的节点，那么当前节点需要添加到 result 中
            if (deleteSet.contains(parent.val)) {
                result.add(child);
            }
        }

        dfs(child, child.left, true);
        dfs(child, child.right, false);
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
