//
// 面试题 14：剪绳子
//
// 问题描述：
// 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n > 1 并且 m > 1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
// 请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
// 例如，当绳子的长度是 8 时，我们把它剪成长度分别为 2、3、3 的三段，此时得到的最大乘积是 18。
//
// 示例 1：
// 输入: 2
// 输出: 1
// 解释: 2 = 1 + 1, 1 × 1 = 1
//
// 示例 2:
// 输入: 10
// 输出: 36
// 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36

// 提示：
// 2 <= n <= 58
//
// 执行用时：0 ms，在所有 Go 提交中击败了 100.00% 的用户
// 内存消耗：2 MB，在所有 Go 提交中击败了 100.00% 的用户
//
// @author Ls J
// @date 2020-06-03 01:32
//
package main

import (
    "fmt"
    "math"
)

func main() {
    n := 8
    fmt.Println(dynamic14(n))
    fmt.Println(greedy14(n))
}

//
// 方法一：动态规划
//
// @param   n    int   绳子长度
// @return  int  乘积最大值
//
func dynamic14(n int) int {
    if 2 > n || 58 < n {
        return -1
    }

    if n <= 3 {
        return n - 1
    }

    var max int

    // dp[i] 代表长度为 i 的时候乘积的最大值
    dp := make([]int, n+1)
    // 前 3 个就不用看了，显而易见，接下来就靠它们来 dp 了
    dp[0] = 0
    dp[1] = 1
    // 注意 dp[2] = 2，dp[3] = 3，因为它们只是一个中间结果，不受限于 m > 1
    dp[2] = 2
    dp[3] = 3

    for i := 4; i <= n; i++ {
        max = 0
        // 为了求出 dp[i]，我们需要计算所有可能的 dp[j] * dp[i-j]
        for j := 1; j <= i/2; j++ {
            dpi := dp[j] * dp[i-j]
            if max < dpi {
                max = dpi
            }
        }
        dp[i] = max
    }

    return dp[n]
}

//
// 方法二：贪心算法
//
// 基于数学结论：当 n >= 5 的时候，要尽可能多的将绳子剪出长度为 3 的绳子；当剩下的为 4 时，要剪成 2 跟长度为 2 的绳子
// 证明：https://leetcode-cn.com/problems/jian-sheng-zi-lcof/solution/mian-shi-ti-14-i-jian-sheng-zi-tan-xin-si-xiang-by/
//
// @param   n    int   绳子长度
// @return  int  乘积最大值
//
func greedy14(n int) int {
    if 2 > n || 58 < n {
        return -1
    }

    if n <= 3 {
        return n - 1
    }
    numOf3 := n / 3
    if n-numOf3*3 == 1 {
        numOf3--
    }
    numOf2 := (n - numOf3*3) / 2
    return int(math.Pow(3, float64(numOf3)) * math.Pow(2, float64(numOf2)))
}
