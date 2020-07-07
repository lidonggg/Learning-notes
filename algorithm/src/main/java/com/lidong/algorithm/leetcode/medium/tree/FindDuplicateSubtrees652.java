package com.lidong.algorithm.leetcode.medium.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 寻找重复的子树（中等-652）
 * 中文链接：https://leetcode-cn.com/problems/find-duplicate-subtrees
 * <p>
 * 问题描述：
 * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * <p>
 * 两棵树重复是指它们具有相同的结构以及相同的结点值。
 * <p>
 * 示例 1：
 * <p>        1
 * <p>       / \
 * <p>      2   3
 * <p>     /   / \
 * <p>    4   2   4
 * <p>       /
 * <p>      4
 * 下面是两个重复的子树：
 * <p>      2
 * <p>     /
 * <p>    4
 * 和
 * <p>    4
 * 因此，你需要以列表的形式返回上述重复子树的根结点。
 *
 * @author Ls J
 * @date 2020/7/8 1:08 AM
 */
public class FindDuplicateSubtrees652 {

    private Map<String, Integer> count;

    private List<TreeNode> ans;

    /**
     * 方法一：序列化，并针对每个节点的序列化去 map 中判断
     * <p>
     * 执行用时：27 ms，在所有 Java 提交中击败了 62.96% 的用户
     * 内存消耗：48.4 MB，在所有 Java 提交中击败了 100.00% 的用户
     * <p>
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     *
     * @param root root
     * @return list
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        count = new HashMap<>();
        ans = new ArrayList<>();
        collect(root);
        return ans;
    }

    /**
     * 序列化
     *
     * @param node cur node
     * @return str
     */
    public String collect(TreeNode node) {
        if (node == null) {
            return "#";
        }
        String serial = node.val + "," + collect(node.left) + "," + collect(node.right);
        count.put(serial, count.getOrDefault(serial, 0) + 1);
        if (count.get(serial) == 2) {
            ans.add(node);
        }
        return serial;
    }

    /**
     * 以下是方法二
     */
    private int t;

    private Map<String, Integer> trees;

    private Map<Integer, Integer> cnt;

    /**
     * 方法二：唯一标识符
     * 假设每棵子树都有一个唯一标识符：只有当两个子树的 id 相同时，认为这两个子树是相同的。
     * 一个节点 node 的左孩子 id 为 x，右孩子 id 为 y，那么该节点的 id 为 (node.val, x, y)。
     * 算法：
     * 如果三元组 (node.val, x, y) 第一次出现，则创建一个这样的三元组记录该子树。如果已经出现过，则直接使用该子树对应的 id。
     *
     * <p>
     * 执行用时：17 ms，在所有 Java 提交中击败了 94.14% 的用户
     * 内存消耗：41.8 MB，在所有 Java 提交中击败了 100.00% 的用户
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param root root
     * @return list
     */
    public List<TreeNode> findDuplicateSubtrees2(TreeNode root) {
        t = 1;
        trees = new HashMap<>();
        cnt = new HashMap<>();
        ans = new ArrayList<>();
        lookup(root);
        return ans;
    }

    public int lookup(TreeNode node) {
        if (node == null) {
            return 0;
        }
        String serial = node.val + "," + lookup(node.left) + "," + lookup(node.right);
        int uid = trees.computeIfAbsent(serial, x -> t++);
        cnt.put(uid, cnt.getOrDefault(uid, 0) + 1);
        if (cnt.get(uid) == 2) {
            ans.add(node);
        }
        return uid;
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
