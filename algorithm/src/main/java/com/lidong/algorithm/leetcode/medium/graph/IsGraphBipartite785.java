package com.lidong.algorithm.leetcode.medium.graph;

/**
 * 判断二分图（中等-785）
 * 中文链接：https://leetcode-cn.com/problems/is-graph-bipartite
 * <p>
 * 问题描述：
 * 给定一个无向图graph，当这个图为二分图时返回true。
 * 如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。
 * graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。
 * <p>
 * 示例 1:
 * 输入: [[1,3], [0,2], [1,3], [0,2]]
 * 输出: true
 * 解释:
 * 无向图如下:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * 我们可以将节点分成两组: {0, 2} 和 {1, 3}。
 * <p>
 * 示例 2:
 * 输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * 输出: false
 * 解释:
 * 无向图如下:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * 我们不能将节点分割成两个独立的子集。
 * <p>
 * 注意:
 * 1.graph 的长度范围为 [1, 100]。
 * 2.graph[i] 中的元素的范围为 [0, graph.length - 1]。
 * 3.graph[i] 不会包含 i 或者有重复的值。
 * 4.图是无向的: 如果j 在 graph[i]里边, 那么 i 也会在 graph[j]里边。
 *
 * @author ls J
 * @date 2020/7/6 14:02
 */
public class IsGraphBipartite785 {

    private int[][] graph;

    /**
     * 用 1 / -1 代表两个分区
     */
    private int[] flags;

    /**
     * dfs
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40.5 MB，在所有 Java 提交中击败了 75.00% 的用户
     * <p>
     * 也可以用 bfs 来做，但是由于图可能是不连通的，因此对于每个没有访问过的点，我们都要进行一次 bfs
     * 思路都差不多，都是根据 flags[] 数组来进行判断并分区，这里就不给出 bfs 具体解答方法了
     *
     * @param graph graph[i] 代表所有与 i 相连的点
     * @return true / false
     */
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        this.graph = graph;
        this.flags = new int[n];
        for (int i = 0; i < n; ++i) {
            if (flags[i] == 0) {
                // 图不一定是连通的，这里每新增一次 dfs，就代表新增了一个连通图，flag 为 1 或者 -1 都可以
                if (!dfs(i, 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * dfs
     *
     * @param i    当前数字
     * @param flag i 期望所在的分区
     * @return true / false
     */
    private boolean dfs(int i, int flag) {
        // 如果相同，代表 i 已经被其他节点访问过了，且在期望的分区中
        if (flags[i] == flag) {
            return true;
        }
        // 如果不同，代表 i 不在期望的分区，返回 false
        if (flags[i] == -flag) {
            return false;
        }
        flags[i] = flag;
        int[] gi = graph[i];
        boolean res = true;
        for (int j : gi) {
            // 染相反色
            res = res && dfs(j, -flag);
        }

        return res;
    }
}
