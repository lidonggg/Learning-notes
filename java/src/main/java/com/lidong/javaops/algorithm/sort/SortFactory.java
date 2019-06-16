package com.lidong.javaops.algorithm.sort;

/**
 * @author Ls J
 * @version 2019/4/29 15:40
 * 排序工厂
 */
public interface SortFactory<T> {

    /**
     * 排序
     *
     * @param arr 待排序数组
     */
    void sort(T arr);
}
