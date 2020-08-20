//
// 二进制矩阵中的最短路径（中等-1091）
// 中文链接：https://leetcode-cn.com/problems/shortest-path-in-binary-matrix
//
// 问题描述：
// 在一个 N × N 的方形网格中，每个单元格有两种状态：空（0）或者阻塞（1）。
// 一条从左上角到右下角、长度为 k 的畅通路径，由满足下述条件的单元格 C_1, C_2, ..., C_k 组成：
// 1. 相邻单元格 C_i 和 C_{i+1} 在八个方向之一上连通（此时，C_i 和 C_{i+1} 不同且共享边或角）
// 2. C_1 位于 (0, 0)（即，值为 grid[0][0]）
// 3. C_k 位于 (N-1, N-1)（即，值为 grid[N-1][N-1]）
// 4. 如果 C_i 位于 (r, c)，则 grid[r][c] 为空（即，grid[r][c] == 0）
// 返回这条从左上角到右下角的最短畅通路径的长度。如果不存在这样的路径，返回 -1 。
//
// 示例 1：
// 输入：[[0,1],[1,0]]
// 输出：2
//
// 示例 2：
// 输入：[[0,0,0],[1,1,0],[1,1,0]]
// 输出：4
//
// 提示：
// 1 <= grid.length == grid[0].length <= 100
// grid[i][j] 为 0 或 1
//
// @author Ls J
// @date 8/20/20 9:22 PM
//
package bfs

//
// bfs
//
// 执行用时：56 ms，在所有 Go 提交中击败了 96.59% 的用户
// 内存消耗：6.4 MB，在所有 Go 提交中击败了 100.00% 的用户
//
// @param grid  [][]int  matrix
// @return shortest path
//
func shortestPathBinaryMatrix(grid [][]int) int {
    if grid[0][0] == 1 {
        return -1
    }

    m, n := len(grid), len(grid[0])
    if m == 1 && n == 1 {
        return 1
    }

    // 方向数组
    dx, dy := []int{-1, -1, 0, 1, 1, 1, 0, -1}, []int{0, 1, 1, 1, 0, -1, -1, -1}

    queue := make([]int, 0)
    queue = append(queue, 0)
    grid[0][0] = 1
    for len(queue) > 0 {
        cur := queue[0]
        // 模拟出队列
        queue = queue[1:]
        x, y := cur/n, cur%n

        for i := 0; i < 8; i++ {
            nx, ny := x+dx[i], y+dy[i]
            if nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 0 {
                queue = append(queue, nx*n+ny)
                grid[nx][ny] = grid[x][y] + 1
                if nx == m-1 && ny == n-1 {
                    return grid[nx][ny]
                }
            }
        }
    }
    return -1
}
