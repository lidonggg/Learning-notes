//
// 面试题 5：替换空格
// 问题描述：
// 实现一个函数，把字符串中的空格替换成"%20"，要求用 O(n) 时间复杂度。
//
// @author Ls J
// @date 2020-05-30 00:35
//
package main

import "fmt"

func main() {
    var res = replaceBlank("1111 222")
    fmt.Print(res)
}

//
// 遍历一次字符串，遇到空格就替换成"%20"
//
// @param str   string    原字符串
// @return string   替换后的字符串
//
func replaceBlank(str string) string {
    var b []rune
    for _, v := range str {
        if v == 32 {
            // " " -> "%20"
            b = append(b, 37, 50, 48)
        } else {
            b = append(b, v)
        }
    }
    return string(b)
}