package com.lidong.algorithm.leetcode.easy.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 二进制手表（中等-401）
 * 中文链接：https://leetcode-cn.com/problems/binary-watch/
 * <p>
 * 问题描述：
 * 二进制手表顶部有 4 个 LED 代表小时（0-11），底部的 6 个 LED 代表分钟（0-59）。
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 * <p>
 * 示例:
 * 输入: n = 1
 * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * <p>
 * 注意事项:
 * 输出的顺序没有要求。
 * 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
 * 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
 *
 * @author ls J
 * @date 2020/5/18 11:01
 */
public class BinaryWatch401 {

    /**
     * 第一步：将小时和分钟依次从 0 到 3，4 到 9 编号，从 start 到 9 中选 num 个数，将结果放入 tmp 中
     * 第二步：将 tmp 中的结果转化成时间，注意 hour 不能大于 11，minute 不能大于 59
     *
     * @param num n
     * @return res
     */
    public List<String> readBinaryWatch(int num) {
        List<List<Integer>> tmp = new ArrayList<>();
        search(0, num, new ArrayList<>(), tmp);

        List<String> res = new ArrayList<>();
        for (List<Integer> list : tmp) {
            int hour = 0;
            int minute = 0;
            for (int n : list) {
                if (n <= 3) {
                    hour += 1 << n;
                } else {
                    minute += 1 << (n - 4);
                }
            }

            if (hour > 11 || minute > 59) {
                continue;
            }

            String m = minute < 10 ? "0" + minute : "" + minute;
            res.add(hour + ":" + m);
        }

        return res;
    }

    /**
     * 将小时和分钟依次从 0 到 3，4 到 9 编号，从 start 到 9 中选 num 个数，将结果放入 tmp 中
     *
     * @param start   start index
     * @param num     num leave
     * @param curList curList
     * @param tmp     res tmp list，存放为一的位置的索引
     */
    private void search(int start, int num, List<Integer> curList, List<List<Integer>> tmp) {
        if (num == 0) {
            tmp.add(new ArrayList<>(curList));
            return;
        }

        for (int i = start; i < 10; i++) {
            curList.add(i);
            // 当前位置为 1，继续遍历下一个数
            search(i + 1, num - 1, curList, tmp);
            // 回溯，当前位置为 0
            curList.remove(curList.size() - 1);
        }
    }

    /**
     * bitCount 解法
     * bitCount 实现的功能是计算一个（byte,short,char,int 统一按照 int 方法计算）int,long 类型的数值在二进制下“1”的数量。
     *
     * @param num n
     * @return res
     */
    public List<String> readBinaryWatch2(int num) {
        List<String> times = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            int hBitCountVal = Integer.bitCount(h);
            if (hBitCountVal > num) {
                continue;
            }
            for (int m = 0; m < 60; m++) {
                int mBitCountVal = Integer.bitCount(m);
                if (mBitCountVal > num) {
                    continue;
                }
                int bigCountVal = hBitCountVal + mBitCountVal;
                if (bigCountVal == num) {
                    times.add(String.format("%d:%02d", h, m));
                }
            }
        }
        return times;
    }

    /**
     * 方法三：暴力穷举
     * 因为目标结果不是很多，可以采用暴力法
     *
     * @param num n
     * @return res
     */
    public List<String> readBinaryWatch3(int num) {
        List<String> ans = new ArrayList<String>();
        // 第 i（i 从 0 开始）个子数组代表需要 i 个 n
        String[][] hstrs = {{"0"}, {"1", "2", "4", "8"}, {"3", "5", "6", "9", "10"}, {"7", "11"}};
        String[][] mstrs = {{"00"}, {"01", "02", "04", "08", "16", "32"}, {"03", "05", "06", "09", "10", "12", "17", "18", "20", "24", "33", "34", "36", "40", "48"}, {"07", "11", "13", "14", "19", "21", "22", "25", "26", "28", "35", "37", "38", "41", "42", "44", "49", "50", "52", "56"}, {"15", "23", "27", "29", "30", "39", "43", "45", "46", "51", "53", "54", "57", "58"}, {"31", "47", "55", "59"}};

        for (int i = 0; i <= Math.min(3, num); i++) {
            if (num - i > 5) {
                continue;
            }
            String[] hstr = hstrs[i];
            String[] mstr = mstrs[num - i];
            for (String s1 : hstr) {
                for (String s : mstr) {
                    ans.add(s1 + ":" + s);
                }
            }
        }
        return ans;
    }
}
