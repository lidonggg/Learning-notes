package com.lidong.javaops.algorithm.tree.binarytree;

import java.util.Stack;

/**
 * @author Ls J
 * @date 2019/5/2 11:27 PM
 * 二叉树遍历，包含前后中序遍历的递归和非递归方法
 * 非递归主要使用了堆栈来进行操作
 */
public class TreeTraverse {

    /**
     *         0
     *       /   \
     *      1    2
     *     / \    \
     *    3   4    5
     * @return 根节点
     */
    private static TreeNode init(){
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setRight(node5);

        return root;
    }

    /**
     * 前序递归遍历
     * @param node 当前节点
     */
    private static void preTraverseRecursive(TreeNode node){
        if(null != node){
            System.out.print(node.getData() + " ");
            preTraverseRecursive(node.getLeft());
            preTraverseRecursive(node.getRight());
        }
    }

    /**
     * 前序非递归遍历
     * @param root 根节点
     */
    private static void preTraverseWithoutRecursive(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (node != null || stack.size() > 0) {
            // 将所有左孩子压栈
            // 压栈之前先访问
            if (node != null) {
                System.out.print(node.getData() + " ");
                stack.push(node);
                node = node.getLeft();
            } else {
                node = stack.pop();
                node = node.getRight();
            }
        }
    }

    /**
     * 中序递归遍历
     * @param node 当前节点
     */
    private static void midTraverseRecursive(TreeNode node){
        if(null != node){
            midTraverseRecursive(node.getLeft());
            System.out.print(node.getData() + " ");
            midTraverseRecursive(node.getRight());
        }
    }

    /**
     * 中序非递归遍历
     * @param root 根节点
     */
    private static void midTraverseWithoutRecursive(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (node != null || stack.size() > 0) {
            if (node != null) {
                // 压栈
                stack.push(node);
                node = node.getLeft();
            } else {
                // 出栈并打印、访问
                node = stack.pop();
                System.out.print(node.getData() + " ");
                node = node.getRight();
            }
        }
    }

    /**
     * 后序递归遍历
     * @param node 当前节点
     */
    private static void lastTraverseRecursive(TreeNode node){
        if(null != node){
            lastTraverseRecursive(node.getLeft());
            lastTraverseRecursive(node.getRight());
            System.out.print(node.getData() + " ");
        }
    }

    /**
     * 后序非递归遍历
     * @param root 根节点
     */
    private static void lastTraverseWithoutRecursive(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        // 构造一个中间栈来存储逆后序遍历的结果
        Stack<TreeNode> output = new Stack<>();
        TreeNode node = root;

        while (node != null || stack.size() > 0) {
            if (node != null) {
                output.push(node);
                stack.push(node);
                node = node.getRight();
            } else {
                node = stack.pop();
                node = node.getLeft();
            }
        }
        while (output.size() > 0) {
            System.out.print(output.pop().getData() + " ");
        }
    }

    public static void main(String[] args) {
        System.out.print("前序递归遍历：");
        preTraverseRecursive(init());
        System.out.print("\n前序非递归遍历：");
        preTraverseWithoutRecursive(init());
        System.out.print("\n中序递归遍历：");
        midTraverseRecursive(init());
        System.out.print("\n中序非递归遍历：");
        midTraverseWithoutRecursive(init());
        System.out.print("\n后序递归遍历：");
        lastTraverseRecursive(init());
        System.out.print("\n后序非递归遍历：");
        lastTraverseWithoutRecursive(init());
    }
}
