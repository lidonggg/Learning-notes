package com.lidong.java.io.scanner;

import java.util.Scanner;

/**
 * @author ls J
 * @date 2020/6/8 17:36
 */
public class ScannerTest {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        int m,n ;
        m = reader.nextInt();
        n = reader.nextInt();
        int[][] arr = new int[m][n];

        for (int i = 0;i <m;++i) {
            for(int j = 0; j <m;++j) {
                arr[i][j] = reader.nextInt();
            }
        }

        for (int i = 0;i <m;++i) {
            for(int j = 0; j <m;++j) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }
}
