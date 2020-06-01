//
// @author Ls J
// @date 2020-05-31 23:48
//
package util

import "fmt"

//
// 交换两个元素的值
//
func Swap(arr []int, a, b int) {
    arr[a], arr[b] = arr[b], arr[a]
}

//
// 顺序输出
//
func Show(arr []int) {
    for _, value := range arr {
        fmt.Printf("%d\t", value)
    }
}
