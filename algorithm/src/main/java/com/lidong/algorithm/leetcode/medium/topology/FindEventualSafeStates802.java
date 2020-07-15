package com.lidong.algorithm.leetcode.medium.topology;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 找到最终的安全状态（中等-802）
 * 中文链接：https://leetcode-cn.com/problems/find-eventual-safe-states
 * <p>
 * 问题描述：
 * 在有向图中, 我们从某个节点和每个转向处开始，沿着图的有向边走。如果我们到达的节点是终点 (即它没有连出的有向边)，我们停止。
 * 现在，如果我们最后能走到终点，那么我们的起始节点是最终安全的。更具体地说，存在一个自然数 K，无论选择从哪里开始行走，我们走了不到 K 步后必能停止在一个终点。
 * 哪些节点最终是安全的？ 结果返回一个有序的数组。
 * 该有向图有 N 个节点，标签为 0, 1, ..., N-1, 其中 N 是 graph 的节点数。图以以下的形式给出: graph[i] 是节点 j 的一个列表，满足 (i, j) 是图的一条有向边。
 * <p>
 * 示例：
 * 输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * 输出：[2,4,5,6]
 * 这里是上图的示意图。
 * <p>
 * 提示：
 * graph 节点数不超过 10000.
 * 图的边数不会超过 32000.
 * 每个 graph[i] 被排序为不同的整数列表， 在区间 [0, graph.length - 1] 中选取。
 *
 * @author ls J
 * @date 2020/7/15 11:24
 */
public class FindEventualSafeStates802 {

    /**
     * 拓扑排序
     * <p>
     * 执行用时：21 ms，在所有 Java 提交中击败了 56.16% 的用户
     * 内存消耗：48.4 MB，在所有 Java 提交中击败了 100.00% 的用户
     * <p>
     * 时间复杂度：O(n+e)
     * 空间复杂度：O(n)
     *
     * @param graph graph
     * @return list
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        // 保存出度
        int[] outDegree = new int[n];

        List<List<Integer>> adjacency = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            adjacency.add(new ArrayList<>());
        }
        // 反向构造邻接表
        for (int i = 0; i < n; ++i) {
            int[] edges = graph[i];
            outDegree[i] = edges.length;
            for (int edge : edges) {
                adjacency.get(edge).add(i);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        // 寻找所有出度为 0 的点，他们就是每一个安全状态的终点
        for (int i = 0; i < n; ++i) {
            if (outDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int curPos = queue.poll();
            // 每遍历到一条边，相应的出度 -1
            for (int i : adjacency.get(curPos)) {
                --outDegree[i];
                if (outDegree[i] == 0) {
                    queue.offer(i);
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (outDegree[i] == 0) {
                res.add(i);
            }
        }

        return res;
    }
}
