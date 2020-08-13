//
// 有序数组中的单一元素（中等-540）
// 中文链接：https://leetcode-cn.com/problems/single-element-in-a-sorted-array
//
// 问题描述：
// 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
//
// 示例 1:
// 输入: [1,1,2,3,3,4,4,8,8]
// 输出: 2
//
// 示例 2:
// 输入: [3,3,7,7,10,11,11]
// 输出: 10
//
// 注意: 您的方案应该在 O(log n)时间复杂度和 O(1)空间复杂度中运行。
//
// @author Ls J
// @date 8/13/20 9:53 PM
//
package binarysearch

//
// 执行用时：8 ms，在所有 Go 提交中击败了 93.33% 的用户
// 内存消耗：4.1 MB，在所有 Go 提交中击败了 100.00% 的用户
//
// @param nums  []int  nums
// @return res
//
func singleNonDuplicate(nums []int) int {
    n := len(nums)

    l, r := 0, n-1

    for l < r {
        mid := l + ((r - l) >> 1)
        // 如果中间索引不是偶数，我们可以对其 -1，只针对偶数位置进行判断
        if mid%2 == 1 {
            mid--
        }
        // 中间索引是偶数，那么如果它前面不存在只出现一次的数，当前值应该是相同元素的第一个
        if nums[mid] == nums[mid+1] {
            l = mid + 2
        } else {
            // 否则只出现一次的数在前面
            r = mid
        }
    }
    return nums[l]
}
