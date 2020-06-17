//
// 面试题 35：复杂链表的复制
//
// 问题描述：
// 实现一个函数，复制一个复杂链表。
// 在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 sibling 指针指向链表中的任意节点或者 nil。
//
// @author Ls J
// @date 2020-06-15 17:53
//
package main

type complexListNode struct {
    val int

    next *complexListNode

    sibling *complexListNode
}

//
// 执行用时：0 ms，在所有 Go 提交中击败了 100.00% 的用户
// 内存消耗：3.3 MB，在所有 Go 提交中击败了 100.00% 的用户
//
// @param   head    node    头节点
// @param   新链表头节点
//
func cloneNodes(head *complexListNode) *complexListNode {

    if nil == head {
        return nil
    }

    tmp := head
    // 第一步：先把复制节点接在被复制节点的后面
    for nil != tmp {
        next := tmp.next
        newNode := &complexListNode{
            tmp.val,
            next,
            nil,
        }

        tmp.next = newNode
        tmp = next
    }

    tmp = head
    // 第二步：新节点 sibling 赋值
    for nil != tmp {
        if nil != tmp.sibling {
            // tmp.sibling.next 刚好指向 tmp.sibling 的复制节点
            tmp.next.sibling = tmp.sibling.next
        }
        tmp = tmp.next.next
    }

    tmp = head
    // 哨兵节点
    hair := &complexListNode{
        -1,
        nil,
        nil,
    }
    cur := hair

    for nil != tmp {
        // 拼接新链表
        cur.next = tmp.next
        cur = cur.next
        // 还原原链表
        tmp.next = cur.next
        tmp = tmp.next
    }

    return hair.next
}
