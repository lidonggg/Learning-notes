//
// 面试题 11：旋转数组的最小数字
//
// 问题描述：
// 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
// 输入一个递增排序数组的一个旋转，输出旋转数组的最小元素。
//
// 顺序查找的话比较简单，每次都比较当前元素与前一个元素的大小，如果当前元素比前一个小，那么就说明它就是数组的最小元素。这里就不给出具体的实现。
// 这里给出二分查找的算法
//
// @author Ls J
// @date 2020-05-31 23:59
//
package main

import "fmt"

func main() {
    arr := []int{1, 1, 1, 0, 1}
    fmt.Print(findMinNum(arr))
}

//
// 利用二分查找的思想，每次都将区间缩小为原来的一半
// 有序数组分成了左右2个小的有序数组，而实际上要找的是右边有序数组的最小值（右边有序数组的第一个元素）
//
// 时间复杂度：O(logN)
//
// @param numbers []int 数组
// @return min number
//
func findMinNum(numbers []int) int {
    left := 0
    right := len(numbers) - 1
    for left < right {
        mid := left + (right-left)>>1
        // 如果中间值大于右边的最大值，说明中间值还在左边的小数组里，需要left向右移动
        if numbers[mid] > numbers[right] {
            left = mid + 1
        } else if numbers[mid] <= numbers[right] {
            // 如果中间值小于等于当前右边最大值，至少说明了当前右边的 right 值不是最小值了或者不是唯一的最小值，需要慢慢向左移动一位
            right = right - 1
        }
        fmt.Printf("left: %d,right: %d", left, right)
    }
    // 返回最左侧元素
    return numbers[left]
}
