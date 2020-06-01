//
// 面试题 09：用两个栈实现队列
//
// 问题描述：
// 用两个栈实现队列
//
// @author Ls J
// @date 2020-05-30 18:02
//
package main

import "fmt"

type stack []int

type CQueue struct {
    in  stack
    out stack
}

//
// 入栈操作
//
func (s *stack) Push(value int) {
    *s = append(*s, value)
}

//
// 出栈操作
//
func (s *stack) Pop() int {
    n := len(*s)
    res := (*s)[n-1]
    *s = (*s)[:n-1]
    return res
}

func main()  {
    queue := Constructor()
    queue.AppendTail(1)
    queue.AppendTail(2)
    fmt.Printf("%d\t",queue.DeleteHead())
    fmt.Printf("%d\t",queue.DeleteHead())
}

func Constructor() CQueue {
    return CQueue{}
}

//
// 队尾追加
//
func (queue *CQueue) AppendTail(value int) {
    queue.in.Push(value)
}

//
// 队头删除
//
func (queue *CQueue) DeleteHead() int {
    // 如果出队栈有元素，那么直接让栈顶元素出栈
    if len(queue.out) != 0 {
        return queue.out.Pop()
    } else if len(queue.in) != 0 {
        // 否则先通过入队栈构造出队栈，然后让出队栈顶元素出栈
        for len(queue.in) != 0 {
            queue.out.Push(queue.in.Pop())
        }
        return queue.out.Pop()
    }

    return -1
}
