package com.lidong.algorithm.leetcode.medium.tree;

/**
 * 在受污染的二叉树中查找元素（中等-1261）
 * 中文链接：https://leetcode-cn.com/problems/find-elements-in-a-contaminated-binary-tree
 * <p>
 * 问题描述：
 * 给出一个满足下述规则的二叉树：
 * 1. root.val == 0
 * 2. 如果 treeNode.val == x 且 treeNode.left != null，那么 treeNode.left.val == 2 * x + 1
 * 3. 如果 treeNode.val == x 且 treeNode.right != null，那么 treeNode.right.val == 2 * x + 2
 * 现在这个二叉树受到「污染」，所有的 treeNode.val 都变成了 -1。
 * 请你先还原二叉树，然后实现 FindElements 类：
 * FindElements(TreeNode* root) 用受污染的二叉树初始化对象，你需要先把它还原。
 * bool find(int target) 判断目标值 target 是否存在于还原后的二叉树中并返回结果。
 * <p>
 * 示例 1：
 * 输入：
 * ["FindElements","find","find"]
 * [[[-1,null,-1]],[1],[2]]
 * 输出：
 * [null,false,true]
 * 解释：
 * FindElements findElements = new FindElements([-1,null,-1]);
 * findElements.find(1); // return False
 * findElements.find(2); // return True
 * <p>
 * 示例 2：
 * 输入：
 * ["FindElements","find","find","find"]
 * [[[-1,-1,-1,-1,-1]],[1],[3],[5]]
 * 输出：
 * [null,true,true,false]
 * 解释：
 * FindElements findElements = new FindElements([-1,-1,-1,-1,-1]);
 * findElements.find(1); // return True
 * findElements.find(3); // return True
 * findElements.find(5); // return False
 * <p>
 * 示例 3：
 * 输入：
 * ["FindElements","find","find","find","find"]
 * [[[-1,null,-1,-1,null,-1]],[2],[3],[4],[5]]
 * 输出：
 * [null,true,false,false,true]
 * 解释：
 * FindElements findElements = new FindElements([-1,null,-1,-1,null,-1]);
 * findElements.find(2); // return True
 * findElements.find(3); // return False
 * findElements.find(4); // return False
 * findElements.find(5); // return True
 * <p>
 * 提示：
 * - TreeNode.val == -1
 * - 二叉树的高度不超过 20
 * - 节点的总数在 [1, 10^4] 之间
 * - 调用 find() 的总次数在 [1, 10^4] 之间
 * - 0 <= target <= 10^6
 *
 * @author Ls J
 * @date 2020/7/25 2:54 PM
 */
public class FindElementsInContaminatedBinaryTree1261 {

    /**
     * 执行用时：28 ms，在所有 Java 提交中击败了 95.48% 的用户
     * 内存消耗：43.1 MB，在所有 Java 提交中击败了 100.00% 的用户
     */
    private static class FindElements {

        TreeNode root;

        public FindElements(TreeNode root) {
            root.val = 0;
            this.root = root;
            reduct(root);
        }

        /**
         * 还原二叉树
         *
         * @param root root
         */
        private void reduct(TreeNode root) {
            if (null != root.left) {
                root.left.val = 2 * root.val + 1;
                reduct(root.left);
            }
            if (null != root.right) {
                root.right.val = 2 * root.val + 2;
                reduct(root.right);
            }
        }

        /**
         * 查找
         * 其实不难发现还原之后的二叉树填满之后是一个从 0 开始，依次往下从左到右递增的二叉树
         * <p>
         * 思路如下：
         * 1. 将完全树中的值加1，得到的树就是：[1]  [2  3]  [4  5  6  7]  [8  9..]
         * 2. 转换成二进制就是：[1]  [10  11]  [100  101  110  111]  [1000  1001]
         * 3. 可以看到所有子节点和父节点都有相同的前缀，而当最后一位是0时则走左侧，是1时则走右侧。
         * <p>
         * 时间复杂度：O(logN)
         * 空间复杂度：O(1)
         *
         * @param target target
         * @return true / false
         */
        public boolean find(int target) {
            if (target < 0) {
                return false;
            }

            TreeNode node = root;
            target++;
            // 次高位
            int bit = Integer.highestOneBit(target) >> 1;

            while (bit > 0 && node != null) {
                if ((target & bit) == 0) {
                    node = node.left;
                } else {
                    node = node.right;
                }
                bit >>= 1;
            }

            return node != null;
        }
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
