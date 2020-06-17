//
// 面试题 56-I：数组中只出现一次的两个数
//
// 问题描述：
// 一个数组里除了两个数字以外，其他数字都出现了两次，请写一个函数返回这两个只出现了一次的数字。
// 要求时间复杂度为 O(n)，空间复杂度为 O(1)。
//
// @author Ls J
// @date 2020-06-10 21:55
//
package main

import "fmt"

func main() {
    arr := []int{1, 3, 3, 3, 3, 4, 4, 5}
    a, b := findTwoNums(arr)
    fmt.Print(a, b)
}

//
// 位运算
// 性质：任何一个数异或它自己都是 0，任何一个数异或另外一个数都不是 0
//
// @param   arr    []int    输入数组
// @return   两个只出现一次的数字
//
func findTwoNums(arr []int) (a int, b int) {
    length := len(arr)
    if length < 2 {
        return
    }
    resultExclusiveOr := 0

    for i := 0; i < length; i++ {
        resultExclusiveOr ^= arr[i]
    }
    // 找到 1 所在的位置
    idxOf1 := findFirstBitIs1(resultExclusiveOr)

    a, b = 0, 0
    // important：按照 idxOf1 位置是不是 1 来对数组分组，这样两组之中每组都有一个只出现一次的数字
    for i := 0; i < length; i++ {
        if isBit1(arr[i], idxOf1) {
            a ^= arr[i]
        } else {
            b ^= arr[i]
        }
    }
    return
}

//
// 寻找某个数的二进制表示的第一个 1 所在的位置
//
// @param   num    int    num
// @return  idxBit
//
func findFirstBitIs1(num int) (idxBit uint) {
    idxBit = 0
    for ((num & 1) == 0) && (idxBit < 32) {
        num = num >> 1
        idxBit++
    }
    return
}

//
// 判断某一位是不是 1
//
// @param   num   int   num
// @return  true if 1
//
func isBit1(num int, idxBit uint) bool {
    num = num >> idxBit
    return (num & 1) == 1
}
