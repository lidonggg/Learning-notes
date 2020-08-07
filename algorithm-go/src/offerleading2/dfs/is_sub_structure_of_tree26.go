//
// 面试题 26：树的子结构
//
// 问题描述：
// 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
// B是A的子结构， 即 A中有出现和B相同的结构和节点值。
//
// 例如:
// 给定的树 A:
//     3
//    / \
//   4   5
//  / \
// 1   2
// 给定的树 B：
//   4 
//  /
// 1
// 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
//
// 示例 1：
// 输入：A = [1,2,3], B = [3,1]
// 输出：false
//
// 示例 2：
// 输入：A = [3,4,5,1,2], B = [4,1]
// 输出：true
//
// 限制：
// 0 <= 节点个数 <= 10000
//
// @author Ls J
// @date 2020-08-08 00:05
//
package main

import (
    . "lib/common"
)

func isSubStructure(A *BinaryTreeNode, B *BinaryTreeNode) bool {
    return (nil != A && nil != B) && (dfs26(A, B) || isSubStructure(A.Left, B) || isSubStructure(A.Right, B))
}

//
// dfs
//
// @param A nodeA
// @param B nodeB
// @return true / false
//
func dfs26(A *BinaryTreeNode, B *BinaryTreeNode) bool {
    if nil == B {
        return true
    }
    if nil == A || A.Val != B.Val {
        return false
    }

    return dfs26(A.Left, B.Left) && dfs26(A.Right, B.Right)
}
