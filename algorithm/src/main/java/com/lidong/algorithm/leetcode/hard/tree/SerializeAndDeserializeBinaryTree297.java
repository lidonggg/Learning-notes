package com.lidong.algorithm.leetcode.hard.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的序列化与反序列化（中等-297）
 * 中文链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 * <p>
 * 问题描述：
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 示例: 
 * 你可以将以下二叉树：
 * <p>    1
 * <p>   / \
 * <p>  2   3
 * <p>     / \
 * <p>    4   5
 * 序列化为 "[1,2,3,null,null,4,5]"
 * <p>
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 *
 * @author Ls J
 * @date 2020/7/16 11:30 PM
 */
public class SerializeAndDeserializeBinaryTree297 {

    /**
     * 想法：先序遍历，遇到 null 则序列化成 #
     * <p>
     * 执行用时：106 ms，在所有 Java 提交中击败了 31.35% 的用户
     * 内存消耗：42.9 MB，在所有 Java 提交中击败了 21.43% 的用户
     *
     * @param root root
     * @param str  str
     * @return str
     */
    public String serializeHelper(TreeNode root, String str) {
        if (root == null) {
            str += "#,";
        } else {
            str += root.val + ",";
            str = serializeHelper(root.left, str);
            str = serializeHelper(root.right, str);
        }
        return str;
    }

    public String serialize(TreeNode root) {
        return serializeHelper(root, "");
    }

    /**
     * decode
     *
     * @param data data
     * @return root
     */
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        List<String> list = new LinkedList<>(Arrays.asList(arr));
        return deserializeHelper(list);
    }

    private TreeNode deserializeHelper(List<String> list) {
        if ("#".equals(list.get(0))) {
            list.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        root.left = deserializeHelper(list);
        root.right = deserializeHelper(list);
        return root;
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
