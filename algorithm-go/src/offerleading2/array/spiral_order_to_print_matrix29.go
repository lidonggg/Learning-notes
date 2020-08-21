//
// 面试题 29：顺时针打印矩阵
//
// 问题描述：
// 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
//
// 示例 1：
// 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
// 输出：[1,2,3,6,9,8,7,4,5]
//
// 示例 2：
// 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
// 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
//
// 限制：
// 0 <= matrix.length <= 100
// 0 <= matrix[i].length <= 100
//
// @author Ls J
// @date 8/21/20 9:59 PM
//
package main

//
// 执行用时：8 ms，在所有 Go 提交中击败了 99.10% 的用户
// 内存消耗：6.1 MB，在所有 Go 提交中击败了 58.88% 的用户
// @param matrix  [][]int  matrix
// @return []int   print order
//
func spiralOrder(matrix [][]int) []int {
    if len(matrix) == 0 || len(matrix[0]) == 0 {
        return []int{}
    }
    m, n := len(matrix), len(matrix[0])
    visited := make([][]bool, m)
    for i := 0; i < m; i++ {
        visited[i] = make([]bool, n)
    }

    var (
        total = m * n
        order = make([]int, total)
        row, column = 0, 0
        directions = [][]int{[]int{0, 1}, []int{1, 0}, []int{0, -1}, []int{-1, 0}}
        directionIndex = 0
    )

    for i := 0; i < total; i++ {
        order[i] = matrix[row][column]
        visited[row][column] = true
        nextRow, nextColumn := row + directions[directionIndex][0], column + directions[directionIndex][1]
        if nextRow < 0 || nextRow >= m || nextColumn < 0 || nextColumn >= n || visited[nextRow][nextColumn] {
            directionIndex = (directionIndex + 1) % 4
        }
        row += directions[directionIndex][0]
        column += directions[directionIndex][1]
    }
    return order
}
