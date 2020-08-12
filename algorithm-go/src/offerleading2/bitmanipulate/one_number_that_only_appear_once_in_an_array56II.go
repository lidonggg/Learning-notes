//
// 面试题 56-II：数组中唯一出现一次的数字
//
// 问题描述：
// 在一个数组中，除了一个数字只出现了一次之外，其他数字都出现了三次，请找到这个只出现了一次的数字。
// 要求时间复杂度为 O(n)，空间复杂度为 O(1)。
//
// 排序或者利用哈希表的方法很简单，这里就不写了
//
// @author Ls J
// @date 2020-06-10 22:47
//
package main

import "fmt"

func main() {
    fmt.Println(findOneNum([]int{2, 3, 3, 3, 4, 4, 4, 5, 5, 5}))
}

//
// 位运算
// 如果一个数字出现了三次，那么它的二进制表示的每一位 0 或者 1 也出现三次。
// 如果把所有出现三次的数字的二进制表示的每一位都分别加起来，那么每一位的和都能被 3 整除。
//
// 我们把数组中所有数字的二进制表示的每一位都加起来，
// 如果某一位的和能被三整除，那么那个只出现一次的数字二进制表示中对应的那一位是 0，否则是 1
//
// @param   arr    []int    输入数组
// @return   一个只出现一次的数字
//
func findOneNum(arr []int) (num int) {
    length := len(arr)
    if length <= 0 {
        return
    }

    bitSum := [32]int{}

    for i := 0; i < length; i++ {
        bitMask := 1
        for j := 31; j >= 0; j-- {
            if (arr[i] & bitMask) != 0 {
                bitSum[j] += 1
            }
            bitMask <<= 1
        }
    }

    num = 0
    for i := 0; i < 32; i++ {
        num <<= 1
        num += bitSum[i] % 3
    }
    return
}
