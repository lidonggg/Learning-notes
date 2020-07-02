package com.lidong.algorithm.leetcode.medium.topology;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 课程表（中等-207）
 * 中文链接：https://leetcode-cn.com/problems/course-schedule
 * <p>
 * 问题描述：
 * 你这个学期必须选修 n 门课程，记为 0 到 n-1 。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 * <p>
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * <p>
 * 示例 2:
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。 
 * <p>
 * 提示：
 * - 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * - 你可以假定输入的先决条件中没有重复的边。
 * - 1 <= n <= 10^5
 *
 * @author ls J
 * @date 2020/7/1 19:19
 */
public class CourseSchedule207 {

    /**
     * 方法一：拓扑排序 + dfs （Kahn 算法）
     * <p>
     * 执行用时：6 ms，在所有 Java 提交中击败了 73.87% 的用户
     * 内存消耗：40.6 MB，在所有 Java 提交中击败了 90.00% 的用户
     * <p>
     * 时间复杂度：O(N + M)
     * 空间复杂度：O(N + M)
     * N -- 节点数量，M -- 邻边数量
     *
     * @param n   n
     * @param pqs pqs
     * @return true / false
     */
    public boolean canFinish(int n, int[][] pqs) {
        // 某个点入度
        int[] inDegree = new int[n];
        // 邻接表
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            adjacency.add(new ArrayList<>());
        }
        for (int[] pq : pqs) {
            inDegree[pq[0]]++;
            adjacency.get(pq[1]).add(pq[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        // 将所有入度为 0 的添加到队列中，作为起点
        for (int i = 0; i < n; ++i) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            // 每出队一个元素，剩余数量就减一
            n--;
            for (int cur : adjacency.get(pre)) {
                // 如果入度为 0，将其加到队列中
                if (--inDegree[cur] == 0) {
                    queue.add(cur);
                }
            }
        }
        // 如果 n == 0，就代表能全部修完
        return n == 0;
    }

    /**
     * 方法二：拓扑排序 + dfs
     *
     * @param n   n
     * @param pqs pqs
     * @return true / false
     */
    public boolean canFinish2(int n, int[][] pqs) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacency.add(new ArrayList<>());
        }
        int[] flags = new int[n];
        // 构造邻接表
        for (int[] cp : pqs) {
            adjacency.get(cp[1]).add(cp[0]);
        }
        for (int i = 0; i < n; i++) {
            if (!dfs(adjacency, flags, i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * dfs
     *
     * @param adjacency 邻接表
     * @param flags     标志位：1 -- 被当前节点启动的 dfs 访问过；-1 -- 被其他节点启动的 dfs 访问过
     * @param i         i
     * @return true / false
     */
    private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
        // 说明同一个节点的 dfs 访问到了两次 i，产生了环，直接返回 false
        if (flags[i] == 1) {
            return false;
        }
        // 被其他节点的 dfs 访问过了，无需再重复搜索，直接返回 true
        if (flags[i] == -1) {
            return true;
        }
        // 标记其被本轮访问
        flags[i] = 1;
        for (Integer j : adjacency.get(i)) {
            if (!dfs(adjacency, flags, j)) {
                return false;
            }
        }
        // 标记位记为 -1，作为其他节点的 dfs 的参照
        flags[i] = -1;
        return true;
    }
}
