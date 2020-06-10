//
// 面试题 55-II：平衡二叉树
//
// 问题描述：
// 给定一棵二叉树，判断它是不是平衡二叉树
//
// @author Ls J
// @date 2020-05-30 13:22
//
package main

import (
    . "lib/common"
    "math"
)

//
// 利用 DFS
//
// @param   root    *BinaryTreeNode   根节点
// @return true if balanced
//
func isBalanced(node *BinaryTreeNode) bool {
    return node == nil || isBalanced(node.Left) &&
        math.Abs(height(node.Left)-height(node.Right)) < 2 &&
        isBalanced(node.Right)
}

//
// DFS
//
// @param   root    *BinaryTreeNode   根节点
// @return height
//
func height(node *BinaryTreeNode) float64 {
    if node == nil {
        return 0
    }
    return math.Max(height(node.Left), height(node.Right)) + 1
}
