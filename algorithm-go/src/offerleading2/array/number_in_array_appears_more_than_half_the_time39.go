//
// 面试题 39：数组中出现次数超过一半的数字
//
// 问题描述：
// 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
//
// 示例 1:
// 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
// 输出: 2
//
// 限制：
// 1 <= 数组长度 <= 50000
//
// @author Ls J
// @date 2020-08-07 22:10
//
package main

func majorityElement(nums []int) int {
    res, cnt := 0, 0
    for _, num := range nums {
        if cnt == 0 {
            res = num
        }
        if res == num {
            cnt++
        } else {
            cnt--
        }

    }
    return res
}
