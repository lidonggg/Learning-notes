//
// 面试题 23：链表中的倒数第 k 个节点
//
// 问题描述：
// 输入一个链表，输出该链表中的倒数第 k 个节点，从 1 开始计数，即最后一个节点是倒数第一个节点
//
// @author Ls J
// @date 2020-06-09 21:25
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

    entrance := getKthFromEnd(head, 1)
    if nil != entrance {
        fmt.Printf("Found! the value of the node is: %d\n", entrance.Val)
    } else {
        fmt.Print("No Found!")
    }
}

//
// 双指针，第一个先走 k 步，然后和第二指针一起走，当第一个指针到达终点的时候，第二个指针指向的位置就是倒数第 k 个元素
//
// 执行用时：0 ms，在所有 Go 提交中击败了 100.00% 的用户
// 内存消耗：2.2 MB，在所有 Go 提交中击败了 100.00% 的用户
//
// @param   head    *ListNode   头节点
// @param   k       int         k
// @return 如果存在，返回第 k  个节点，否则返回 nil
//
func getKthFromEnd(head *ListNode, k int) *ListNode {
    if nil == head {
        return nil
    }

    first := head
    for i := 0; i < k; i++ {
        first = first.Next
        // 注意这里的边界处理，如果 first 走到了最后一个元素，但是还不到 k 步，那么说明链表的长度小于 k
        if nil == first && i < k-1 {
            return first
        }
    }
    second := head
    for nil != first {
        first, second = first.Next, second.Next
    }
    return second
}
