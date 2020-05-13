package com.lidong.algorithm.leetcode.hard.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 恢复二叉搜索树（中等-99）
 * 中文链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 * <p>
 * 问题描述：
 * 二叉搜索树中的两个节点被错误地交换。
 * 请在不改变其结构的情况下，恢复这棵树。
 * <p>
 * 示例 1:
 * 输入: [1,3,null,null,2]
 * <p>    1
 * <p>   /
 * <p>  3
 * <p>   \
 * <p>    2
 * 输出: [3,1,null,null,2]
 * <p>    3
 * <p>   /
 * <p>  1
 * <p>   \
 * <p>    2
 * <p>
 * 示例 2:
 * 输入: [3,1,4,null,null,2]
 * <p>   3
 * <p>  / \
 * <p> 1   4
 * <p>    /
 * <p>   2
 * 输出: [2,1,4,null,null,3]
 * <p>   2
 * <p>  / \
 * <p> 1   4
 * <p>    /
 * <p>  3
 * <p>
 * 进阶:
 * 使用 O(n) 空间复杂度的解法很容易实现，
 * 你能想出一个只使用常数空间的解决方案吗？
 * <p>
 * 纪念一下：第一个完整写出来的困难级别的题目=.=
 *
 * @author ls J
 * @date 2020/5/12 11:43 PM
 */
public class RecoverBinarySearchTree99 {

    private List<TreeNode> nodes = new ArrayList<>();

    /**
     * 方法一：
     * 1.中序遍历；
     * 2.找到两个位置交换的节点（从前往后找第一个，从后往前找第二个）；
     * 3.更换两个节点的值
     * O(n) 空间复杂度
     *
     * @param root root 节点
     */
    public void recoverTree(TreeNode root) {
        if (null == root || (null == root.left && null == root.right)) {
            return;
        }
        // 中序遍历，如果是搜索二叉树，那么值的大小应该是从小到大变化的
        createInorderList(root);
        TreeNode wrong1 = null, wrong2 = null;
        // 从前往后遍历找 wrong1
        for (int i = 0; i < nodes.size() - 1; ++i) {
            if (nodes.get(i).val > nodes.get(i + 1).val) {
                wrong1 = nodes.get(i);
                break;
            }
        }
        // 从后往前遍历找 wrong2
        for (int i = nodes.size() - 1; i > 0; --i) {
            if (nodes.get(i).val < nodes.get(i - 1).val) {
                wrong2 = nodes.get(i);
                break;
            }
        }
        if (null != wrong1 && null != wrong2) {
            swapValue(wrong1, wrong2);
        }
    }

    /**
     * 中序遍历
     *
     * @param curNode node
     */
    private void createInorderList(TreeNode curNode) {
        if (null == curNode) {
            return;
        }
        createInorderList(curNode.left);
        nodes.add(curNode);
        createInorderList(curNode.right);
    }

    /**
     * 交换两个节点的值
     *
     * @param node1 node1
     * @param node2 node2
     */
    private void swapValue(TreeNode node1, TreeNode node2) {
        // 交换两个节点的值
        int v1 = node1.val;
        node1.val = node2.val;
        node2.val = v1;
    }

    private TreeNode swapped1 = null;

    private TreeNode swapped2 = null;

    private TreeNode lastNode = null;

    /**
     * 方法二：O(h) 空间复杂度，h 是树的高度
     * 执行用时：2ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了 37.50% 的用户
     *
     * @param root 根节点
     */
    public void recoverTree2(TreeNode root) {
        if (null == root || (null == root.left && null == root.right)) {
            return;
        }
        inorderTraverse(root);
        if (null != swapped1 && null != swapped2) {
            swapValue(swapped1, swapped2);
        }
    }

    /**
     * 中序遍历，每次都拿当前节点跟它上一个节点的值作比较
     *
     * @param curNode current node
     */
    private void inorderTraverse(TreeNode curNode) {
        if (null == curNode) {
            return;
        }

        inorderTraverse(curNode.left);

        if (lastNode != null) {
            if (curNode.val < lastNode.val) {
                // 如果 swapped1 是 null 的话，代表是第一个交换的值
                // 此时被交换的是 lastNode
                // 否则是 curNode
                // 比如：1 6 3 4 5 2 7 8，6 与 2 被交换
                // 当遍历到 3 的时候，发现它比上一个小，此时 6 就是 swapped1
                // 当遍历到 2 的时候，发现它比上一个小，此时 2 就是 swapped2
                //
                // 还有一种情况是相邻的两个节点被交换了位置
                // 比如：1 2 3 5 4 6 7 8，5 与 4 被交换
                // 这个时候如果按照上面的逻辑会发现只能将 swapped1 赋值
                // 因此这个时候，当 swapped1 为 null 的时候，我们一起将当前节点也赋值给 swapped2
                // 当下次也出现了逆序的时候，再更新它的值就好了
                if (null == swapped1) {
                    swapped1 = lastNode;
                    swapped2 = curNode;
                } else {
                    swapped2 = curNode;
                    return;
                }
            }
        }
        lastNode = curNode;

        inorderTraverse(curNode.right);
    }

    /**
     * 方法三：Morris 中序遍历
     * 来自官方题解：https://leetcode-cn.com/problems/recover-binary-search-tree/solution/hui-fu-er-cha-sou-suo-shu-by-leetcode
     * <p>
     * Morris 的遍历思想很简单：只遍历树而不是用空间。
     * 怎么能够做到呢？在每个节点上，你必须决定下一个遍历的方向：遍历左子树或者右子树。如果不适用额外的空间，怎么指的左子树已经遍历完成了呢？
     * Morris 算法的思想是在节点和它的直接前驱之间设置一个临时的链接：predecessor.right = root，从该节点开始，找到它的直接前驱并验证是否存在链接。
     * 1.如果没有连接，设置连接并走向左子树。
     * 2.如果有连接，断开连接并走向右子树。
     * <p>
     * Morris 中序遍历的步骤如下：
     * 1. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
     * 2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
     * a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
     * b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。
     * 3. 重复以上1、2直到当前节点为空。
     * 参考链接：https://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
     *
     * @param root 根节点
     */
    public void recoverTree3(TreeNode root) {
        TreeNode x = null, y = null, pred = null, predecessor;

        while (root != null) {
            if (root.left != null) {
                // 查找 predecessor
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // 2.a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 2.b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。
                else {
                    // 检查是否是被交换过的节点
                    if (pred != null && root.val < pred.val) {
                        y = root;
                        if (x == null) {
                            x = pred;
                        }
                    }
                    pred = root;
                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 1. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
            else {
                // 检查是否是被交换过的节点
                if (pred != null && root.val < pred.val) {
                    y = root;
                    if (x == null) {
                        x = pred;
                    }
                }
                pred = root;

                root = root.right;
            }
        }
        if (null != x) {
            swapValue(x, y);
        }

    }

    public static void main(String[] args) {
        RecoverBinarySearchTree99 rb = new RecoverBinarySearchTree99();
        TreeNode root = new TreeNode(2);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(1);
        root.left = node2;
        root.right = node4;
        node4.left = node3;
        rb.recoverTree2(root);
        for (TreeNode node : rb.nodes) {
            System.out.println(node.val);
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
