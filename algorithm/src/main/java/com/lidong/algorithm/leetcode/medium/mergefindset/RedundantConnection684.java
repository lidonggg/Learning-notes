package com.lidong.algorithm.leetcode.medium.mergefindset;

/**
 * 冗余连接（中等-684）
 * 中文链接：https://leetcode-cn.com/problems/redundant-connection
 * <p>
 * 问题描述：
 * 在本问题中, 树指的是一个连通且无环的无向图。
 * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。
 * 附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
 * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
 * <p>
 * 示例 1：
 * 输入: [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 解释: 给定的无向图为:
 * <p>  1
 * <p> / \
 * <p>2 - 3
 * <p>
 * 示例 2：
 * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * 输出: [1,4]
 * 解释: 给定的无向图为:
 * <p>5 - 1 - 2
 * <p>    |   |
 * <p>    4 - 3
 * <p>
 * 注意:
 * 1.输入的二维数组大小在 3 到 1000。
 * 2.二维数组中的整数在1到N之间，其中N是输入数组的大小。
 *
 * @author Ls J
 * @date 2020/7/11 2:42 PM
 */
public class RedundantConnection684 {

    /**
     * 关于并查集：https://leetcode-cn.com/problems/redundant-connection/solution/tong-su-jiang-jie-bing-cha-ji-bang-zhu-xiao-bai-ku/
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 97.70% 的用户
     * 内存消耗：40.4 MB，在所有 Java 提交中击败了 33.33% 的用户
     *
     * @param edges edges
     * @return edge
     */
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        // 最初始的状态每个节点都是自己的代表，也是自己所在集合的代表
        for (int i = 1; i < n; ++i) {
            parent[i] = i;
        }
        for (int[] edge : edges) {
            if (find(parent, edge[0]) == find(parent, edge[1])) {
                return edge;
            } else {
                union(parent, edge[0], edge[1]);
            }
        }
        return new int[0];
    }

    /**
     * 确定 x 属于哪一个子集（找到代表节点，从当前节点一直往上走直到最后一个节点）
     *
     * @param parent 每个元素所在集合的代表节点（可以看成树的根结点）
     * @param x      当前元素
     * @return 代表节点
     */
    private int find(int[] parent, int x) {
        int tp = x;
        while (parent[tp] != tp) {
            tp = parent[tp];
        }
        return tp;
    }

    /**
     * 合并两个集合
     *
     * @param parent parent
     * @param x      edge[0]
     * @param y      edge[1]
     */
    private void union(int[] parent, int x, int y) {
        // x 的代表节点
        int px = find(parent, x);
        // y 的代表节点
        int py = find(parent, y);
        // 如果两个代表节点不同，则需要合并两个集合
        if (px != py) {
            parent[py] = px;
        }
    }
}
