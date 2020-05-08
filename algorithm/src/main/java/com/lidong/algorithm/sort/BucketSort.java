package com.lidong.algorithm.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 桶排序，时间复杂度O(n)，稳定的，非原地排序
 * 将数据分成n个桶，每个桶代表不同范围，把相应的数据插入到每个桶中，然后每个桶分别排序，最后再合并
 * 适合数据量比较大的场景，内存加载不了需要在外部磁盘中进行的外部排序等；数据分布也最好要比较均匀，否则极端情况下时间复杂度会退化成O(nlogn)
 * 下面是最基本的桶排序，当某个桶中的元素较多的时候，可以再次递归利用桶排序进一步划分
 *
 * @author Ls J
 * @date 2019/4/30 9:14 AM
 */
public class BucketSort implements SortFactory<int[]> {

    @Override
    public void sort(int[] arr) {
        bucketSort(arr);
    }

    private static void bucketSort(int[] arr) {
        int len = arr.length;

        // 获取数组中的最大值和最小值，用于作为划分桶的依据
        int min = arr[0], max = arr[0];
        for (int i = 1; i < len; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        // b代表桶的数量，每个桶的区间范围就是(max-min)/b
        // b的取法要有规则，影响到下面的分区算法
        int b = (max - min) / len + 1;

        System.out.println("b:" + b);

        // 使用动态数组来表示每个桶
        List<ArrayList<Integer>> bucketArr = new ArrayList<>(b);
        // 初始化每个桶
        for (int i = 0; i < b; i++) {
            bucketArr.add(new ArrayList<>());
        }

        // 将每个元素放入桶中，分区
        for (int anArr : arr) {
            // 根据b的取值方法获取当前元素所应该在的桶的位置
            // (max - min) / b 代表每个区间的范围
            // slot的值等于当前元素与最小值的差值处以区间跨度，+1是为了防止最后一个区间范围不到((max - min) / b)
            int slot = (anArr - min) / ((max - min) / b + 1);
            // 插入到对应的桶中
            bucketArr.get(slot).add(anArr);
        }

        // 对每个桶进行排序
        /// 这里的排序算法可以自行优化，比如说使用其他的一些排序算法等，这里简化了一下，用了java集合框架
        for (ArrayList<Integer> aBucketArr : bucketArr) {
            Collections.sort(aBucketArr);
        }

        // 将桶中元素全部取出来并放入 arr 中输出
        int index = 0;
        for (ArrayList<Integer> bucket : bucketArr) {
            for (int data : bucket) {
                arr[index++] = data;
            }
        }
    }

    public static void main(String[] args) {
        SortTest.sortTest(new BucketSort());
    }
}
