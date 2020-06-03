//
// 面试题 15：二进制中 1 的个数
//
// 问题描述：
// 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
//
//
// @author Ls J
// @date 2020-06-03 22:12
//
package main

import "fmt"

func main() {
    fmt.Println(countNumOfOne1(9))
    fmt.Println(countNumOfOne2(9))
}

//
// 方法一
// 从 n 的最低位开始，每一位进行比较
//
// @param  n  int  n
// @return count of 1
//
func countNumOfOne1(n uint32) int {
    count := 0
    fmt.Printf("n:%b\n", n)
    var flag uint32 = 1
    for flag > 0 {
        // 1010 & 0001 = 0 = 0
        // 1010 & 0010 = 0010
        // 1010 & 0100 = 0100
        // 1010 & 1000 = 1000
        if n&flag == flag {
            count++
        }
        flag = flag << 1
        fmt.Printf("%b\n", flag)
    }
    return count
}

//
// 方法二
// 把一个整数减去 1 ，再和原整数做与运算，会把该整数最右边的 1 变成 0
//
// @param  n  int  n
// @return count of 1
//
func countNumOfOne2(n int) int {
    count := 0
    for n != 0 {
        count++
        n &= n - 1
    }
    return count
}
