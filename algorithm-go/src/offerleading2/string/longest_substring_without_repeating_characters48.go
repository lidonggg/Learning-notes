//
// 面试题 48：最长的不含重复字符的子字符串
//
// 问题描述：
// 请从字符串中找出一个最长的不含重复字符的子字符串，返回这个子字符串的长度。
// 假设字符串中只包含 'a'~'z' 的字符。
//
// @author Ls J
// @date 2020-06-04 21:36
//
package main

import "fmt"

func main() {
    s := ""
    fmt.Println(findLongest(s))
    fmt.Println(dynamic48(s))
}

//
// 方法一：双指针
//
// 执行用时：4 ms，在所有 Go 提交中击败了 90.58% 的用户
// 内存消耗：2.9 MB，在所有 Go 提交中击败了 100.00% 的用户
//
// @param   s   string  输入字符串
// @return 最长子字符串长度
//
func findLongest(s string) int {
    if len(s) == 0 {
        return 0
    }
    sarr := []rune(s)
    length := len(sarr)

    res := 1
    // 左右指针
    // 我们要始终保持两个指针内的字符串是没有重复字符的
    left, right := 0, 1
    for right < length {
        if length-left < res {
            break
        }
        left += findExist(sarr[left:right], sarr[right]) + 1
        curLen := right - left + 1
        if curLen > res {
            res = curLen
        }
        right++
    }
    return res
}

//
// 查找某个字符在切片中是否存在，如果不存在，返回 -1
//
// @param   s   []rune  字符串qiepian
// @param   t   rune    目标字符
// @return  res 从后往前第一个存在的字符的位置
//
func findExist(s []rune, t rune) (res int) {
    res = -1
    for i := len(s) - 1; i >= 0; i-- {
        if s[i] == t {
            res = i
            break
        }
    }
    return
}

//
// 方法二：动态规划
// dp[i-1] => 以 s[i-1] 为结尾的最长字串的长度
// dp[i] => 1.如果 s[i] 在之前没出现过或者它上一次出现的位置在以 s[i-1] 为结尾的最长字串之前，那么 dp[i] = dp[i-1] + 1
//          2.如果 s[i] 在之前出现过，并且是在以 s[i-1] 为结尾的最长字串中的时候，那么 dp[i] = i - (preIdx)，preIdx 代表它上一次出现的位置
// 由于 dp[i] 只与 dp[i-1] 有关系，因此我们可以用一个常数的空间占用只存储当前字符的前一个字符的状态就行
//
// @param   s   string  输入字符串
// @return 最长子字符串长度
//
func dynamic48(s string) int {
    if len(s) == 0 {
        return 0
    }
    sarr := []rune(s)
    length := len(sarr)
    // 记录每个字符最后一次出现的位置
    // 因为题目限制了字符只能是 'a'~'z'，所以这里用数组来表示
    // 否则可以用一个 map
    positions := [26]int{}
    for i := 0; i < 26; i++ {
        positions[i] = -1
    }

    // curLen: 以当前字符结尾的最大子串长度，这里其实是将一维的 dp 数组优化成了常数空间复杂度
    // dp[i] 代表以当前字符为结尾的最长子字符串，因为 dp[i] 的状态只与 dp[i-1] 有关，所有这里可以用 curLen 优化
    // maxLen: 最终的结果
    lastLen, maxLen := 0, 0

    for i := 0; i < length; i++ {
        preIdx := positions[sarr[i]-'a']
        // 如果当前字符没有出现过或者当前字符两次出现的间隔大于 curLen (也就是以前一个字符为结尾的最大字串不可能包含当前字符)
        // 说明 curLen 需要 +1 => dp[i] = dp[i-1] + 1
        if preIdx == -1 || i-preIdx > lastLen {
            lastLen++
        } else {
            // 否则比较并交换
            if lastLen > maxLen {
                maxLen = lastLen
            }
            // curLen 设置为当前字符与前一次出现的间隔
            // 要命名 curLen 的含义 => curLen = dp[i]
            lastLen = i - preIdx
        }
        positions[sarr[i]-'a'] = i
    }
    // 最后一个字符也有可能是第一次出现，所有这里要额外增加一次比较并交换
    // 或者直接 return 两者的最大值即可
    if lastLen > maxLen {
        maxLen = lastLen
    }

    return maxLen
}
