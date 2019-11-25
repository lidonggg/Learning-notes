package com.lidong.algorithm.thetest;

/**
 * @author Ls J
 * @date 2019/10/30 10:15 PM
 */
public class SingleTon {

    public static int count1;

    public static int count2 = 0;
    private static SingleTon singleTon = new SingleTon();

    private SingleTon(){
        count1++;
        count2++;
    }

    public static SingleTon getInstance() {
        return singleTon;
    }

    public static void main(String[] args) {
        SingleTon singleTon = SingleTon.getInstance();

        System.out.println(SingleTon.count1);
        System.out.println(SingleTon.count2);
    }
}
