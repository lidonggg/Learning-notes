//
// 面试题 07：根据前序和中序遍历的结果重建二叉树
//
// 问题描述：
// 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
// 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
//
// @author Ls J
// @date 2020-05-30 13:22
//
package main

import (
    . "lib/common"
)

func main() {
    buildTree([]int{1, 2, 3}, []int{2, 1, 3})
}

//
// 递归
// 假设两个序列如下：
// 前序：1 2 4 7 3 5 6 8
// 中序：4 7 2 1 5 3 8 6
// 前序遍历序列的第一个值肯定是根结点，通过它我们可以在中序遍历序列中找到它的左右子树（对应该元素的左右侧子序列）
// 然后我们就可以以同样的办法去构造左右两个子树
//
func buildTree(preorder []int, inorder []int) *BinaryTreeNode {
    if len(preorder) == 0 {
        return nil
    }

    rootIdx := 0
    for i := range inorder {
        if inorder[i] == preorder[0] {
            rootIdx = i
            break
        }
    }
    root := &BinaryTreeNode{Val: preorder[0]}
    // 左子树对应前序序列中的第 1～rootIdx+1 项，其中第一项是左子树的树根
    // 对应中序序列中的第 0～rootIdx 项
    // 然后构造左子树
    root.Left = buildTree(preorder[1:rootIdx+1], inorder[:rootIdx])
    // 左子树对应前序序列中的第 rootIdx+1～last 项，其中第一项是右子树的树根
    // 对应中序序列中的第 rootIdx+1～last 项
    // 然后构造右子树
    root.Right = buildTree(preorder[rootIdx+1:], inorder[rootIdx+1:])
    return root
}
