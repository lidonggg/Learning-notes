package com.lidong.algorithm.leetcode.medium.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 完全二叉树插入器（中等-919）
 * 中文链接：https://leetcode-cn.com/problems/complete-binary-tree-inserter
 * <p>
 * 问题描述：
 * 完全二叉树是每一层（除最后一层外）都是完全填充（即，节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。
 * 设计一个用完全二叉树初始化的数据结构 CBTInserter，它支持以下几种操作：
 * 1. CBTInserter(TreeNode root) 使用头节点为 root 的给定树初始化该数据结构；
 * 2. CBTInserter.insert(int v)  向树中插入一个新节点，节点类型为 TreeNode，值为 v 。使树保持完全二叉树的状态，并返回插入的新节点的父节点的值；
 * 3. CBTInserter.get_root() 将返回树的头节点。
 *  
 * 示例 1：
 * 输入：inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
 * 输出：[null,1,[1,2]]
 * <p>
 * 示例 2：
 * 输入：inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
 * 输出：[null,3,4,[1,2,3,4,5,6,7,8]]
 *  
 * 提示：
 * 1. 最初给定的树是完全二叉树，且包含 1 到 1000 个节点。
 * 2. 每个测试用例最多调用 CBTInserter.insert  操作 10000 次。
 * 3. 给定节点或插入节点的每个值都在 0 到 5000 之间。
 *
 * @author Ls J
 * @date 8/12/20 9:33 PM
 */
class CBTInserter919 {

    /**
     * 队列中保存接下来需要添加孩子的节点
     */
    Queue<TreeNode> queue;

    TreeNode root;

    /**
     * 当前要添加孩子的节点
     */
    TreeNode curNode;

    /**
     * 执行用时：19 ms，在所有 Java 提交中击败了 99.57% 的用户
     * 内存消耗：40.2 MB，在所有 Java 提交中击败了 82.76% 的用户
     *
     * @param root root
     */
    public CBTInserter919(TreeNode root) {
        if (null == root) {
            return;
        }
        this.queue = new LinkedList<>();
        this.root = root;
        TreeNode tmp = root;
        // 如果左右孩子都不是 null，代表当前节点已满
        while (null != tmp.left && null != tmp.right) {
            queue.offer(tmp.left);
            queue.offer(tmp.right);
            // 继续看下一个节点
            tmp = queue.poll();
        }

        if (null != tmp.left) {
            queue.offer(tmp.left);
        }
        this.curNode = tmp;
    }

    public int insert(int v) {
        TreeNode nn = new TreeNode(v);
        int ps = curNode.val;
        if (null == curNode.left) {
            curNode.left = nn;
        } else {
            curNode.right = nn;
            // 当前节点已满，去看下一个节点
            curNode = queue.poll();
        }
        queue.offer(nn);


        return ps;
    }

    public TreeNode getRoot() {
        return this.root;
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
