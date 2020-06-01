//
// 排序算法之快速排序
//
// @author Ls J
// @date 2020-05-31 23:33
//
package main

import (
    "fmt"
    "lib/util"
)

func main() {
    var arr = []int{41, 24, 76, 11, 45, 64, 21, 69, 19, 36}

    fmt.Println("before sort：")
    util.Show(arr)

    quickSort(arr, 0, len(arr)-1)

    fmt.Println("\nafter sort:")
    util.Show(arr)
}

func quickSort(arr []int, left, right int) {
    if left < right {
        pos := partition(arr, left, right)
        partition(arr, left, pos-1)
        partition(arr, pos+1, right)
    }
}

func partition(arr []int, left, right int) int {
    key := arr[right]
    i := left - 1

    for j := left; j < right; j++ {
        if arr[j] <= key {
            i++
            util.Swap(arr, i, j)
        }
    }

    util.Swap(arr, i+1, right)

    return i + 1
}
