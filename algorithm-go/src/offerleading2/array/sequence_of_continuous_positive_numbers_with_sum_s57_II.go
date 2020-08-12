//
// 面试题 57-II：和为s的连续正数序列
//
// 问题描述：
// 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
//
// 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
//
// 示例 1：
// 输入：target = 9
// 输出：[[2,3,4],[4,5]]
//
// 示例 2：
// 输入：target = 15
// 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
// 
// 限制：
// 1 <= target <= 10^5
//
// @author Ls J
// @date 8/12/20 9:43 PM
//
package main

import "math"

//
// 根据 N = k(2x+k-1)/2，而 k<2x+k-1，所以我们只有遍历根号 target 的数就行了。
// 而 target - i * (i - 1) / 2 = ki，k 为其中的起点，i 为项数，所以前半部分能被 i 整除的时候就是满足条件的时候，此时就可以计算出 k 值，加入 slice res 就行。
//
func findContinuousSequence(target int) [][]int {
    res := make([][]int, 0)
    for i := int(math.Sqrt(float64(2 * target))); i >= 2; i-- {
        judge := target - i*(i-1)/2
        if judge%i == 0 {
            begin := judge / i
            temp := make([]int, 0)
            for j := 0; j < i; j++ {
                temp = append(temp, begin+j)
            }
            res = append(res, temp)
        }
    }
    return res
}
