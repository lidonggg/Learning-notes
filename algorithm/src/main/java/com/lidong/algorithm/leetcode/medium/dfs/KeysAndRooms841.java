package com.lidong.algorithm.leetcode.medium.dfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 钥匙和房间（中等-841）
 * 中文链接：https://leetcode-cn.com/problems/keys-and-rooms
 * <p>
 * 问题描述：
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 * 你可以自由地在房间之间来回走动。
 * 如果能进入每个房间返回 true，否则返回 false。
 * <p>
 * 示例 1：
 * 输入: [[1],[2],[3],[]]
 * 输出: true
 * 解释:
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 * <p>
 * 示例 2：
 * 输入：[[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 * <p>
 * 提示：
 * - 1 <= rooms.length <= 1000
 * - 0 <= rooms[i].length <= 1000
 * - 所有房间中的钥匙数量总计不超过 3000。
 *
 * @author Ls J
 * @date 2020/7/5 7:08 PM
 */
public class KeysAndRooms841 {

    private List<List<Integer>> rooms;

    private boolean[] visited;

    private int left;

    /**
     * 方法一：dfs
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 95.80% 的用户
     * 内存消耗：39.5 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param rooms rooms
     * @return true / false
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        this.rooms = rooms;
        this.visited = new boolean[n];
        this.left = n;
        dfs(0);
        return left == 0;
    }

    private void dfs(int idx) {
        visited[idx] = true;
        left--;
        List<Integer> curKeys = rooms.get(idx);

        for (int k : curKeys) {
            if (!visited[k]) {
                dfs(k);
            }
        }
    }

    /**
     * 方法二：bfs
     * <p>
     * 执行用时：2 ms，在所有 Java 提交中击败了 73.67% 的用户
     * 内存消耗：40 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param rooms rooms
     * @return true / false
     */
    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            int curRoom = queue.poll();
            List<Integer> curKeys = rooms.get(curRoom);
            n--;
            for (int k : curKeys) {
                if (!visited[k]) {
                    visited[k] = true;
                    queue.add(k);
                }
            }
        }
        return n == 0;
    }
}
