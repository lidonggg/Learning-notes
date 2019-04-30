package com.lidong.javaops.algorithm.common.impl;

import com.lidong.javaops.algorithm.common.ArrayFactory;

/**
 * @author Ls J
 * @version 2019/4/29 15:03
 */
public class ArrayForSort implements ArrayFactory {
    public int[] createArray() {
        return new int[]{10, 1, 5, 3, 7, 5, 6, 3, 2, 8, 11, 9};
    }
}
