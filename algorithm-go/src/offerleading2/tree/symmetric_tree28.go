//
// 面试题 28：对称的二叉树
//
// 问题描述：
// 请实现一个函数，判断一颗二叉树是不是对称的。
// 如果一个二叉树和它的镜像一样，那么它是对称的。
//
// @author Ls J
// @date 2020-06-06 16:34
//
package main

import (
    . "lib/common"
    . "lib/queue"
)

//
// 方法一：DFS
//
// @param   root    BinaryTreeNode  根节点
// @return bool 如果是对称的，那么返回 true
//
func isSymmetric1(root *BinaryTreeNode) bool {
    if nil == root {
        return true
    }
    return dbfs28(root.Left, root.Right)
}

//
// DFS
//
// 执行用时：0 ms，在所有 Go 提交中击败了 100.00% 的用户
// 内存消耗：2.9 MB，在所有 Go 提交中击败了 100.00% 的用户
//
// @param   left    BinaryTreeNode  左侧节点
// @param   right   BinaryTreeNode  右侧节点
// @return bool 如果是对称的，那么返回 true
//
func dbfs28(left *BinaryTreeNode, right *BinaryTreeNode) bool {
    if nil == left && nil == right {
        return true
    }
    if nil == left || nil == right {
        return false
    }

    if left.Val != right.Val {
        return false
    }

    return dbfs28(left.Left, right.Right) && dbfs28(left.Right, right.Left)
}

//
// 方法二：BFS
//
// @param   root    BinaryTreeNode  根节点
// @return bool 如果是对称的，那么返回 true
//
func isSymmetric2(root *BinaryTreeNode) bool {
    return bfs28(root)
}

//
// BFS
//
// @param   root    BinaryTreeNode  根节点
// @return bool 如果是对称的，那么返回 true
//
func bfs28(root *BinaryTreeNode) bool {
    if nil == root {
        return true
    }

    queue := new(Queue)
    queue.Init()
    queue.Enqueue(root.Left)
    queue.Enqueue(root.Right)

    for queue.Size() > 0 {
        left := queue.Dequeue().(*BinaryTreeNode)
        right := queue.Dequeue().(*BinaryTreeNode)
        if nil == left && nil == left {
            continue
        }
        if nil == left || nil == right {
            return false
        }
        if left.Val != right.Val {
            return false
        }
        queue.Enqueue(left.Left)
        queue.Enqueue(right.Right)
        queue.Enqueue(left.Right)
        queue.Enqueue(right.Left)
    }

    return true
}
