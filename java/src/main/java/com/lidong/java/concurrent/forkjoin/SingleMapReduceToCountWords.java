package com.lidong.java.concurrent.forkjoin;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 单机版 MapReduce
 *
 * @author ls J
 * @date 2019/8/15 3:17 PM
 */
public class SingleMapReduceToCountWords extends RecursiveTask<Map<String, Long>> {

    private String[] fc;
    private int start, end;

    SingleMapReduceToCountWords(String[] fc, int start, int end) {
        this.fc = fc;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Map<String, Long> compute() {
        if (end - start == 1) {
            return calc(fc[start]);
        } else {
            // 利用二分法递归分解
            int mid = (start + end) / 2;
            SingleMapReduceToCountWords smr1 = new SingleMapReduceToCountWords(fc, start, mid);
            smr1.fork();
            SingleMapReduceToCountWords smr2 = new SingleMapReduceToCountWords(fc, mid, end);
            // 计算子任务，并返回合并的结果
            return merge(smr2.compute(), smr1.join());
        }
    }

    /**
     * 结果合并
     *
     * @param r1 r1
     * @param r2 r2
     * @return res
     */
    private Map<String, Long> merge(Map<String, Long> r1, Map<String, Long> r2) {
        Map<String, Long> result = new HashMap<>(r1);
        // 合并结果
        r2.forEach((k, v) -> result.merge(k, v, (a, b) -> a + b));
        return result;
    }

    /**
     * 统计单词数量
     *
     * @param line 每一行数据
     * @return map
     */
    private Map<String, Long> calc(String line) {
        Map<String, Long> result = new HashMap<>();
        // 分割单词
        String[] words = line.split("\\s+");
        // 统计单词数量
        for (String w : words) {
            Long v = result.get(w);
            if (null != v) {
                result.put(w, v + 1);
            } else {
                result.put(w, 1L);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] fc = {"hello world",
                "hello me",
                "hello fork",
                "hello join",
                "fork join in world"};
        // 创建 ForkJoin 线程池
        ForkJoinPool fjp = new ForkJoinPool(3);
        // 创建任务
        SingleMapReduceToCountWords smr = new SingleMapReduceToCountWords(fc, 0, fc.length);
        // 启动任务
        Map<String, Long> result = fjp.invoke(smr);
        // 输出结果
        result.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
