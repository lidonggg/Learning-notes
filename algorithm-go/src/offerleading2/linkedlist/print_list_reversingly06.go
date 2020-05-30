//
// 面试题 6：从尾到头打印链表
//
// 问题描述：
// 输入一个链表的头节点，从尾到头打印链表每个节点的值，要求不改变链表的结构。
//
// @author Ls J
// @date 2020-05-30 11:39
//
package main

import (
    "container/list"
    "fmt"

    "lib/common"
)

func main() {
    head := &common.ListNode{
        Val:  0,
        Next: nil,
    }
    node1 := &common.ListNode{
        Val:  1,
        Next: nil,
    }
    node2 := &common.ListNode{
        Val:  3,
        Next: nil,
    }
    head.Next = node1
    node1.Next = node2

    printListReversinglyIteratively(head)
    fmt.Print("\n")
    printListReversinglyRecursively(head)
}

//
// 利用栈 FIFO 的特性，从头到尾遍历链表，将值入栈，然后依次出栈
// 如果链表过长，使用此方法可能会导致调用栈溢出
//
func printListReversinglyIteratively(head *common.ListNode) {
    if nil == head {
        return
    }

    var stack = list.New()
    for nil != head {
        stack.PushFront(head.Val)
        head = head.Next
    }

    for val := stack.Front(); nil != val; val = val.Next() {
        fmt.Printf("%d\t", val.Value)
    }
}

//
// 递归解法：每访问到一个链表节点的时候，先递归输出它后面的节点，再输出它本身
//
func printListReversinglyRecursively(head *common.ListNode) {
    if nil != head {
        if nil != head.Next {
            printListReversinglyRecursively(head.Next)
        }
        fmt.Printf("%d\t", head.Val)
    }
}
