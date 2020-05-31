//
// 面试题 10：斐波那契数列
//
// 问题描述：
// 求斐波那契数列的第 n 项
//
// @author Ls J
// @date 2020-05-31 19:36
//
package main

import "fmt"

func main() {
    fmt.Println(fibonacciRecurse(5))
    fmt.Println(fibonacciDynamic(5))
}

//
// 动态规划实现
//
func fibonacciDynamic(n int) int {
    if n <= 0 {
        return 0
    }
    if n == 1 {
        return 1
    }
    a := 1
    b := 0
    fibN := 0
    for i := 2; i <= n; i++ {
        fibN = a + b
        b, a = a, fibN
    }
    return fibN
}

//
// 递归实现
//
func fibonacciRecurse(n int) int {
    if n <= 0 {
        return 0
    }
    if n == 1 {
        return 1
    }
    return fibonacciRecurse(n-1) + fibonacciRecurse(n-2)
}
