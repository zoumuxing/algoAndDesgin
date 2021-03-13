package com.zoumx.algo.dynamicprograming;

/**
 * 0,1背包问题，求解在限制值的情况下，能装进最大重量<br>
 *
 * @className: ZeroOneBugQuestion
 * @package: com.zoumx.algo.dynamicprograming
 * @author: admin
 * @date: 2020/3/2 15:06
 */
public class ZeroOneBugDyProQuestion {


   // weight:物品重量，n:物品个数，w:背包可承载重量
    public int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w+1]; // 默认值false
        states[0][0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划状态转移
            for (int j = 0; j <= w; ++j) {// 不把第i个物品放入背包
                if (states[i-1][j] == true)  {
                    states[i][j] = states[i-1][j];
                }
            }
            for (int j = 0; j <= w-weight[i]; ++j) {//把第i个物品放入背包
                if (states[i-1][j]==true) {
                    states[i][j+weight[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[n-1][i] == true) {
                return i;
            }
        }
        return 0;
    }


    /**
     * @Author zoumx
     * @Description //动态规划改良版，只用一维数组，实际上我们只需要关注放入背包的场景
     * @Date 17:38 2020/3/2
     * @Param [items, n, w]
     * @return int
     **/
    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w+1]; // 默认值false
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w-items[i]; j >= 0; --j) {//把第i个物品放入背包
                if (states[j]==true) {
                    states[j+items[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true) {
                return i;
            }
        }
        return 0;
    }


    /**
     * @Author zoumx
     * @Description //再满足满减条件时，最大程度的选择价值商品
     * @Date 20:23 2020/3/2
     * @Param [items, n, w]
     * @return void
     **/
    // items商品价格，n商品个数, w表示满减条件，比如200
    public static void double11advance(int[] items, int n, int w) {
        boolean[][] states = new boolean[n][3*w+1];//超过3倍就没有薅羊毛的价值了
        states[0][0] = true;  // 第一行的数据要特殊处理
        if (items[0] <= 3*w) {
            states[0][items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = 0; j <= 3*w; ++j) {// 不购买第i个商品
                if (states[i-1][j] == true) {
                    states[i][j] = states[i-1][j];
                }
            }
            for (int j = 0; j <= 3*w-items[i]; ++j) {//购买第i个商品
                if (states[i-1][j]==true) {
                    states[i][j+items[i]] = true;
                }
            }
        }

        int j;
        for (j = w; j < 3*w+1; ++j) {
            if (states[n-1][j] == true) {
                break; // 输出结果大于等于w的最小值
            }
        }
        if (j == 3*w+1) {
            return; // 没有可行解
        }
        for (int i = n-1; i >= 1; --i) { // i表示二维数组中的行，j表示列
            //一个一个往前找，找到符合的商品而已
            if(j-items[i] >= 0 && states[i-1][j-items[i]] == true) {
                System.out.print(items[i] + " "); // 购买这个商品
                j = j - items[i];
            } // else 没有购买这个商品，j不变。
        }
        if (j != 0) {
            System.out.print(items[0]);
        }
    }



    /**
     * @Author zoumx
     * @Description //背包问题变种，动态规划解决方法
     * @Date 19:38 2020/3/2
     * @Param [weight, value, n, w]
     * @return int
     **/
    public static int knapsack3(int[] weight, int[] value, int n, int w) {
        int[][] states = new int[n][w+1];
        for (int i = 0; i < n; ++i) { // 初始化states
            for (int j = 0; j < w+1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        if (weight[0] <= w) {
            states[0][weight[0]] = value[0];
        }
        for (int i = 1; i < n; ++i) { //动态规划，状态转移
            for (int j = 0; j <= w; ++j) { // 不选择第i个物品
                if (states[i-1][j] >= 0) {
                    states[i][j] = states[i-1][j];
                }
            }
            for (int j = 0; j <= w-weight[i]; ++j) { // 选择第i个物品
                if (states[i-1][j] >= 0) {
                    int v = states[i-1][j] + value[i];
                    if (v > states[i][j+weight[i]]) {
                        states[i][j+weight[i]] = v;
                    }
                }
            }
        }
        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[n-1][j] > maxvalue) {
                maxvalue = states[n-1][j];
            }
        }
        return maxvalue;
    }


    public static void main(String[] args) {
        ZeroOneBugDyProQuestion zeroOneBugQuestion = new ZeroOneBugDyProQuestion();
        int[] weight = {2,2,4,6,3};
        int n = 5;
        int w = 9;
        zeroOneBugQuestion.knapsack(weight,5,9);
    }
}
