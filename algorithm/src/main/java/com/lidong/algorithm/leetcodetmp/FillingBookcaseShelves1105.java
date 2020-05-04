package com.lidong.algorithm.leetcodetmp;

/**
 * @author Ls J
 * @date 2020/5/4 10:56 AM
 *
 *
 *
 * 动态规划
 *
 *
 *
 * 填充书架（中等-1105）
 * 中文链接：https://leetcode-cn.com/problems/filling-bookcase-shelves/
 * 问题描述：
 * 附近的家居城促销，你买回了一直心仪的可调节书架，打算把自己的书都整理到新的书架上。
 * 你把要摆放的书 books 都整理好，叠成一摞：从上往下，第 i 本书的厚度为 books[i][0]，高度为 books[i][1]。
 * <strong>按顺序</strong>将这些书摆放到总宽度为 shelf_width 的书架上。
 * 先选几本书放在书架上（它们的厚度之和小于等于书架的宽度 shelf_width），然后再建一层书架。重复这个过程，直到把所有的书都放在书架上。
 * 需要注意的是，在上述过程的每个步骤中，摆放书的顺序与你整理好的顺序相同。 例如，如果这里有 5 本书，那么可能的一种摆放情况是：第一和第二本书放在第一层书架上，第三本书放在第二层书架上，第四和第五本书放在最后一层书架上。
 * 每一层所摆放的书的最大高度就是这一层书架的层高，书架整体的高度为各层高之和。
 * 以这种方式布置书架，返回书架整体可能的最小高度。
 * <p>
 * 示例：
 * 输入：books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
 * 输出：6
 * 解释：
 * 3 层书架的高度和为 1 + 3 + 2 = 6 。
 * 第 2 本书不必放在第一层书架上。
 * <p>
 * 提示：
 * 1 <= books.length <= 1000
 * 1 <= books[i][0] <= shelf_width <= 1000
 * 1 <= books[i][1] <= 1000
 */
public class FillingBookcaseShelves1105 {

    private int[][] books;

    private int shelfWidth;

    private int result = Integer.MAX_VALUE;

    /**
     * 动态规划
     *
     * dp[i] = min()
     *
     * @param books      books
     * @param shelfWidth shelfWidth
     * @return res
     */
    public int minHeightShelves(int[][] books, int shelfWidth) {
        if (books.length == 0) {
            return 0;
        }

        int[] dp = new int[books.length + 1];



        return 0;
    }

    /**
     * 方法二：递归回溯
     *
     * @param books      books
     * @param shelfWidth shelf_width
     * @return min height
     */
    public int minHeightShelves1(int[][] books, int shelfWidth) {
        this.books = books;
        this.shelfWidth = shelfWidth;
        recurse(1, books[0][1], books[0][1], shelfWidth - books[0][0]);
        return result;
    }

    /**
     * 采用递归回溯的方法，会超时
     * 针对每本书在当前层都考虑放还是不放
     * 如果不放，则添加一层，放入下一层
     * 如果放，则判断当前层剩余宽度是否够用，如果够用，则放进去，计算当前本层高度，否则放入下一层
     *
     * @param curIdx      当前书本索引
     * @param totalHeight 当前书架已有高度，此时 books[curIdx] 没有放入
     * @param curHeight   当前层已有高度，此时 books[curIdx] 没有放入
     * @param curWidth    当前层剩余宽度，此时 books[curIdx] 没有放入
     */
    private void recurse(int curIdx, int totalHeight, int curHeight, int curWidth) {
        // 剪枝，递归结束
        if (totalHeight >= result) {
            return;
        }
        // 遍历结束，递归结束
        if (curIdx == books.length) {
            // 如果 totalHeight 较小，则赋值给 result
            if (result > totalHeight) {
                result = totalHeight;
            }
            return;
        }

        int curbH = books[curIdx][1], curbW = books[curIdx][0];
        // 当前层容不下它，则新增一层
        if (curbW > curWidth) {
            recurse(curIdx + 1, totalHeight + curbH, curbH, shelfWidth - curbW);
        } else {
            // 放入当前层，首先调整当前层的高度
            int newCurHeight = curHeight > curbH ? curHeight : curbH;
            recurse(curIdx + 1, totalHeight - curHeight + newCurHeight, newCurHeight, curWidth - curbW);
            // 放入下一层
            recurse(curIdx + 1, totalHeight + curbH, curbH, shelfWidth - curbW);
        }
    }

    public static void main(String[] args) {
        FillingBookcaseShelves1105 fbs = new FillingBookcaseShelves1105();

        int[][] books = new int[][]{
                {1, 1},
                {2, 3},
                {2, 3},
                {1, 1},
                {1, 1},
                {1, 1},
                {1, 2},
                {1, 1}
        };
        System.out.println(5 == fbs.minHeightShelves1(books, 5));
    }
}
