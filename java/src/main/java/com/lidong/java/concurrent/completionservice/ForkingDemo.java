package com.lidong.java.concurrent.completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author ls J
 * @date 2019/8/13 5:50 PM
 * 实现一个类似于 Dobbo 的 Forking Cluster 的功能
 * 并行地调用多个查询服务，只要有一个成功地返回了结果，则整个服务就可以返回了
 */
public class ForkingDemo {

    public static int getValueFromIp1() {
        return 1;
    }

    public static int getValueFromIp2() {
        return 2;
    }

    public static int getValueFromIp3() {
        return 3;
    }

    /**
     * 取值
     *
     * @return int
     */
    public static Integer getValue() {
        ExecutorService es = Executors.newFixedThreadPool(3);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(es);

        List<Future<Integer>> futures = new ArrayList<>();
        futures.add(cs.submit(ForkingDemo::getValueFromIp1));
        futures.add(cs.submit(ForkingDemo::getValueFromIp2));
        futures.add(cs.submit(ForkingDemo::getValueFromIp3));
        Integer res = 0;
        try {
            for (int i = 0; i < futures.size(); ++i) {
                res = cs.take().get();
                if (null != res) {
                    break;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            for (Future<Integer> future : futures) {
                future.cancel(true);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(getValue());
    }
}
