package com.lidong.algorithm.backtracking;

/**
 * 八皇后问题
 *
 * @author Ls j
 * @date 2020/1/8 19:52
 */
public class EightQueens {

    /**
     * 保存结果，值为每一行摆放的位置
     */
    private int[] result = new int[8];

    /**
     * 考察某一行
     *
     * @param row row
     */
    public void calcEightQueens(int row) {
        if (row == 8) {
            // TODO 打印，省略，也可以存到一个结果列表中，然后继续考察，找出所有情况
            return;
        }
        for (int i = 0; i < 8; ++i) {
            if (isOk(row, i)) {
                result[row] = i;
                calcEightQueens(row + 1);
            }
        }
    }

    /**
     * 考察某个位置是否可以，因为是从上往下依次进行每一行的填充，因此这里只需要比较正上方、左上方和右上方三种情况就行
     *
     * @param row    行
     * @param column 列
     * @return true if success
     */
    private boolean isOk(int row, int column) {
        // lu--左上 ru--右上
        int lu = column - 1, ru = column + 1;
        for (int i = row - 1; i >= 0; --i) {
            if (result[i] == column) {
                return false;
            }
            if (lu > 0 && result[i] == lu) {
                return false;
            }
            if (ru < 8 && result[i] == ru) {
                return false;
            }
            // 继续往上考察一行
            --lu;
            ++ru;
        }
        return true;
    }
}
