package com.lidong.algorithm.leetcode.medium.topology;

import java.util.*;

/**
 * 课程表 II（中等-210）
 * 中文链接：https://leetcode-cn.com/problems/course-schedule-ii
 * <p>
 * 问题描述：
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 * <p>
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: [0,1]
 * 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * <p>
 * 示例 2:
 * 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
 * 输出: [0,1,2,3] or [0,2,1,3]
 * 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 *      因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * 说明:
 * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * <p>
 * 提示:
 * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
 * 拓扑排序也可以通过 BFS 完成。
 *
 * @author ls J
 * @date 2020/7/2 12:04
 */
public class CourseScheduleII210 {

    /**
     * 拓扑排序 + bfs
     * <p>
     * 执行用时：6 ms，在所有 Java 提交中击败了 80.68% 的用户
     * 内存消耗：41 MB，在所有 Java 提交中击败了 93.33% 的用户
     * <p>
     * 时间复杂度：O(N + M)
     * 空间复杂度：O(N + M)
     * N -- 节点数量，M -- 邻边数量
     *
     * @param n   n
     * @param pqs pqs
     * @return int[]
     */
    public int[] findOrder(int n, int[][] pqs) {
        int[] inDegree = new int[n];
        // 存放结果
        int[] res = new int[n];
        List<List<Integer>> adjacency = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            adjacency.add(new ArrayList<>());
        }
        // 构造入度数组和邻接链表
        for (int[] pq : pqs) {
            inDegree[pq[0]]++;
            adjacency.get(pq[1]).add(pq[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        int curIdx = 0;
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            n--;
            res[curIdx++] = pre;
            for (int cur : adjacency.get(pre)) {
                if (--inDegree[cur] == 0) {
                    queue.add(cur);
                }
            }
        }

        if (n > 0) {
            return new int[]{};
        }
        return res;
    }

    /**
     * 方法二：拓扑排序 + dfs
     * <p>
     * 执行用时：4 ms，在所有 Java 提交中击败了 96.44% 的用户
     * 内存消耗：41.7 MB，在所有 Java 提交中击败了 93.33% 的用户
     * <p>
     * 时间复杂度：O(N + M)
     * 空间复杂度：O(N + M)
     * N -- 节点数量，M -- 邻边数量
     *
     * @param n   n
     * @param pqs pqs
     * @return true / false
     */
    public int[] findOrder2(int n, int[][] pqs) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacency.add(new ArrayList<>());
        }
        int[] flags = new int[n];
        // 构造邻接表
        for (int[] cp : pqs) {
            adjacency.get(cp[1]).add(cp[0]);
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (!dfs(adjacency, flags, i, stack)) {
                return new int[]{};
            }
        }
        // 存放结果
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            res[i] = stack.pop();
        }
        return res;
    }

    /**
     * dfs
     *
     * @param adjacency 邻接表
     * @param flags     标志位：1 -- 被当前节点启动的 dfs 访问过；-1 -- 被其他节点启动的 dfs 访问过
     * @param i         i
     * @return true / false
     */
    private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i, Stack<Integer> stack) {
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
            if (!dfs(adjacency, flags, j, stack)) {
                return false;
            }
        }
        // 标记位记为 -1，作为其他节点的 dfs 的参照
        flags[i] = -1;
        // 当前课程入栈
        stack.push(i);
        return true;
    }
}
