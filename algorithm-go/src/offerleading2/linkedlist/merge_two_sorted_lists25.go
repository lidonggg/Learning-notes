//
// 面试题 25：合并两个有序链表
//
// 问题描述：
// 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
//
// @author Ls J
// @date 2020-06-06 14:59
//
package main

import (
    . "lib/common"
)

func main() {

}

//
// 一次遍历，添加一个哨兵节点对边界处理进行优化
//
// 执行用时：8 ms，在所有 Go 提交中击败了 82.53% 的用户
// 内存消耗：4.1 MB，在所有 Go 提交中击败了 100.00% 的用户
//
// @param   l1   ListNode    链表一的头节点
// @param   l2   ListNode    链表二的头节点
// @return  ListNode    合并后的头节点
//
func mergeTwoList(l1 *ListNode, l2 *ListNode) *ListNode {
    if nil == l1 {
        return l2
    }
    if nil == l2 {
        return l1
    }

    // 哨兵节点，可以减少边界处理
    head := &ListNode{
        Val: 0,
    }

    tmp := head

    for nil != l1 && nil != l2 {
        if l1.Val <= l2.Val {
            tmp.Next, l1 = l1, l1.Next
        } else {
            tmp.Next, l2 = l2, l2.Next
        }
        tmp = tmp.Next
    }

    if nil != l1 {
        tmp.Next = l1
    }

    if nil != l2 {
        tmp.Next = l2
    }
    // head 是哨兵节点，所以这里要返回它的下一个节点
    return head.Next
}
