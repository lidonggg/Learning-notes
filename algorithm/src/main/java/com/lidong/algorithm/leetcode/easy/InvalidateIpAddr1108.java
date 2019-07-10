package com.lidong.algorithm.leetcode.easy;

/**
 * @author ls J
 * @date 2019/7/10 2:19 PM
 * IP地址无效化（简单-1108）
 * 问题描述：
 * 给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。
 * 所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."。
 * 给出的IP地址有效
 */
public class InvalidateIpAddr1108 {

    /**
     * 使用String的替换方法
     *
     * @param address ip
     * @return str
     */
    public static String invalidateIpAddr(String address) {
        return address.replaceAll("\\.", "[.]");
    }

    /**
     * 通过遍历的方法
     *
     * @param address ip
     * @return str
     */
    public static String invalidateIpAddr2(String address) {
        StringBuilder sb = new StringBuilder(String.valueOf(address.charAt(0)));
        for (int i = 1; i < address.length(); ++i) {
            if (address.charAt(i) != '.') {
                sb.append(address.charAt(i));
            } else {
                sb.append("[.]");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(invalidateIpAddr2("10.1.1.1"));
    }
}
