//
// 面试题 49：丑数
//
// 问题描述：
// 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
//
// 示例:
// 输入: n = 10
// 输出: 12
// 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
//
// 说明:  
// 1 是丑数。
// n 不超过1690。
//
// @author Ls J
// @date 8/13/20 9:48 PM
//
package main

func nthUglyNumber(n int) int {
    i2, i3, i5 := 0, 0, 0
    dp := make([]int, n)
    dp[0] = 1
    for i := 1; i < n; i++ {
        min := min(dp[i2]*2, min(dp[i3]*3, dp[i5]*5))
        if min == dp[i2]*2 {
            i2++
        }
        if min == dp[i3]*3 {
            i3++
        }
        if min == dp[i5]*5 {
            i5++
        }
        dp[i] = min
    }
    return dp[n-1]
}

func min(a, b int) int {
    if a <= b {
        return a
    }
    return b
}
