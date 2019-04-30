package com.lidong.javaops.algorithm.common.impl;

import com.lidong.javaops.algorithm.common.ArrayFactory;

/**
 * @author Ls J
 * @version 2019/4/29 15:04
 */
public class ArrayForSearch implements ArrayFactory {

    @Override
    public int[] createArray() {
        return new int[]{5, 5, 5, 6, 8, 9, 10, 10, 12};
    }
}
