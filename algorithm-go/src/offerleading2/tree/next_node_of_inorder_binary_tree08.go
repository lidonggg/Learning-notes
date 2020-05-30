//
// 面试题 08：二叉树中序遍历的下一个节点
//
// 问题描述：
// 给定一个二叉树和其中的一个节点，返回其中序遍历的下一个节点。
// 树中的节点除了有两个分别指向左右子节点的指针，还有一个指向父节点的指针
//
// @author Ls J
// @date 2020-05-30 15:37
//
package main

import "fmt"

type TreeNode struct {
    Val int

    Left *TreeNode

    Right *TreeNode

    Parent *TreeNode
}

func main() {
    root := &TreeNode{
        Val: 0,
    }
    node1 := &TreeNode{
        Val: 1,
    }
    node2 := &TreeNode{
        Val: 2,
    }
    node3 := &TreeNode{
        Val: 3,
    }
    root.Left = node1
    node1.Parent = root

    root.Right = node2
    node2.Parent = root

    node2.Left = node3
    node3.Parent = node2

    next := getNextNode(node3)
    if nil != next {
        fmt.Print(next.Val)
    } else {
        fmt.Print("nil")
    }
}

//
// 考察中序遍历，最好画图来判断一下不同的情况
//
func getNextNode(node *TreeNode) *TreeNode {
    if nil == node {
        return nil
    }
    var nextNode *TreeNode = nil
    // 如果该节点有右子树，那么它的下一个节点就是该右子树的最左节点
    if nil != node.Right {
        nextNode = node.Right
        for nil != nextNode.Right {
            nextNode = nextNode.Left
        }
    } else if nil != node.Parent { // 如果该节点没有右子树，并且有父节点
        parent := node.Parent
        current := node
        // 如果该节点是它父节点的右子节点，那么需要沿着指向父节点的指针一直往上遍历，直到找到第一个是他父节点的左节点的节点
        // 如果这个节点存在，那么它的父节点就是我们要找的下一个节点
        for nil != parent && current == parent.Right {
            current = parent
            parent = parent.Parent
        }
        // 如果该节点是它父节点的左子节点，那么下一个节点就是该父节点
        nextNode = parent
    }

    return nextNode
}
