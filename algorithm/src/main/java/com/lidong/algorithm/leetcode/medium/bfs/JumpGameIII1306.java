package com.lidong.algorithm.leetcode.medium.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 跳跃游戏 III（中等-1306）
 * 中文链接：https://leetcode-cn.com/problems/jump-game-iii/
 *
 * 问题描述：
 * 这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
 * 请你判断自己是否能够跳到对应元素值为 0 的 任意 下标处。
 * 注意，不管是什么情况下，你都无法跳到数组之外。
 *
 * 示例 1：
 * 输入：arr = [4,2,3,0,3,1,2], start = 5
 * 输出：true
 * 解释：
 * 到达值为 0 的下标 3 有以下可能方案：
 * 下标 5 -> 下标 4 -> 下标 1 -> 下标 3
 * 下标 5 -> 下标 6 -> 下标 4 -> 下标 1 -> 下标 3
 *
 * 示例 2：
 * 输入：arr = [4,2,3,0,3,1,2], start = 0
 * 输出：true
 * 解释：
 * 到达值为 0 的下标 3 有以下可能方案：
 * 下标 0 -> 下标 4 -> 下标 1 -> 下标 3
 *
 * 示例 3：
 * 输入：arr = [3,0,2,1,2], start = 2
 * 输出：false
 * 解释：无法到达值为 0 的下标 1 处。
 *
 * 提示：
 * 1 <= arr.length <= 5 * 10^4
 * 0 <= arr[i] < arr.length
 * 0 <= start < arr.length
 *
 * @author Ls J
 * @date 2020/6/22 12:11 AM
 */
public class JumpGameIII1306 {

    /**
     * bfs
     *
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：47.4 MB，在所有 Java 提交中击败了 80.00% 的用户
     *
     * @param arr  arr
     * @param start start index
     * @return true if found
     */
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int curIdx = queue.poll();
            if (arr[curIdx] == 0) {
                return true;
            }
            visited[curIdx] = true;
            int right = curIdx + arr[curIdx];
            if (right < n && !visited[right]) {
                queue.add(right);
            }
            int left = curIdx - arr[curIdx];
            if (left >= 0 && !visited[left]) {
                queue.add(left);
            }
        }

        return false;
    }
}
