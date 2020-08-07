//
// 面试题 13：机器人的运动范围
//
// 问题描述：
// 地上有一个 m 行 n 列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
// 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），但不能进入行坐标和列坐标的数位之和大于 k 的格子。
// 例如，当k为 18 时，机器人能够进入方格 [35, 37] ，因为 3+5+3+7=18。
// 但它不能进入方格 [35, 38]，因为 3+5+3+8=19。请问该机器人能够到达多少个格子？
//
// 示例 1：
// 输入：m = 2, n = 3, k = 1
// 输出：3
//
// 示例 2：
// 输入：m = 3, n = 1, k = 0
// 输出：1
//
// 提示：
// 1 <= m,n <= 100
// 0 <= k <= 20
//
// @author Ls J
// @date 2020-06-02 22:45
//
package main

import (
    "fmt"
    . "lib/queue"
)

// 点坐标
type point struct {
    x int
    y int
}

func main() {
    m, n, k := 1, 2, 1
    fmt.Println(bfs13(m, n, k))
    fmt.Println(dfs13Helper(m, n, k))
    fmt.Println(dynamic13(m, n, k))
}

//
// 方法一：bfs
//
// @param   m     int 横坐标长度
// @param   n     int 纵坐标长度
// @param   k     int k
// @return  total int 总格子数
//
func bfs13(m, n, k int) int {
    if m == 0 || n == 0 {
        return 0
    }
    if k == 0 {
        return 1
    }
    // visited 数组
    visited := make([][]bool, m)
    for i := 0; i < m; i++ {
        tmp := make([]bool, n)
        visited[i] = tmp
    }

    // 向右和向下位移
    // 其实只要看这两个方向就行了
    dx := [2]int{0, 1}
    dy := [2]int{1, 0}

    res := 1
    visited[0][0] = true
    queue := new(Queue)
    queue.Init()
    queue.Enqueue(point{0, 0})

    for queue.Size() > 0 {
        curPoint := queue.Dequeue().(point)
        for i := 0; i < 2; i++ {
            newX := curPoint.x + dx[i]
            newY := curPoint.y + dy[i]
            if newX >= m || newY >= n || visited[newX][newY] || getSum(newX)+getSum(newY) > k {
                continue
            }
            res++
            visited[newX][newY] = true
            queue.Enqueue(point{newX, newY})
        }
    }

    return res
}

//
// dfs init
//
// @param   m     int 横坐标长度
// @param   n     int 纵坐标长度
// @param   k     int k
// @return  total int 总格子数
//
func dfs13Helper(m, n, k int) int {
    if m == 0 || n == 0 {
        return 0
    }
    if k == 0 {
        return 1
    }
    // visited 数组
    visited := make([][]bool, m)
    for i := 0; i < m; i++ {
        tmp := make([]bool, n)
        visited[i] = tmp
    }

    return dfs13(m, n, k, 0, 0, visited)
}

//
// 方法二：dfs
//
// @param   m       int      横坐标长度
// @param   n       int      纵坐标长度
// @param   k       int      k
// @param   visited [][]bool 记录被访问过的格子
// @param   x       int      横坐标
// @param   y       int      纵坐标
// @param   res     int      存放结果
// @return  total   int      总格子数
//
func dfs13(m, n, k, x, y int, visited [][]bool) int {
    if x >= m || y >= n || visited[x][y] || getSum(x)+getSum(y) > k {
        return 0
    }
    visited[x][y] = true
    res := 1
    // 向右和向下位移
    // 其实只要看这两个方向就行了
    dx := [2]int{0, 1}
    dy := [2]int{1, 0}
    for i := 0; i < 2; i++ {
        newX := x + dx[i]
        newY := y + dy[i]
        res += dfs13(m, n, k, newX, newY, visited)
    }
    return res
}

//
// 方法三：动态规划
//
// @param   m     int 横坐标长度
// @param   n     int 纵坐标长度
// @param   k     int k
// @return  total int 总格子数
//
func dynamic13(m, n, k int) int {
    if m == 0 || n == 0 {
        return 0
    }
    if k == 0 {
        return 1
    }
    // visited 数组，这里用 int 类型来保存，方便下面的计算，res 可以直接加 visited[i][j]
    visited := make([][]int, m)
    for i := 0; i < m; i++ {
        tmp := make([]int, n)
        visited[i] = tmp
    }
    res := 1
    visited[0][0] = 1

    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            if (i == 0 && j == 0) || getSum(i)+getSum(j) > k {
                continue
            }
            // 边界判断
            // 只要 (i-1, j) 和 (i, j-1) 有一个可达的，那么 (i, j) 就是可达的
            if i-1 >= 0 {
                visited[i][j] |= visited[i-1][j]
            }
            if j-1 >= 0 {
                visited[i][j] |= visited[i][j-1]
            }
            res += visited[i][j]
        }
    }

    return res
}

//
// 获取数位和
//
// @param   num   int   num
// @return  num
func getSum(num int) int {
    res := 0
    for ; num > 0; num /= 10 {
        res += num % 10
    }
    return res
}
