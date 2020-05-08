package com.lidong.algorithm.util;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 自定义简单的加密
 *
 * @author Ls J
 * @date 2019/7/11 9:45 PM
 */
public class EncryptUtil {

    /**
     * 加密盐
     */
    private static String salt = "hfh1n5k";

    /**
     * 加密
     *
     * @param source
     * @return
     */
    public static String encode(String source) {
        char[] chars = (source + salt).toCharArray();
        int len = chars.length;
        Queue<Character> queue = new ArrayDeque<>();

        for (int i = 0; i < len; ++i) {
            queue.add(chars[len - i - 1]);
            if (i == len - 1) {
                break;
            }
            queue.add(queue.poll());
        }
        char[] res = new char[len];
        for (int i = res.length - 1; i >= 0; --i) {
            res[i] = queue.poll();
        }

        return String.valueOf(res);

    }

    /**
     * 解密
     *
     * @param source
     * @return
     */
    public static String decode(String source) {
        char[] chars = source.toCharArray();
        int len = chars.length;
        Queue<Character> queue = new ArrayDeque<>();
        for (int i = 0; i < len; ++i) {
            queue.offer(chars[i]);
        }
        for (int i = 0; i < len; i++) {
            chars[i] = queue.poll();
            if (i == len - 1) {
                break;
            }
            queue.add(queue.poll());
        }

        return String.valueOf(chars).substring(0, len - salt.length());
    }

    public static void main(String[] args) {
        System.out.println(encode("anfh31"));
        System.out.println(decode("a5nffnhh3k11h"));
    }
}
