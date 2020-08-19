//
// 回文链表（简单-面试题 02.06）
// 中文链接：https://leetcode-cn.com/problems/palindrome-linked-list-lcci
//
// 问题描述：
// 编写一个函数，检查输入的链表是否是回文的。
//
// 示例 1：
// 输入： 1->2
// 输出： false
//
// 示例 2：
// 输入： 1->2->2->1
// 输出： true
//
// 进阶：
// 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
//
// @author Ls J
// @date 8/19/20 11:38 PM
//
package linkedlist

import (
    . "lib/common"
)

//
// 只考虑进阶方法
//
// 执行用时：12 ms，在所有 Go 提交中击败了 97.62% 的用户
// 内存消耗：6 MB，在所有 Go 提交中击败了 97.30% 的用户
//
func isPalindrome(head *ListNode) bool {
    if nil == head {
        return true
    }
    midNode := findMidNode(head)
    // 翻转后半部分链表节点
    currTail := revertLinkedNode(midNode)
    // 判断头节点和尾节点值是否一直
    for nil != head && nil != currTail {
        if head.Val != currTail.Val {
            return false
        }
        head = head.Next
        currTail = currTail.Next
    }
    return true
}

//
// 寻找中间节点
//
func findMidNode(head *ListNode) *ListNode {
    slow := head
    fast := head.Next
    for fast != nil && fast.Next != nil {
        slow = slow.Next
        fast = fast.Next.Next
    }
    return slow
}

//
// 翻转链表
//
func revertLinkedNode(head *ListNode) *ListNode {
    if nil == head {
        return head
    }
    var pre *ListNode

    for nil != head {
        head.Next, pre, head = pre, head, head.Next
    }

    return pre
}
