package com.lidong.algorithm.leetcode.medium.dfs;

/**
 * 朋友圈（中等-547）
 * 中文链接：https://leetcode-cn.com/problems/friend-circles
 * <p>
 * 问题描述：
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。
 * 如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 * <p>
 * 示例 1:
 * 输入:
 * [[1,1,0],
 * [1,1,0],
 * [0,0,1]]
 * 输出: 2
 * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
 * 第2个学生自己在一个朋友圈。所以返回2。
 * <p>
 * 示例 2:
 * 输入:
 * [[1,1,0],
 * [1,1,1],
 * [0,1,1]]
 * 输出: 1
 * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
 * <p>
 * 注意：
 * 1.N 在[1,200]的范围内。
 * 2.对于所有学生，有M[i][i] = 1。
 * 3.如果有M[i][j] = 1，则有M[j][i] = 1。
 *
 * @author ls J
 * @date 2020/7/9 13:52
 */
public class FriendCircles547 {

    private boolean[] visited;

    /**
     * dfs
     * 从每一个没有被访问过的人开始，去查找由它所能拓展到的其他所有人
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 99.96% 的用户
     * 内存消耗：40.8 MB，在所有 Java 提交中击败了 96.00% 的用户
     * <p>
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     *
     * @param matrix matrix
     * @return num
     */
    public int findCircleNum(int[][] matrix) {
        int n = matrix.length;

        if (n == 0) {
            return 0;
        }
        this.visited = new boolean[n];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                res++;
                dfs(i, n, matrix);
            }
        }

        return res;
    }

    private void dfs(int x, int n, int[][] matrix) {
        if (visited[x]) {
            return;
        }
        // 标记为已访问
        visited[x] = true;

        for (int i = 0; i < n; ++i) {
            // 如果当前两个人互为朋友，那么就去看下一个人的朋友圈
            if (matrix[x][i] == 1 || matrix[i][x] == 1) {
                dfs(i, n, matrix);
            }
        }
    }
}
