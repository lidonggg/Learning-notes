package com.lidong.algorithm.leetcode.middling.bfs;

import java.util.*;

/**
 * 二叉树的右视图（中等-199）
 * 中文链接：https://leetcode-cn.com/problems/binary-tree-right-side-view/
 * <p>
 * 问题描述：
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 示例:
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 * <p>   1            <---
 * <p> /   \
 * <p>2     3         <---
 * <p> \     \
 * <p>  5     4       <---
 *
 * @author ls J
 * @date 2020/5/21 9:45
 */
public class BinaryTreeRightSideView199 {

    /**
     * 方法一：BFS
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 95.00% 的用户
     * 内存消耗：37.8 MB，在所有 Java 提交中击败了 5.00% 的用户
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param root root
     * @return res list
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; ++i) {
                TreeNode curNode = queue.poll();
                // 这里的检查其实是不需要的，因为下面在 add 的时候已经对 null 值进行了检查
                assert null != curNode;
                // 取当前层最后一个元素
                if (i == len - 1) {
                    res.add(curNode.val);
                }
                if (null != curNode.left) {
                    queue.add(curNode.left);
                }
                if (null != curNode.right) {
                    queue.add(curNode.right);
                }
            }
        }
        return res;
    }

    /**
     * 方法二：DFS
     * 来着 leetcode 官方题解：https://leetcode-cn.com/problems/binary-tree-right-side-view/solution/er-cha-shu-de-you-shi-tu-by-leetcode-solution/
     * <p>
     * 我们对树进行深度优先搜索，在搜索过程中，我们总是先访问右子树。
     * 那么对于每一层来说，我们在这层见到的第一个结点一定是最右边的结点。
     *
     * @param root root
     * @return res
     */
    public List<Integer> rightSideView2(TreeNode root) {
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<>();
        int maxDepth = -1;

        // 用栈来存放 node
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();
        nodeStack.push(root);
        depthStack.push(0);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();
            if (node != null) {
                // 维护二叉树的最大深度
                maxDepth = Math.max(maxDepth, depth);
                // 如果不存在对应深度的节点我们才插入
                if (!rightmostValueAtDepth.containsKey(depth)) {
                    rightmostValueAtDepth.put(depth, node.val);
                }
                // 左子节点先入栈，右子节点先出栈，这样下一次访问的时候，就是先访问最右侧的节点
                nodeStack.push(node.left);
                nodeStack.push(node.right);
                depthStack.push(depth + 1);
                depthStack.push(depth + 1);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int depth = 0; depth <= maxDepth; depth++) {
            res.add(rightmostValueAtDepth.get(depth));
        }

        return res;
    }

    public static void main(String[] args) {

    }

    private class TreeNode {
        int val;

        TreeNode left;

        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
