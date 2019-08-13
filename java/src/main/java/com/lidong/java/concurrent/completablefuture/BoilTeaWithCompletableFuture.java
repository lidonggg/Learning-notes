package com.lidong.java.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author ls J
 * @date 2019/8/6 9:33 AM
 */
public class BoilTeaWithCompletableFuture {

    public static void boilTea() {

        CompletableFuture<Void> cf1 = CompletableFuture.runAsync(() -> {
            System.out.println("t1：洗水壶======");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("t1：烧开水======");
            sleep(15, TimeUnit.SECONDS);
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("t2：洗茶壶======");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("t2：洗茶杯======");
            sleep(2, TimeUnit.SECONDS);

            System.out.println("t2：拿茶叶======");
            sleep(1, TimeUnit.SECONDS);

            return "龙井";
        });

        CompletableFuture<String> t3 = cf1.thenCombine(cf2, (tt, tea) -> {
            System.out.println("t3：拿到茶叶" + tea);
            System.out.println("t3：泡茶=======");
            return "上茶：" + tea;
        });

        System.out.println(t3.join());
    }

    private static void sleep(int t, TimeUnit unit) {
        try {
            unit.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        boilTea();
    }
}
