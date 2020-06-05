//
// 二叉树节点
//
// @author Ls J
// @date 2020-05-30 13:40
//
package common

import (
    "fmt"
    "strconv"
)

type BinaryTreeNode struct {

    Val int

    Left *BinaryTreeNode

    Right *BinaryTreeNode
}

//
// 中序打印
//
func (root *BinaryTreeNode) PrintBinaryTreeInorder() {
    if nil == root {
        return
    }

    root.Left.PrintBinaryTreeInorder()
    fmt.Print(strconv.Itoa(root.Val) + " ")
    root.Right.PrintBinaryTreeInorder()
}