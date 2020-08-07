//
// 面试题 40：最小的 k 个数字
//
// 问题描述：
// 给定一个整数数组和一个整数 k ，返回数组中最小的 k 个数字
//
// TOP K 问题：
// 1. 全排序，取前 k 个元素
// 2. 快速排序
// 3. 大（小）顶堆
//
// @author Ls J
// @date 2020-06-07 13:26
//
package main

import (
    "fmt"
    "sort"
)

func main() {
    arr := []int{3, 2, 1}
    k := 2
    fmt.Print(getLeastNumbers3(arr, k))
}

//
// 方法一：先排序，然后返回前 k 个数字
// 执行用时：52 ms，在所有 Go 提交中击败了 28.21% 的用户
// 内存消耗：6.3 MB，在所有 Go 提交中击败了 100.00% 的用户
//
// 时间复杂度：O(nlogn)
//
// @param   arr   []int   原数组
// @param   k     int     要寻找的数字个数
// @return  k 个最小的数字
//
func getLeastNumbers1(arr []int, k int) []int {
    aLen := len(arr)
    if aLen <= k {
        return arr
    }
    sort.Ints(arr)
    return arr[0:k]
}

//
// 方法二：替换 res 数组中的值
//
// 执行用时：212 ms，在所有 Go 提交中击败了 11.03% 的用户
// 内存消耗：6.3 MB，在所有 Go 提交中击败了 100.00% 的用户
//
// @param   arr   []int   原数组
// @param   k     int     要寻找的数字个数
// @return  k 个最小的数字
//
func getLeastNumbers2(arr []int, k int) []int {
    aLen := len(arr)
    if aLen <= k {
        return arr
    }

    res, idx := make([]int, k), 0
    if k == 0 {
        return res
    }
    for _, v := range arr {
        if idx <= k-1 {
            res[idx] = v
            idx++
        } else {
            maxIdx := findMax(res)
            if res[maxIdx] > v {
                res[maxIdx] = v
            }
        }
    }
    return res
}

func findMax(arr []int) (idx int) {
    max := arr[0]
    idx = 0

    for i := 1; i < len(arr); i++ {
        if max < arr[i] {
            max = arr[i]
            idx = i
        }
    }
    return
}

//
// 方法三：选择排序的思想，选 k 次
//
// 如果 k <= len/2，直接选最小；如果 k > len /2，选（len-k）个最大元素，然后剩下的是所求的结果
//
// 执行用时：212 ms，在所有 Go 提交中击败了 11.03% 的用户
// 内存消耗：6.3 MB，在所有 Go 提交中击败了 100.00% 的用户
//
// @param   arr   []int   原数组
// @param   k     int     要寻找的数字个数
// @return  k 个最小的数字
//
func getLeastNumbers3(arr []int, k int) []int {
    aLen := len(arr)
    if aLen <= k {
        return arr
    }

    idx := 0

    for idx < k {
        min, foundIdx := arr[idx], idx
        for i := idx + 1; i < aLen; i++ {
            if min > arr[i] {
                min, foundIdx = arr[i], i
            }
        }
        arr[idx], arr[foundIdx] = min, arr[idx]
        fmt.Println(arr)
        idx++
    }

    return arr[0:k]
}
