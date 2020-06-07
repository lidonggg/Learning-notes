//
// 面试题31：栈的压入、弹出序列
//
// 问题描述：
// 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
// 假设压入栈的所有数字均不相等。
// 例如：
// 序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
//
//
// @author Ls J
// @date 2020-06-07 11:40
//
package main

import (
    "fmt"
    . "lib/stack"
)

func main() {
    pushed := []int{1, 2, 3, 4, 5}
    poped := []int{4, 5, 3, 2, 1}
    fmt.Print(validateStackSequences(pushed, poped))
}

//
// 模拟入栈出栈操作
//
// 执行用时：8 ms，在所有 Go 提交中击败了 84.19% 的用户
// 内存消耗：4.2 MB，在所有 Go 提交中击败了 100.00% 的用户
//
// @param   pushede   []int   入栈顺序
// @param   poped     []int   出栈顺序
// @return  true if success
//
func validateStackSequences(pushed []int, popped []int) bool {
    len1, len2 := len(pushed), len(popped)
    if len1 == 0 && len2 == 0 {
        return true
    }
    if len1 != len2 {
        return false
    }
    popIdx := 0
    stack := new(Stack)
    stack.Push(pushed[0])

    for _, v := range pushed {
        // 入栈
        stack.Push(v)
        // 依次判断当前栈顶元素是否等于当前应该出栈的元素
        // 如果相等，当前元素出栈，popped 往后移一位
        // 直到栈顶元素与当前要出栈元素不想等为止
        for popIdx < len2 && stack.Peek() == popped[popIdx] {
            stack.Pop()
            popIdx++
        }
    }

    return popIdx == len2
}
