//
// 面试题 12：矩阵中的路径
//
// 问题描述：
// 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
// 路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。
// 如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
// 例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径。
// [["a","b","c","e"],
// ["s","f","c","s"],
// ["a","d","e","e"]]
// 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
//
// 示例 1：
// 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
// 输出：true
//
// 示例 2：
// 输入：board = [["a","b"],["c","d"]], word = "abcd"
// 输出：false
//
// 提示：
// 1 <= board.length <= 200
// 1 <= board[i].length <= 200
// board[i][j] 都是字母
//
// @author Ls J
// @date 2020-06-01 21:38
//
package main

import "fmt"

func main() {
    matrix := [][]byte{
        {'a', 'b', 'c', 'e'},
        {'s', 'f', 'c', 's'},
        {'a', 'd', 'e', 'e'},
    }

    // fmt.Println(checkExist1(matrix, "bfce"))
    fmt.Println(checkExist2(matrix, "bfce"))
}

//
// 方法一：DFS
//
// @param board  [][]byte	matrix
// @param word 	 string		word
// @return true if found
//
func checkExist1(board [][]byte, word string) bool {
    rows, cols := len(board), len(board[0])
    // 以每个元素作为起点，如果找到了就返回 true
    for i := 0; i < rows; i++ {
        for j := 0; j < cols; j++ {
            if dfs12(board, rows, cols, i, j, word, 0) {
                return true
            }
        }
    }
    return false
}

//
// dfs
// 这里会改变原数组的内容，如果不想改变原数组，可以额外开辟一个 bool 类型的数组，表示当前元素有没有被占用
//
// @param board   [][]byte   matrix
// @param rows	  int 	     rows
// @param cols	  int   	 cols
// @param i	      int 	     当前横坐标
// @param j	      int 	     当前纵坐标
// @param word    string	 word
// @param k       int   	 index of character
// @return true if
//
func dfs12(board [][]byte, rows int, cols int, i int, j int, word string, k int) bool {
    // 如果当前位置的字符与 word 中对应位置的字符不同，返回 false
    if board[i][j] != word[k] {
        return false
    }
    // 如果是最后一个字符了，返回 true
    if k == len(word)-1 {
        return true
    }
    // 临时变量存储 board[i][j] 本来的值
    temp := board[i][j]
    // board[i][j] 置为 ' '，方便下次排重
    board[i][j] = byte(' ')
    // 往左走
    if 0 <= i-1 && dfs12(board, rows, cols, i-1, j, word, k+1) {
        return true
    }
    // 往右走
    if i+1 < rows && dfs12(board, rows, cols, i+1, j, word, k+1) {
        return true
    }
    // 往上走
    if 0 <= j-1 && dfs12(board, rows, cols, i, j-1, word, k+1) {
        return true
    }
    // 往下走
    if j+1 < cols && dfs12(board, rows, cols, i, j+1, word, k+1) {
        return true
    }
    board[i][j] = temp
    return false
}

//
// 方法二：回溯，书中的方法
//
// @param board  [][]byte	matrix
// @param word 	 string		word
// @return true if found
//
func checkExist2(board [][]byte, word string) bool {
    rows, cols := len(board), len(board[0])
    // 以每个元素作为起点，如果找到了就返回 true
    for i := 0; i < rows; i++ {
        for j := 0; j < cols; j++ {
            if backtrack12(board, rows, cols, i, j, word, 0) {
                return true
            }
        }
    }
    return false
}

//
// 回溯
// 这里会改变原数组的内容，如果不想改变原数组，可以额外开辟一个 bool 类型的数组，表示当前元素有没有被占用
//
// @param board   [][]byte   matrix
// @param rows	  int 	     rows
// @param cols	  int   	 cols
// @param i	      int 	     当前横坐标
// @param j	      int 	     当前纵坐标
// @param word    string	 word
// @param k       int   	 index of character
//
func backtrack12(board [][]byte, rows int, cols int, i int, j int, word string, k int) bool {
    if k == len(word) {
        return true
    }
    hasPath := false
    if i >= 0 && i < rows && j >= 0 && j < cols && board[i][j] == word[k] {
        k++
        temp := board[i][j]
        board[i][j] = ' '
        // 左
        hasPath = backtrack12(board, rows, cols, i-1, j, word, k) ||
            // 右
            backtrack12(board, rows, cols, i+1, j, word, k) ||
            // 上
            backtrack12(board, rows, cols, i, j-1, word, k) ||
            // 下
            backtrack12(board, rows, cols, i, j+1, word, k)
        if !hasPath {
            // 回溯
            k--
            board[i][j] = temp
        }
    }
    return hasPath
}
