package com.lidong.algorithm.sort;

import com.lidong.algorithm.common.ArrayFactory;
import com.lidong.algorithm.common.impl.ArrayForSort;
import com.lidong.algorithm.util.ArrayUtil;

/**
 * @author Ls J
 * @date 2019/4/30 9:19 AM
 */
public class SortTest {

    protected static void sortTest(SortFactory sort){
        ArrayFactory arrayFactory = new ArrayForSort();
        int[] arr = arrayFactory.createArray();
        ArrayUtil.printArray(arr);
        sort.sort(arr);
        System.out.println("排序后的数组为：");
        ArrayUtil.printArray(arr);
    }
}
