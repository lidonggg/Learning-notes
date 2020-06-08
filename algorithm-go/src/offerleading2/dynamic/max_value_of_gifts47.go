//
// 面试题 47：礼物的最大价值
//
// 问题描述：
// 在一个 m * n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
// 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格，直到到达棋盘的右下角。
// 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
//
// @author Ls J
// @date 2020-06-08 21:33
//
package main

import "fmt"

func main() {
    arr := [][]int{
        {1, 10, 3, 8},
        {12, 2, 9, 6},
        {5, 7, 4, 11},
        {3, 7, 16, 5},
    }
    fmt.Print(53 == maxValue(arr))
}

//
// 动态规划，更改原数组
// 状态转移方程：
//
// dp[i][j] = max(dp[i-1][j], dp[i][j-1]) + grip[i][j]
//
// 执行用时：8 ms，在所有 Go 提交中击败了 92.28% 的用户
// 内存消耗：3.9 MB，在所有 Go 提交中击败了 100.00% 的用户
//
// @param   grid    [][]int     m * n 棋盘
// @return  最大价值
//
func maxValue(grid [][]int) int {
    lenX := len(grid)
    if lenX == 0 {
        return 0
    }
    lenY := len(grid[0])
    if lenY == 0 {
        return 0
    }
    for i := 1; i < lenX; i++ {
        grid[i][0] += grid[i-1][0]
    }
    for i := 1; i < lenY; i++ {
        grid[0][i] += grid[0][i-1]
    }
    for i := 1; i < lenX; i++ {
        for j := 1; j < lenY; j++ {
            grid[i][j] += max(grid[i-1][j], grid[i][j-1])
        }
    }
    return grid[lenX-1][lenY-1]
}

//
// 返回两者最大值
//
// @param   a   int    a
// @param   b   int    b
// @return max of a and b
//
func max(a int, b int) int {
    if a >= b {
        return a
    }
    return b
}
