package com.zoumx.algo.recall;

/**
 * 背包问题，有n个物品，每个物品重量都不一样，
 * 在满足限制的重量w下，装入背包中价值最大<br>
 *
 * @className: ZeroOrOneBagValue
 * @package: com.zoumx.algo.recall
 * @author: admin
 * @date: 2020/3/2 19:39
 */
public class ZeroOrOneBagValue {

    private int maxV = Integer.MIN_VALUE; // 结果放到maxV中
    private int[] items = {2,2,4,6,3};  // 物品的重量
    private int[] value = {3,4,8,9,6}; // 物品的价值
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量
    public void f(int i, int cw, int cv) { // 调用f(0, 0, 0)
        if (cw == w || i == n) { // cw==w表示装满了，i==n表示物品都考察完了
            if (cv > maxV) {
                maxV = cv;
            }
            return;
        }
        f(i+1, cw, cv); // 选择不装第i个物品
        if (cw + items[i] <= w) {
              f(i+1,cw+items[i], cv+value[i]); // 选择装第i个物品
        }
    }
}
