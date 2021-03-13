package com.zoumx.algo.recall;

/**
 * 〈功能概述〉<br>
 *
 * @className: ZeroOrOneBugZmx
 * @package: com.zoumx.algo.recall
 * @author: admin
 * @date: 2020/2/29 21:12
 */
public class ZeroOrOneBugZmx {
    public static int maxW = Integer.MIN_VALUE; //存储背包中物品总重量的最大值

    public static void main(String[] args) {
        int [] item = {15,2,14,17,18,21,43,16,39,20};
        f(0, 0, item, 10, 100);
        System.out.println(maxW);
    }

    //每种选择都有二次，要么放入，要么不放入
    private static void f(int i, int cw, int[] item, int n, int w) {
        //如果已经选择完n次或者已经等于总得总量，那么我们返回
        //递归终止条件
        if (cw == w || i == n) {
            if (cw > maxW) {maxW = cw;}
            return;
        }
        //进行选择，不放入背包
        f(i+1,cw,item,n,w);
        //进行剪枝判断 进行优化，如当前放入背包大于总得重量，那么我们就直接返回
        if(cw + item[i] <= w) {
            //进行放入背包操作
            f(i+1,cw+item[i],item,n,w);
        }
    }
}
