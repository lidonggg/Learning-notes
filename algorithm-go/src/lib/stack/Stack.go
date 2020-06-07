//
// stack
//
// @author Ls J
// @date 2020-06-07 11:46
//
package stack

type Stack []interface{}

//
// 入栈操作
//
func (s *Stack) Push(value interface{}) {
    *s = append(*s, value)
}

//
// 出栈操作
//
func (s *Stack) Pop() interface{} {
    n := len(*s)
    res := (*s)[n-1]
    *s = (*s)[:n-1]
    return res
}

//
// 栈中元素个数
//
func (s *Stack) Size() int {
    return len(*s)
}

//
// 查看栈顶元素
//
func (s *Stack) Peek() interface{} {
    n := len(*s)
    if n == 0 {
        return nil
    }
    return (*s)[n-1]
}
