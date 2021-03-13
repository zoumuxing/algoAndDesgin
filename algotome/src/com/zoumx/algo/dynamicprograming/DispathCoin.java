package com.zoumx.algo.dynamicprograming;

import java.util.ArrayList;

/**
 * 〈功能概述〉<br>
 *
 * @className: DispathCoin
 * @package: com.zoumx.algo.dynamicprograming
 * @author: admin
 * @date: 2020/3/5 16:29
 */
public class DispathCoin {


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
    static int min = 10000;
    private void allotCoinQuestion(int n, int w, int total, ArrayList al) {

        if(total == w) {
            if(n < min) {
                System.out.println(al.toString());
                min = n;
            }
            return;
        }else if(total > w) {
            return;
        }
        for (int i = 0; i < value.length; i++) {
            ArrayList arrayList = (ArrayList) al.clone();
            arrayList.add(value[i]);
            allotCoinQuestion(n + 1,w,total + value[i],arrayList);
        }
    }



    int coin = 0;
    /**
     * @Author zoumx
     * @Description //
     * @Date 18:08 2020/3/5
     * @Param [money]
     * @return int
     **/
    public int minCoins(int money) {
        if (money == 1 || money == 3 || money == 5) {
            return 1;
        }
        boolean [][] state = new boolean[money][money + 1];
        //该问题属于多阶段的动态规划问题，但是各阶段又是不一样的，可能是1，可能是3，可能是5
        //因此初始化各阶段的情况
        if (money >= 1) {
            state[0][1] = true;
        }
        if (money >= 3) {
            state[0][3] = true;
        }
        if (money >= 5) {
            state[0][5] = true;
        }
        //进行动态规划转移
        for (int i = 1; i < money; i++) {
            for (int j = 1; j <= money; j++) {
                //假如当前情况走了一步
                if (state[i - 1][j]) {
                    if (j + 1 <= money) {
                        state[i][j + 1] = true;
                    }
                    //假如当前情况走了三步
                    if (j + 3 <= money) {
                        state[i][j + 3] = true;
                    }
                    //假如当前情况走了五步
                    if (j + 5 <= money) {
                        state[i][j + 5] = true;
                    }
                    //输出最早走完的情况
                    if (state[i][money]) {
                        return i + 1;
                    }
                }
            }
        }
        return money;
    }

    /*public static void main(String[] args) {
        DispathCoin dispathCoin = new DispathCoin();
       *//* dispathCoin.allotCoinQuestion(0,9,0,new ArrayList());
        System.out.println(min);*//*
        int result = dispathCoin.minCoins(9);
        System.out.println(result);
        *//* int[] values = {1,3,5};
        minCoinCount(0,values,9,0);
        System.out.println(min_count);*//*
    }*/

    static int min_count = 1000000000;

    /**
     * @Author zoumx
     * @Description
     * @Date 17:39 2020/3/5
     * @Param [i, values, w, cw]
     * i硬币数量
     * values 银币数组
     * w 总额
     * cw 当前值
     * @return void
     **/
    private static void minCoinCount(int i, int[] values, int w, int cw) {
        if (cw == w || i == w) {
            if (cw == w && i < min_count) {
                min_count = i;
            }
            return;
        }
        for (int j = 0; j < values.length; j++) {
            if (cw + values[i] <= w) {
                minCoinCount(i+1,values,w,cw + values[i]);
            }
        }

    }

   /**
    * @Author zoumx
    * @Description //爬楼梯的思想进行思考，加入当前走一步，走三步，走五步
    * @Date 18:12 2020/3/5
    * @Param [money]
    * @return void
    **/
   //static int coinTotal = 0;
   private int minCoin(int money) {
       //分情况 先求最大的5块钱的余数，先分配多的
       //小于five


       //
       int one = minCoin(money-1);
       int three = minCoin(money-3);
       int five = minCoin(money-5);
       coin = 1 + Math.min(Math.min(one,three),five);
       System.out.println(coin);
       return coin;
   }



   /**
    * @Author zoumx
    * @Description //少于5块钱的情况
    * @Date 20:36 2020/3/5
    * @Param [money]
    * @return int
    **/
   private int lessFiveOfDispathCoin(int money) {
       //还剩一块钱的 还剩几次
       if(money == 1) {
           return 1;
       }
       //还剩二块钱的 还剩二次
       else if (money == 2) {
           return 2;
       }
       //还剩三块钱的 还剩一次
       else if (money == 3) {
           return 1;
       }
       //还剩四块钱的 还剩二次
       else if (money == 4) {
           return 2;
       }else if (money == 5) {
           return 1;
       }else {
           throw new RuntimeException("within five");
       }

   }

    public static void main(String[] args) {
        DispathCoin dispathCoin = new DispathCoin();
        dispathCoin.minCoin(9);
        System.out.println();
    }

    public static void minCoinDP(int money) {
       //定义一个状态数组
        // 分别找出剩余几块钱的要最少的数量
        int []state = new int[money+1];
        state[1] = 1;
        state[2] = 2;
        state[3] = 1;
        state[4] = 2;
        state[5] = 1;
        for (int i = 6; i <= money; i++) {
           state[i] = 1 + Math.min(Math.min(state[i-1],state[i-3]),state[i-9]);
        }
    }
}
