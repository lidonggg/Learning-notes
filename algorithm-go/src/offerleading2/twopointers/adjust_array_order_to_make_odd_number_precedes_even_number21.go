//
// 面试题 21：调整数组顺序使奇数位于偶数前面
//
// 问题描述：
// 输入一个数组，实现一个函数来调整该数组中数字的顺序，
// 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分
//
// @author Ls J
// @date 2020-06-07 21:47
//
package main

import "fmt"

func main() {
    arr := []int{1, 2, 3, 4, 5, 8, 9, 1, 6, 7, 3, 5}
    fmt.Print(adjust1(arr))
}

//
// 方法一：双指针，从前往后移动
// 定义两个指针，一个指向数组中的第一个偶数，一个指向数组中的第一个奇数
// 如果这个偶数在这个奇数前面，那么交换两个元素
//
// 执行用时：16 ms，在所有 Go 提交中击败了 99.68% 的用户
// 内存消耗：6.3 MB，在所有 Go 提交中击败了 100.00% 的用户
//
// @param   arr    []int    输入数组
// @return  []int   调整后的数组
//
func adjust1(arr []int) []int {
    length := len(arr)
    if length <= 1 {
        return arr
    }

    evenIdx, oddIdx := 0, 0

    for oddIdx < length && evenIdx < length {
        // 寻找下一个奇数
        for oddIdx < length && isEven(arr[oddIdx]) {
            oddIdx++
        }
        // 寻找下一个偶数
        for evenIdx < length && !isEven(arr[evenIdx]) {
            evenIdx++
        }
        if oddIdx > evenIdx && oddIdx < length {
            arr[oddIdx], arr[evenIdx] = arr[evenIdx], arr[oddIdx]
            // 交换之后，原来的奇数变成了偶数，偶数变成了奇数，因此这里奇偶指针都往后移一位
            oddIdx++
            evenIdx++
        } else {
            // 防止 奇数指针在原地呆住
            oddIdx++
        }

    }

    return arr
}

//
// 方法二：双指针，一前一后开始
//
// 执行用时：28 ms，在所有 Go 提交中击败了 60.58% 的用户
// 内存消耗：6.3 MB，在所有 Go 提交中击败了 100.00% 的用户
//
// @param   arr    []int    输入数组
// @return  []int   调整后的数组
//
func adjust2(arr []int) []int {
    length := len(arr)
    if length <= 1 {
        return arr
    }

    l := 0
    r := len(arr) - 1
    for l < r {
        // 如果左边的数是偶数，同时右边的数是奇数，那么交换两个数
        if isEven(arr[l]) && !isEven(arr[r]) {
            arr[l], arr[r] = arr[r], arr[l]
        }
        // 寻找下一个偶数
        for l < len(arr) && !isEven(arr[r]) {
            l++
        }
        // 寻找下一个奇数
        for r >= 0 && isEven(arr[l]) {
            r--
        }

    }
    return arr
}

//
// 判断标准，这里可修改成其他标准
//
// @param   num    int    当前数字
// @return  如果符合标准，返回true
//
func isEven(num int) bool {
    return (num & 1) == 0
}
