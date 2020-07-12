package com.lidong.algorithm.leetcode.medium.mergefindset;

/**
 * 等式方程的可满足性（中等-990）
 * 中文链接：https://leetcode-cn.com/problems/satisfiability-of-equality-equations
 * <p>
 * 问题描述：
 * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。
 * 在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
 * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 
 * <p>
 * 示例 1：
 * 输入：["a==b","b!=a"]
 * 输出：false
 * 解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
 * <p>
 * 示例 2：
 * 输入：["b==a","a==b"]
 * 输出：true
 * 解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
 * <p>
 * 示例 3：
 * 输入：["a==b","b==c","a==c"]
 * 输出：true
 * <p>
 * 示例 4：
 * 输入：["a==b","b!=c","c==a"]
 * 输出：false
 * <p>
 * 示例 5：
 * 输入：["c==c","b==d","x!=z"]
 * 输出：true
 *  
 * 提示：
 * - 1 <= equations.length <= 500
 * - equations[i].length == 4
 * - equations[i][0] 和 equations[i][3] 是小写字母
 * - equations[i][1] 要么是 '='，要么是 '!'
 * - equations[i][2] 是 '='
 *
 * @author Ls J
 * @date 2020/7/12 5:06 PM
 */
public class SatisfiabilityOfEqualityEquations990 {

    /**
     * 利用等式构造并查集
     *
     * 执行用时：1 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.1 MB，在所有 Java 提交中击败了 16.67% 的用户
     *
     * @param equations equations
     * @return res
     */
    public boolean equationsPossible(String[] equations) {
        int[] parent = new int[26];

        for (int i = 0; i < 26; ++i) {
            parent[i] = i;
        }
        // 根据相等关系构造并查集
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                // 左变量
                int lf = equation.charAt(0) - 'a';
                // 右变量
                int rt = equation.charAt(3) - 'a';
                union(parent, lf, rt);
            }
        }
        for (String equation : equations) {
            if (equation.charAt(1) != '=') {
                // 左变量
                int lf = equation.charAt(0) - 'a';
                // 右变量
                int rt = equation.charAt(3) - 'a';
                // 如果两个节点找到了同一个代表节点，那说明出现了矛盾
                if (find(parent, lf) == find(parent, rt)) {
                    return false;
                }
            }
        }
        return true;
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }

    private void union(int[] parent, int x, int y) {
        parent[find(parent, x)] = find(parent, y);
    }
}
