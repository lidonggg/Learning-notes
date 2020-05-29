//
// 面试题 4：二维数组中的查找
// 问题描述：
// 在一个 int 类型的二维数组中，每一行从左到右递增，每一列从左到右递增，现给定一个整数 key，判断它是否在这个数组中
//
// @author Ls J
// @date 2020-05-29 22:10
//
package array

import "fmt"

func main() {
	var arr = [][]int{
		{1, 2, 3, 4},
		{5, 6, 7, 8},
		{6, 7, 8, 9},
	}
	fmt.Print(find(arr, 10))
}

func find(arr [][]int, key int) bool {
	var rows = len(arr) - 1
	if rows == 0 || len(arr[0]) == 0 {
		return false
	}
	var columns = len(arr[0])
	var row = 0
	var column = columns - 1
	// 从右上角开始看
	for row < rows && column >= 0 {
		if arr[row][column] > key {
			// 如果当前数字大于要查找的数字，则剔除该列，寻找下一列
			column--
		} else if arr[row][column] < key {
			// 如果当前数字小于要查找的数字，则剔除该行，寻找下一行
			row++
		} else {
			return true
		}
	}
	return false
}
