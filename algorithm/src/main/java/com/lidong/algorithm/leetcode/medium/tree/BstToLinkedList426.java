package com.lidong.algorithm.leetcode.medium.tree;

/**
 * 二叉搜索树与双向链表（中等-426）
 * 中文链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof
 * <p>
 * 问题描述：
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 * <p>
 * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
 *
 * @author Ls J
 * @date 2020/7/5 6:25 PM
 */
public class BstToLinkedList426 {

    private TreeNode head, pre;

    /**
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.4 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param root root
     * @return head
     */
    public TreeNode treeToDoublyList(TreeNode root) {
        if (null == root) {
            return null;
        }
        dfs(root);
        // 头尾节点相互指向
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void dfs(TreeNode cur) {
        if (null == cur) {
            return;
        }
        // dfs 左子树
        dfs(cur.left);
        // pre 用于记录双向链表中位于 cur 左侧的节点，即上一次迭代中的 cur，pre!=null 时，cur 左侧存在节点 pre，需要进行 pre.right=cur 的操作
        if (null != pre) {
            pre.right = cur;
        } else {
            // 反之，当 pre==null 时，cur 左侧没有节点,即此时 cur 为双向链表中的头节点
            head = cur;
        }
        cur.left = pre;
        // 让 pre 指向当前节点 cur，作为右子树的前驱链表节点
        pre = cur;
        // dfs 右子树
        dfs(cur.right);
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
