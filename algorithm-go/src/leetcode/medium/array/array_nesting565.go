//
// 数组嵌套（中等-565）
// 中文链接：https://leetcode-cn.com/problems/array-nesting
//
// 问题描述：
// 索引从0开始长度为N的数组 A，包含 0 到 N - 1 的所有整数。找到最大的集合 S 并返回其大小，其中 S[i] = {A[i], A[A[i]], A[A[A[i]]], ... } 且遵守以下的规则。
// 假设选择索引为i的元素 A[i] 为 S 的第一个元素，S 的下一个元素应该是 A[A[i]]，之后是A[A[A[i]]]... 以此类推，不断添加直到 S 出现重复的元素。
//
// 示例 1:
// 输入: A = [5,4,0,3,1,6,2]
// 输出: 4
// 解释:
// A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
// 其中一种最长的 S[K]:
// S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
// 
// 提示：
// 1. N是[1, 20,000]之间的整数。
// 2. A中不含有重复的元素。
// 3. A中的元素大小在[0, N-1]之间。
//
// @author Ls J
// @date 8/17/20 11:27 PM
//
package array

//
// 执行用时：12 ms，在所有 Go 提交中击败了 100.00% 的用户
// 内存消耗：5.9 MB，在所有 Go 提交中击败了 100.00% 的用户
//
func arrayNesting(nums []int) int {
    res := 0
    for i := 0; i < len(nums); i++ {
        if nums[i] != 20000 {
            start, count := nums[i], 0
            for nums[start] != 20000 {
                count++
                tmp := start
                start = nums[start]
                // 模拟标记为已访问
                nums[tmp] = 20000
            }
            res = max(res, count)
        }
    }
    return res
}

func max(a, b int) int {
    if a >= b {
        return a
    }
    return b
}
