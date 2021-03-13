package com.zoumx.algo.dynamicprograming;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈功能概述〉<br>
 *
 * @className: Test
 * @package: com.zoumx.algo.dynamicprograming
 * @author: admin
 * @date: 2020/3/5 14:21
 */
public class Test {


    /**
     * @Author zoumx
     * @Description //TODO admin
     * @Date 15:00 2020/3/5
     * @Param [matrix]
     * @return int
     **/
    public int yanghuiTriangle(int[][] matrix) {
        int[][] state = new int[matrix.length][matrix.length];
        state[0][0] = matrix[0][0];
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == 0) {
                    state[i][j] = state[i - 1][j] + matrix[i][j];
                }
                else if (j == matrix[i].length - 1) {
                    state[i][j] = state[i - 1][j - 1] + matrix[i][j];
                }
                else {
                    int top1 = state[i - 1][j - 1];
                    int top2 = state[i - 1][j];
                    state[i][j] = Math.min(top1, top2) + matrix[i][j];
                }
            }
        }
        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < matrix[matrix.length - 1].length; i++) {
            int distance = state[matrix.length - 1][i];
            if (distance < minDis) {
                minDis = distance;
            }
        }
        return minDis;
    }

    public static void main(String[] args) {
        Test test = new Test();
        int[][] matrix = {{5},{7,8},{2,3,4},{4,9,6,1},{2,7,9,4,5}};
        int min = test.yanghuiTriangle(matrix);
        System.out.println(min);
    }


    /**
     * @Author zoumx
     * @Description 把三角形 从上到下的路径值 填充到一个二维数组中。每一步记录状态，通过上一步状态，
     * 推算出下一步状态
     * @Date 15:05 2020/3/5
     * @Param [matrix]
     * @return int
     **/
    public int yanghuiTriangle1(int[][] matrix) {
        int[][] state = new int[matrix.length][matrix.length];
        //初始化最初值
         state[0][0] = matrix[0][0];
         //那么开始动态规划转移
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length ; j++) {
                //判断三角形各种情况
                //第一列的时候
                if(j == 0) {
                    state[i][j] = state[i-1][j] + matrix[i][j];
                }else if(j == matrix[i].length) {
                    state[i][j] = state[i-1][j -1] + matrix[i][j];
                }else {
                    int leftTop = state[i-1][j-1] + matrix[i][j];
                    int rightTop = state[i-1][j] + matrix[i][j];
                    state[i][j] = Math.max(leftTop,rightTop);
                }
            }
        }
        //打印最后一行的所有值，进行比较
        int min = 0;
        int [] tmp = matrix[matrix.length-1];
        for (int i = 0; i <tmp.length; i++) {
            if(tmp[i] < min) {
                min = tmp[i];
            }
        }
       return min;
    }

    /**
     * @Author zoumx
     * @Description //硬币找零问题，假设有几种面值不同硬币，支付某个金额，
     * 求最少需要几种硬币支付
     * @Date 16:07 2020/3/5
     * @Param []
     * @return void
     **/
   /* int n = 0;
    int value[] = {1,3,5};
    int w = 9;*/

    int value[] = {1,3,5};
    int min = 10000;
    private void allotCoinQuestion(int n, int w, int total, ArrayList al) {

        if(total == w) {
            if(n < min) {
                n = min;
            }
            return;
        }else if(total > w) {
            return;
        }
        for (int i = 0; i < value.length; i++) {
             ArrayList arrayList = (ArrayList) al.clone();
             arrayList.add(value[i]);
             allotCoinQuestion(i + 1,w,total + value[i],arrayList);
        }
    }

}
