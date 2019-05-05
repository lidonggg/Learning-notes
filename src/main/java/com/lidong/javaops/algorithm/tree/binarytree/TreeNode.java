package com.lidong.javaops.algorithm.tree.binarytree;

/**
 * @author Ls J
 * @date 2019/5/2 11:19 PM
 * 二叉树节点
 */
class TreeNode {

    private int data;

    private TreeNode left;

    private TreeNode right;

    public TreeNode(){}

    public TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
