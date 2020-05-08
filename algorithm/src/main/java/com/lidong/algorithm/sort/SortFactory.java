package com.lidong.algorithm.sort;

/**
 * 排序工厂
 *
 * @author Ls J
 * @version 2019/4/29 15:40
 */
public interface SortFactory<T> {

    /**
     * 排序
     *
     * @param arr 待排序数组
     */
    void sort(T arr);
}
