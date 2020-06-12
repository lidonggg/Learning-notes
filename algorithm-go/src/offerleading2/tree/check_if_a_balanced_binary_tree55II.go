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
// 方法一：利用 DFS
// 存在重复遍历的情况
//
// 执行用时：12 ms，在所有 Go 提交中击败了 56.85% 的用户
// 内存消耗：5.7 MB，在所有 Go 提交中击败了 30.00% 的用户
//
// @param   root    *BinaryTreeNode   根节点
// @return true if balanced
//
func isBalanced1(node *BinaryTreeNode) bool {
    return node == nil || isBalanced1(node.Left) &&
        math.Abs(height(node.Left)-height(node.Right)) < 2 &&
        isBalanced1(node.Right)
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

//
// 方法二
//
// 执行用时：8 ms，在所有 Go 提交中击败了 89.92% 的用户
// 内存消耗：5.7 MB，在所有 Go 提交中击败了 60.00% 的用户
//
// @param   root    *BinaryTreeNode   根节点
// @return true if balanced
//
func isBalanced2(node *BinaryTreeNode) bool {
    depth := 0
    // depth 传递指针，能把结果传递出去
    return isBalanced2Helper(node, &depth)
}

//
// 方法二：后序遍历
// 不存在重复遍历的情况，在遍历每个节点的时候都记录它的深度，从而可以一边遍历一边判断它是不是平衡的
//
// @param   root    *BinaryTreeNode   根节点
// @return true if balanced
//
func isBalanced2Helper(node *BinaryTreeNode, pDepth *int) bool {
    if nil == node {
        *pDepth = 0
        return true
    }

    var left, right int

    if isBalanced2Helper(node.Left, &left) && isBalanced2Helper(node.Right, &right) {
        diff := left - right
        if math.Abs(float64(diff)) < 2 {
            if left > right {
                *pDepth = 1 + left
            } else {
                *pDepth = 1 + right
            }
            return true
        }
    }
    return false
}
