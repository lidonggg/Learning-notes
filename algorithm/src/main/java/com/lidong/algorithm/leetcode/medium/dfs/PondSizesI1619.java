package com.lidong.algorithm.leetcode.medium.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 水域大小（中等-面试题16_19）
 * 中文链接：https://leetcode-cn.com/problems/pond-sizes-lcci
 * <p>
 * 问题描述：
 * 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。
 * 由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
 * <p>
 * 示例：
 * 输入：
 * [
 * [0,2,1,0],
 * [0,1,0,1],
 * [1,1,0,1],
 * [0,1,0,1]
 * ]
 * 输出： [1,2,4]
 * <p>
 * 提示：
 * 0 < len(land) <= 1000
 * 0 < len(land[i]) <= 1000
 *
 * @author ls J
 * @date 2020/7/20 10:58
 */
public class PondSizesI1619 {

    private int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};

    private int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    /**
     * dfs
     * <p>
     * 执行用时：10 ms，在所有 Java 提交中击败了 90.47% 的用户
     * 内存消耗：74.3 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param land arr
     * @return int[]
     */
    public int[] pondSizes(int[][] land) {
        int m = land.length, n = land[0].length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (land[i][j] == 0) {
                    list.add(dfs(i, j, m, n, land));
                }
            }
        }
        int[] res = new int[list.size()];

        for (int i = 0; i < list.size(); ++i) {
            res[i] = list.get(i);
        }
        Arrays.sort(res);
        return res;
    }

    private int dfs(int x, int y, int m, int n, int[][] land) {
        int count = 1;
        // 每遍历到一个水域，就将当前元素标记为已遍历，赋值为 -1
        land[x][y] = -1;
        for (int i = 0; i < 8; ++i) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && land[nx][ny] == 0) {
                count += dfs(nx, ny, m, n, land);
            }
        }
        return count;
    }
}
