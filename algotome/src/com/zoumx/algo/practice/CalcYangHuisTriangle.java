package com.zoumx.algo.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * 计算杨辉三角
 * 1.什么是杨辉三角 有啥特性
 *                 1                 1
 *               1   1               2
 *             1   2   1             3
 *           1  3    3   1           4
 *         1  4    6  4    1         5
 **/
public class CalcYangHuisTriangle {
    /**
     * @param n: a Integer
     * @return: the first n-line Yang Hui's triangle
     */
    public List<List<Integer>> calcYangHuisTriangle(int n) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < n; i++){
            List<Integer> list = new ArrayList<>();

            for(int j = 0; j <= i; j++){
                if(j ==0 || j == i){
                    list.add(1);
                }else{
                    list.add(res.get(i-1).get(j) + res.get(i-1).get(j-1));
                }
            }
            res.add(list);
        }
        return res;
    }


    /**
     * @param n: a Integer
     * @return: the first n-line Yang Hui's triangle
     */
    public List<List<Integer>> calcYangHuisTriangle1(int n) {
        List<List<Integer> > res = new ArrayList<>();
        int i, j;
        if (n == 0) {
            return res;
        }
        for (i = 0; i < n; ++i) {
            List<Integer> t = new ArrayList<Integer>();
            t.add(1);
            for (j = 1; j < i; ++j) {
                t.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
            }
            if (i > 0) {
                t.add(1);
            }
            res.add(t);
        }
        return res;
    }


    /**
     * @param n: a Integer
     * @return: the first n-line Yang Hui's triangle
     */
    public List<List<Integer>> calcYangHuisTriangle2(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n < 0) {
            return res;
        }

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }

        for (int i = 2; i < n; i++) {
            for (int j = 1; j < n - 1; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        for (int j = 0; j < n; j++) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i <= j; i++) {
                list.add(dp[j][i]);
            }
            res.add(list);
        }
        System.out.println(res);
        return res;
    }

}
