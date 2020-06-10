//
// 面试题 55-I：二叉树深度
//
// 问题描述：
// 给定一棵二叉树，返回其深度
//
// @author Ls J
// @date 2020-06-10 21:34
//
package main

import (
    "fmt"
    . "lib/common"
    . "lib/queue"
    "math"
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

    fmt.Print(maxDepth(root))
}

//
// 利用 DFS
//
// 执行用时：4 ms，在所有 Go 提交中击败了 92.08% 的用户
// 内存消耗：4.4 MB，在所有 Go 提交中击败了 100.00% 的用户
//
// @param   root    *BinaryTreeNode     根结点
// @return  max depth
//
func maxDepth(root *BinaryTreeNode) int {
    //return int(dfs55I(root))
    return bfs55I(root)
}

//
// DFS
//
// @param   root    *BinaryTreeNode     根结点
// @return  cur max depth
//
func dfs55I(root *BinaryTreeNode) float64 {
    if nil == root {
        return 0
    }

    return math.Max(dfs55I(root.Left), dfs55I(root.Right)) + 1
}

//
// BFS
//
// @param   root    *BinaryTreeNode     根结点
// @return  cur max depth
//
func bfs55I(root *BinaryTreeNode) int {
    if nil == root {
        return 0
    }

    queue := new(Queue)
    queue.Init()
    queue.Enqueue(root)
    res := 0

    for queue.Size() > 0 {
        size := queue.Size()
        for i := 0; i < int(size); i++ {
            curNode := queue.Dequeue().(*BinaryTreeNode)
            if nil != curNode.Right {
                queue.Enqueue(curNode.Right)
            }
            if nil != curNode.Left {
                queue.Enqueue(curNode.Left)
            }
        }
        res++
    }

    return res
}
