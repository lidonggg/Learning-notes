package com.lidong.algorithm.leetcode.easy;

/**
 * @author ls J
 * @date 2019/7/11 10:20 AM
 * 机器人能否返回原点（简单-657）
 * 问题描述：
 * 在二维平面上，有一个机器人从原点 (0, 0) 开始。给出它的移动顺序，判断这个机器人在完成移动后是否在 (0, 0) 处结束。
 * <p>
 * 移动顺序由字符串表示。字符 move[i] 表示其第 i 次移动。机器人的有效动作有 R（右），L（左），U（上）和 D（下）。如果机器人在完成所有动作后返回原点，则返回 true。否则，返回 false。
 * <p>
 * 注意：机器人“面朝”的方向无关紧要。 “R” 将始终使机器人向右移动一次，“L” 将始终向左移动等。此外，假设每次移动机器人的移动幅度相同。
 * <p>
 * 示例:
 * 输入: "UD"
 * 输出: true
 * 解释：机器人向上移动一次，然后向下移动一次。所有动作都具有相同的幅度，因此它最终回到它开始的原点。因此，我们返回 true。
 * <p>
 */
public class JudgeCircle657 {

    /**
     * 如果想要返回原点，那么机器人向上移动的次数 = 向下移动的次数，向左移动的次数 = 向右移动的次数
     *
     * @param moves str
     * @return boolean
     */
    public static boolean judgeCircle(String moves) {
        int ud = 0, rl = 0;
        for (int i = 0; i < moves.length(); ++i) {
            char c = moves.charAt(i);
            switch (c) {
                case 'U':
                    ud++;
                    break;
                case 'D':
                    ud--;
                    break;
                case 'R':
                    rl++;
                    break;
                case 'L':
                    rl--;
                    break;
                default:
                    break;
            }
        }

        return ud == 0 && rl == 0;
    }

    public static void main(String[] args) {
        System.out.println(judgeCircle("DDUURRLL"));
    }
}
