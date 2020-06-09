//
// 面试题 23：链表中环的入口
//
// 问题描述：
// 如果一个链表中包含环，返回环的入口节点
//
// @author Ls J
// @date 2020-06-08 22:33
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
    l3.Next = l1

    entrance := findEntrance2(head)
    if nil != entrance {
        fmt.Printf("Found! the value of the node is: %d\n", entrance.Val)
    }
}

//
// 方法一：双指针
// 如果单链表有环，按照判断是否有环的思路，当快指针和慢指针相遇时，slow 指针肯定没有遍历完链表，而fast指针已经在环内循环了 n 圈。
// 假设 slow 指针走了 s 步，则 fast 指针走了 2*s 步，设环长为 r，则：
// 2 * s = s + n * r;
// s = n * r;
// 设链表头到环入口距离为l，入口处距离相遇点距离为 a，则：
// s = l + a + m * r;
// 得：l = (n - m) * r - a
// 所以，相遇后，如果在链表头和相遇点各设置一个指针，每次走一步，两指针必定相遇，且在相遇第一个点为环入口
//
// @param   head    *ListNode   头节点
// @return 入口节点，如果不存在环，返回 false
//
func findEntrance1(head *ListNode) *ListNode {
    meetPoint := finMeetPoint(head)

    // 环不存在
    if nil == meetPoint || nil == meetPoint.Next {
        return nil
    }

    p1 := head
    for p1 != meetPoint {
        p1 = p1.Next
        meetPoint = meetPoint.Next
    }

    return meetPoint
}

//
// 方法二：双指针，书中的方法
// 根据环的长度来计算
//
// @param   head    *ListNode   头节点
// @return 入口节点，如果不存在环，返回 false
//
func findEntrance2(head *ListNode) *ListNode {
    meetPoint := finMeetPoint(head)

    // 环不存在
    if nil == meetPoint || nil == meetPoint.Next {
        return nil
    }

    // 计算环中节点个数
    nodesInCircle := 1
    p1 := meetPoint
    for meetPoint != p1.Next {
        p1 = p1.Next
        nodesInCircle++
    }

    // p1 从头节点开始先移动 nodesInCircle 次
    p1 = head
    for i := 0; i < nodesInCircle; i++ {
        p1 = p1.Next
    }

    // 假设链表有 n 个节点，那么环之前有 (n - nodesInCircle) 个节点
    // 由于 p1 先走了 nodesInCircle 步，所以接下来再走 (n - nodesInCircle) 步就刚好到达环入口
    // 两个节点同步移动，当第二个指针指向环的入口的时候，p1 刚好走完一圈，到达环入口
    p2 := head
    // 这里刚好会经过 (n - nodesInCircle) 步
    for p1 != p2 {
        p1, p2 = p1.Next, p2.Next
    }

    return p1
}

//
// 方法二：缓存已遍历过的节点，这样只需要遍历一次链表即可
//
// @param   head    *ListNode   头节点
// @return 入口节点，如果不存在环，返回 false
//
func findEntrance3(head *ListNode) *ListNode {
    if nil == head || nil == head.Next {
        return nil
    }

    nodeSet := map[*ListNode]bool{}
    for nil != head {
        if nodeSet[head] {
            return head
        }
        nodeSet[head] = true
        head = head.Next
    }
    return nil
}

func finMeetPoint(head *ListNode) *ListNode {
    if nil == head || nil == head.Next {
        return nil
    }

    slow, fast := head, head
    for nil != fast && nil != fast.Next {
        slow, fast = slow.Next, fast.Next.Next
        if fast == slow {
            return fast
        }
    }
    return nil
}
