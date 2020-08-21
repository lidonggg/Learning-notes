//
// 重新规划路线（中等-1466）
// 中文链接：https://leetcode-cn.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero
//
// 问题描述：
// n 座城市，从 0 到 n-1 编号，其间共有 n-1 条路线。因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。
// 路线用 connections 表示，其中 connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。
// 今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。
// 请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。
// 题目数据 保证 每个城市在重新规划路线方向后都能到达城市 0 。
//
// 示例 1：
// 输入：n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
// 输出：3
// 解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
//
// 示例 2：
// 输入：n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
// 输出：2
// 解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
//
// 示例 3：
// 输入：n = 3, connections = [[1,0],[2,0]]
// 输出：0
//
// 提示：
// 2 <= n <= 5 * 10^4
// connections.length == n-1
// connections[i].length == 2
// 0 <= connections[i][0], connections[i][1] <= n-1
// connections[i][0] != connections[i][1]
//
// @author Ls J
// @date 8/21/20 9:52 PM
//
package bfs

type Neibor struct {
    // N 代表节点
    N int
    // W 代表方向，1--代表方向反了，0--代表方向正确
    W int
}

//
// bfs
//
// 执行用时：168 ms，在所有 Go 提交中击败了 87.06% 的用户
// 内存消耗：13.9 MB，在所有 Go 提交中击败了 100.00% 的用户
//
// @param n  int   城市数量
// @param connections [][]int  边
// @return 要改变方向的路线数量
//
func minReorder(n int, connections [][]int) int {
    g := make([][]Neibor, n)

    for i := range connections {
        // 因为道路原先就是从 connections[i][0] 到 connections[i][1]，如果刚好和 bfs 的访问路径一致，也就是方向从城市0出来的方向，就需要反转
        g[connections[i][0]] = append(g[connections[i][0]], Neibor{N: connections[i][1], W: 1})
        g[connections[i][1]] = append(g[connections[i][1]], Neibor{N: connections[i][0], W: 0})
    }

    visited := make([]bool, n)

    cities := []int{0}
    visited[0] = true
    // bfs
    res := 0
    for len(cities) > 0 {
        newCities := make([]int, 0)
        for i := range cities {
            for _, neibor := range g[cities[i]] {
                if !visited[neibor.N] {
                    newCities = append(newCities, neibor.N)
                    visited[neibor.N] = true
                    res += neibor.W
                }
            }
        }
        cities = newCities
    }

    return res
}
