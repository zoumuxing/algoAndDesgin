package com.zoumx.algo.recall;

/**
 * 01被包问题，假设有N个物品，每个物品的重量都不一样。在不超过总重量为Wkg的情况下，尽可能的装进背包的重量，每种物品要么装
 * 要么不装，所以叫做O1背包问题<br>
 *
 * @className: ZeroOrOneBug
 * @package: com.zoumx.algo.recall
 * @author: admin
 * @date: 2020/2/29 20:14
 */
public class ZeroOrOneBug {

    public static int maxW = Integer.MIN_VALUE; //存储背包中物品总重量的最大值

    public static void main(String[] args) {
        int [] item = {15,2,14,17,18,21,43,16,39,20};
        f(0, 0, item, 10, 100);
        System.out.println(maxW);
    }

    // cw 表示当前已经装进去的物品的重量和；i 表示考察到哪个物品了；
    // w 背包重量；items 表示每个物品的重量；n 表示物品个数
    // 假设背包可承受重量 100，物品个数 10，物品重量存储在数组 a 中，那可以这样调用函数：
    // f(0, 0, a, 10, 100)
    public static void f(int i, int cw, int[] items, int n, int w) {
        // cw==w 表示装满了 ;i==n 表示已经考察完所有的物品
        if (cw == w || i == n) {
            if (cw > maxW) {maxW = cw;}
            return;
        }
        //当前物品不装进背包
        f(i+1, cw, items, n, w);
        // 已经超过可以背包承受的重量的时候，就不要再装了
        if (cw + items[i] <= w) {
            //当前物品装进背包
            f(i+1,cw + items[i], items, n, w);
        }

    }
}
