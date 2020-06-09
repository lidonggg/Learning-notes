//
// 面试题 24：反转链表
//
// 问题描述：
// 定义一个函数，接收一个链表的头节点，反转该链表，并返回反转后的头节点
//
// @author Ls J
// @date 2020-06-09 21:46
//
package main

import (
    "fmt"
    . "lib/common"
)

func main() {
    head := &ListNode{
        Val: 1,
    }
    l1 := &ListNode{
        Val: 2,
    }
    l2 := &ListNode{
        Val: 3,
    }
    l3 := &ListNode{
        Val: 4,
    }
    head.Next = l1
    l1.Next = l2
    l2.Next = l3

    entrance := reverseListByRecurse(head)
    if nil != entrance {
        fmt.Printf("Found! the value of the head node is: %d\n", entrance.Val)
    } else {
        fmt.Print("No Found!")
    }
}

//
// 方法一：迭代
//
// 执行用时：0 ms，在所有 Go 提交中击败了 100.00% 的用户
// 内存消耗：2.5 MB，在所有 Go 提交中击败了 100.00% 的用户
//
// @param   head    *ListNode   头节点
// @return  新的头节点
//
func reverseList(head *ListNode) *ListNode {
    if nil == head || nil == head.Next {
        return head
    }
    var newHead, next *ListNode

    for head != nil {
        next = head.Next
        head.Next = newHead
        newHead = head
        head = next
        // 可以简化成下面这个样子
        //head.Next,newHead,head = newHead,head,head.Next
    }

    return newHead
}

//
// 方法二：递归
//
// 执行用时：4 ms，在所有 Go 提交中击败了 15.46% 的用户
// 内存消耗：3 MB，在所有 Go 提交中击败了 8.70% 的用户
//
// @param   head    *ListNode   头节点
// @return  新的头节点
//
func reverseListByRecurse(head *ListNode) *ListNode {
    if nil == head || nil == head.Next {
        return head
    }
    next := head.Next
    head.Next = nil
    reserveRest := reverseListByRecurse(next)
    next.Next = head
    return reserveRest
}
