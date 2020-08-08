package com.lidong.algorithm.leetcode.medium.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 复原IP地址（中等-93）
 * 中文链接：https://leetcode-cn.com/problems/restore-ip-addresses
 * <p>
 * 问题描述：
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 * <p>
 * 示例:
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 * @author Ls J
 * @date 2020/8/9 1:08 AM
 */
public class RestoreIPAddresses93 {

    static final int SEG_COUNT = 4;
    List<String> ans = new ArrayList<String>();
    int[] segments;

    /**
     * 执行用时：1 ms，在所有 Java 提交中击败了 99.83% 的用户
     * 内存消耗：38.4 MB，在所有 Java 提交中击败了 93.57% 的用户
     *
     * @param s s
     * @return list
     */
    public List<String> restoreIpAddresses(String s) {
        segments = new int[SEG_COUNT];
        dfs(s, 0, 0);
        return ans;
    }

    /**
     * 递归求解
     *
     * @param s        s
     * @param segId    表示当前第几段
     * @param segStart 当前段的开始索引
     */
    public void dfs(String s, int segId, int segStart) {
        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
        if (segId == SEG_COUNT) {
            if (segStart == s.length()) {
                StringBuilder ipAddr = new StringBuilder();
                for (int i = 0; i < SEG_COUNT; ++i) {
                    ipAddr.append(segments[i]);
                    if (i != SEG_COUNT - 1) {
                        ipAddr.append('.');
                    }
                }
                ans.add(ipAddr.toString());
            }
            return;
        }

        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (segStart == s.length()) {
            return;
        }

        // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1);
        }

        // 一般情况，枚举每一种可能性并递归
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 0xFF) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                break;
            }
        }
    }
}
