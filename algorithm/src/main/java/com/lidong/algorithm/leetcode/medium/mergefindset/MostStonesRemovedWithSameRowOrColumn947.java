package com.lidong.algorithm.leetcode.medium.mergefindset;

import java.util.HashSet;
import java.util.Set;

/**
 * 移除最多的同行或同列石头（中等-947）
 * 中文链接：https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column/
 * <p>
 * 问题描述：
 * 我们将石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 * 每次 move 操作都会移除一块所在行或者列上有其他石头存在的石头。
 * 请你设计一个算法，计算最多能执行多少次 move 操作？
 * <p>
 * 示例 1：
 * 输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * 输出：5
 * <p>
 * 示例 2：
 * 输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * 输出：3
 * <p>
 * 示例 3：
 * 输入：stones = [[0,0]]
 * 输出：0
 * <p>
 * 提示：
 * 1 <= stones.length <= 1000
 * 0 <= stones[i][j] < 10000
 *
 * @author Ls J
 * @date 2020/7/11 5:32 PM
 */
public class MostStonesRemovedWithSameRowOrColumn947 {

    /**
     * 并查集
     * 将同一行或者同一列的石头两两相连，变成一个图，这样的话，在图中，相互连通的石子组成了一个连通分量。
     * 显然，同一个连通分量中的石子都可以通过一定的方法（从每个图的最后一个节点开始删除）删除到只剩下最后一个石子。
     * 那么删除的石子个数就是石子总数减去连通分量的个数。
     * <p>
     * 对于一个坐标为 (i, j) 的石子来说，需要把行 i 和列 j 合并，因为并查集是一维的，用 j+10000 来代替 j。
     * 在将所有石子的行和列都合并好之后，只需数一下并查集中有几个集合就可以得到答案了。
     * <p>
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(n)
     * <p>
     * 执行用时：5 ms，在所有 Java 提交中击败了 99.47% 的用户
     * 内存消耗：39.8 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param stones stones
     * @return removed num
     */
    public int removeStones(int[][] stones) {
        int n = stones.length;
        // 用一维数组来表示二维
        int[] parent = new int[20000];
        for (int i = 0; i < 20000; ++i) {
            parent[i] = i;
        }
        // 合并集合
        for (int[] stone : stones) {
            union(parent, stone[0], stone[1] + 10000);
        }

        Set<Integer> seen = new HashSet<>();
        // 将每个集合的代表节点（根结点）添加到 set 中，这样 set 中的元素个数就是连通分量的个数
        for (int[] stone : stones) {
            seen.add(find(parent, stone[0]));
        }
        return n - seen.size();
    }

    /**
     * 查找每个节点的代表节点
     *
     * @param parent parent
     * @param x      x
     * @return y
     */
    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }

    private void union(int[] parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            parent[py] = px;
        }
    }
}
