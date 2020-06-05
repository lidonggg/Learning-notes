//
// 面试题 27：二叉树的镜像
//
// 问题描述：
// 请完成一个函数，输入一颗二叉树，该函数输出它的镜像
//
// @author Ls J
// @date 2020-06-05 21:39
//
package main

import (
    "fmt"
    . "lib/common"
    . "lib/queue"
)

func main() {
    root := &BinaryTreeNode{
        Val: 0,
    }
    node1 := &BinaryTreeNode{
        Val: 1,
    }
    node2 := &BinaryTreeNode{
        Val: 2,
    }
    node3 := &BinaryTreeNode{
        Val: 3,
    }
    node4 := &BinaryTreeNode{
        Val: 4,
    }
    node5 := &BinaryTreeNode{
        Val: 5,
    }
    root.Left = node1
    root.Right = node2
    node2.Left = node3
    node3.Right = node4
    node3.Left = node5
    root.PrintBinaryTreeInorder()
    fmt.Println()
    dfs27(root)
    root.PrintBinaryTreeInorder()
    fmt.Println()
    bfs27(root)
    root.PrintBinaryTreeInorder()
}

//
// 方法一：dfs
//
// @param   root    *BinaryTreeNode    根结点
//
func dfs27(root *BinaryTreeNode) {
    if nil == root {
        return
    }

    // 交换当前两个子节点
    root.Right, root.Left = root.Left, root.Right
    // 递归右节点
    dfs27(root.Right)
    // 递归左节点
    dfs27(root.Left)
}

//
// 方法二：bfs
//
// @param   root    *BinaryTreeNode    根结点
//
func bfs27(root *BinaryTreeNode) {
    if nil == root {
        return
    }
    queue := new(Queue)
    queue.Init()
    queue.Enqueue(root)

    for queue.Size() > 0 {
        curNode := queue.Dequeue().(*BinaryTreeNode)
        curNode.Left, curNode.Right = curNode.Right, curNode.Left
        if nil != curNode.Left {
            queue.Enqueue(curNode.Left)
        }
        if nil != curNode.Right {
            queue.Enqueue(curNode.Right)
        }
    }
}
