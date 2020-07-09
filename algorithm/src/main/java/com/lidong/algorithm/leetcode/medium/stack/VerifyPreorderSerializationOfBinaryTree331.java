package com.lidong.algorithm.leetcode.medium.stack;

import java.util.Stack;

/**
 * 验证二叉树的前序序列化（中等-331）
 * 中文链接：https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree
 * <p>
 * 问题描述：
 * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
 * <p>     _9_
 * <p>    /   \
 * <p>   3     2
 * <p>  / \   / \
 * <p> 4   1  #  6
 * <p>/ \ / \   / \
 * <p># # # #   # #
 * 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
 * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
 * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
 * 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
 * <p>
 * 示例 1:
 * 输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: "1,#"
 * 输出: false
 * <p>
 * 示例 3:
 * 输入: "9,#,#,1"
 * 输出: false
 *
 * @author ls J
 * @date 2020/7/9 9:44
 */
public class VerifyPreorderSerializationOfBinaryTree331 {

    /**
     * 方法一：栈
     * 其实我们可以发现，每遇到相邻的两个 '#'，则说明遍历到了叶子结点，那么这个时候这两个叶子节点的前一个遍历的节点就是它们的父节点
     * 此时我们就可以将这三个节点看成一个 '#'。
     * <p>
     * 例如对于上述的例子：
     * <p>     _9_
     * <p>    /   \
     * <p>   3     2
     * <p>  / \   / \
     * <p> 4   1  #  6
     * <p>/ \ / \   / \
     * <p># # # #  #  #
     *
     * <p>      |将叶子节点及它们的父节点看成 #
     *
     * <p>     _9_
     * <p>    /   \
     * <p>   3     2
     * <p>  / \   / \
     * <p> #  #  #  #
     *
     * <p>      |
     *
     * <p>     _9_
     * <p>    /   \
     * <p>   #    #
     *
     * <p>      |
     *
     * <p>      #
     * <p>
     * <p>
     * 执行用时：8 ms，在所有 Java 提交中击败了 23.01% 的用户
     * 内存消耗：40.1 MB，在所有 Java 提交中击败了 5.55% 的用户
     *
     * @param preorder preorder
     * @return true / false
     */
    public boolean isValidSerialization(String preorder) {
        Stack<String> stack = new Stack<>();

        String[] chars = preorder.split(",");

        int n = chars.length;
        if (n == 1 && "#".equals(chars[0])) {
            return true;
        }
        for (int i = 0; i < n; ++i) {
            // 如果栈空或者栈顶元素不是 '#'，直接入栈
            if (stack.isEmpty() || !"#".equals(chars[i])) {
                stack.push(chars[i]);
            } else {
                // 一旦遇到相邻的两个 '#'，则连同父节点一起出栈
                // 同时迭代所有栈顶元素，直到栈空或者栈顶不是 '#'
                while (!stack.isEmpty() && "#".equals(stack.peek())) {
                    stack.pop();
                    if (stack.isEmpty()) {
                        return false;
                    }
                    stack.pop();
                }
                if (i != n - 1) {
                    stack.push("#");
                }
            }
        }

        return stack.isEmpty();
    }

    /**
     * 方法二
     * 来自 leetcode 官方题解：https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree/solution/yan-zheng-er-cha-shu-de-qian-xu-xu-lie-hua-by-leet/
     *
     * @param preorder preorder
     * @return trur / false
     */
    public boolean isValidSerialization2(String preorder) {
        // 可用的槽位
        int slots = 1;

        int n = preorder.length();
        for(int i = 0; i < n; ++i) {
            if (preorder.charAt(i) == ',') {
                // 每遇到逗号就更新槽位的数量，占用一个槽位
                --slots;

                // 如果槽位小于 0 了，代表没有更多可用的槽位，直接返回 false
                if (slots < 0) {
                    return false;
                }

                // 如果逗号前不是 '#'，代表不是叶子节点，可用贡献两个槽位
                if (preorder.charAt(i - 1) != '#') {
                    slots += 2;
                }
            }
        }

        // 最后一个节点单独处理，因为最后一个节点之后没有逗号，上面的 for 循环中处理不到
        slots = (preorder.charAt(n - 1) == '#') ? slots - 1 : slots + 1;
        // 所有槽位必须都被用完
        return slots == 0;
    }
}
