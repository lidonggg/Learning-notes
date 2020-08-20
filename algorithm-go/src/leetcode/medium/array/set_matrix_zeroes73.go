//
// 矩阵置零（中等-73）
// 中文链接：https://leetcode-cn.com/problems/set-matrix-zeroes
//
// 问题描述：
// 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
//
// 示例 1:
// 输入:
// [
//  [1,1,1],
//  [1,0,1],
//  [1,1,1]
// ]
// 输出:
// [
//  [1,0,1],
//  [0,0,0],
//  [1,0,1]
// ]
//
// 示例 2:
// 输入:
// [
//  [0,1,2,0],
//  [3,4,5,2],
//  [1,3,1,5]
// ]
// 输出:
// [
//  [0,0,0,0],
//  [0,4,5,0],
//  [0,3,1,0]
// ]
//
// 进阶:
// 1.一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
// 2.一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
// 你能想出一个常数空间的解决方案吗？
//
// @author Ls J
// @date 8/20/20 9:28 PM
//
package array

//
// 在首行和首列设置标记位，等全部遍历完成之后，根据标记位置零
//
// 执行用时：12 ms，在所有 Go 提交中击败了 95.02% 的用户
// 内存消耗：6 MB，在所有 Go 提交中击败了 42.70% 的用户
//
// @param matrix [][]int matrix
//
func setZeroes(matrix [][]int) {
    m := len(matrix)
    if m == 0 {
        return
    }
    n := len(matrix[0])
    if n == 0 {
        return
    }
    // 记录第一行和第一列是否需要被置零
    clearFirstR, clearFirstC := false, false
    // 通过第一行或第一列设置标记位
    for r := 0; r < m; r++ {
        for c := 0; c < n; c++ {
            // 第一行和第一列特殊处理，否则我们不能确定标记位是否是第一行或第一列影响的
            if r == 0 && matrix[r][c] == 0 {
                clearFirstR = true
            }
            if c == 0 && matrix[r][c] == 0 {
                clearFirstC = true
            }
            if matrix[r][c] == 0 {
                matrix[r][0], matrix[0][c] = 0, 0
            }
        }
    }
    // 处理每一行（除了第一行）
    for r := 1; r < m; r++ {
        if matrix[r][0] == 0 {
            for c := 1; c < n; c++ {
                matrix[r][c] = 0
            }
        }
    }
    // 处理每一列（除了第一列）
    for c := 1; c < n; c++ {
        if matrix[0][c] == 0 {
            for r := 1; r < m; r++ {
                matrix[r][c] = 0
            }
        }
    }
    // 处理第一行和第一列
    if clearFirstR {
        for c := 1; c < n; c++ {
            matrix[0][c] = 0
        }
    }
    if clearFirstC {
        for r := 1; r < m; r++ {
            matrix[r][0] = 0
        }
    }
}
