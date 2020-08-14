//
// 面试题 53-I 在排序数组中查找数字 I
//
// 问题描述：
//
//
// @author Ls J
// @date 8/14/20 8:27 PM
//
package main

//
// 用二分查找法分别查找出现的第一个位置和最后一个位置
//
// 执行用时：8 ms，在所有 Go 提交中击败了 91.75% 的用户
// 内存消耗：4.1 MB，在所有 Go 提交中击败了 100.00% 的用户
//
// @param  nums    []int  nums
// @param  target  int    target
// @return count
//
func search(nums []int, target int) int {
    n := len(nums)
    if n == 0 {
        return 0
    }

    l, r := 0, n - 1
    // 查找右边界
    for l <= r {
        mid := l + ((r - l) >> 1)

        if nums[mid] <= target {
            l = mid + 1
        } else {
            r = mid - 1
        }
    }

    if r >= 0 && nums[r] != target {
        return 0
    }

    ls := l
    l = 0
    // 查找左边界
    for l <= r {
        mid := l + ((r - l) >> 1)

        if nums[mid] < target {
            l = mid + 1
        } else {
            r = mid - 1
        }
    }

    return ls - r - 1
}
