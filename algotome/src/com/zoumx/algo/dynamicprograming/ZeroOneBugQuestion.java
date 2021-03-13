package com.zoumx.algo.dynamicprograming;

/**
 * 0,1背包问题，求解在限制值的情况下，能装进最大重量<br>
 *
 * @className: ZeroOneBugQuestion
 * @package: com.zoumx.algo.dynamicprograming
 * @author: admin
 * @date: 2020/3/2 15:06
 */
public class ZeroOneBugQuestion {

    // 结果放到maxW中
    private int maxW = Integer.MIN_VALUE;
    // 物品重量
    private int[] weight = {2,2,4,6,3};
    // 物品个数
    private int n = 5;
    // 背包承受的最大重量
    private int w = 9;
    // 备忘录，默认值false
    private boolean[][] mem = new boolean[5][10];
    // 调用f(0, 0)
    /**
     * @Author zoumx
     * @Description 如何对回溯算法进行改进呢，增加一个备忘录，记录已经放入过物品的状态，
     * 例如之前我已经知道放入 3号物品是8kg，没必要在继续判断之后的放与不放
     * 如果已经放入过，就不需要再走递归，减少递归次数，提高性能
     * @Date 15:10 2020/3/2
     * @Param [i, cw]
     * @return void
     **/
    public void f(int i, int cw) {
        // cw==w表示装满了，i==n表示物品都考察完了
        if (cw == w || i == n) {
            if (cw > maxW) {maxW = cw;}
            return;
        }
        // 重复状态
        if (mem[i][cw]) {
            return;
        }
        // 记录(i, cw)这个状态
        mem[i][cw] = true;
        // 选择不装第i个物品
        f(i+1, cw);
        if (cw + weight[i] <= w) {
            f(i+1,cw + weight[i]); // 选择装第i个物品
        }
    }

    public static void main(String[] args) {
        ZeroOneBugQuestion zeroOneBugQuestion = new ZeroOneBugQuestion();
        zeroOneBugQuestion.f(0,0);
    }
}
