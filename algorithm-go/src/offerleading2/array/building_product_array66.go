//
// 面试题 66：构建乘积数组
//
// 问题描述：
// 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
// 其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。
// 不能使用除法。
//
// 示例:
// 输入: [1,2,3,4,5]
// 输出: [120,60,40,30,24]
// 
// 提示：
// 所有元素乘积之和不会溢出 32 位整数
// a.length <= 100000
//
// @author Ls J
// @date 2020-08-07 22:04
//
package main

//
// 前缀和后缀乘积
//
// @param a arr
// @return arr
//
func constructArr(a []int) []int {

    n := len(a)
    if n == 0 {
        return a
    }
    pre, suf := make([]int, n), make([]int, n)

    pre[0] = 1
    for i := 1; i < n; i++ {
        pre[i] = pre[i-1] * a[i-1]
    }

    suf[n-1] = 1
    for i := n - 2; i >= 0; i-- {
        suf[i] = suf[i+1] * a[i+1]
    }

    for i := 0; i < n; i++ {
        a[i] = pre[i] * suf[i]
    }

    return a
}
