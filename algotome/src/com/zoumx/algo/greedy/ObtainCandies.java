package com.zoumx.algo.greedy;

import java.util.*;

/**
 * 分糖果，有m个糖果，n个小孩，每个小孩对糖果的需求需要大于等于该糖果的价值<br>
 * 例如 糖果的 价值为 m1 m2 m3 ..mm,小孩对糖果的需求为n1 n2 n3..nn.  n1>=m1 ，那么小孩n1可以获得m1糖果</br>
 * 假设，n>m,那么如何在满足有限的糖果的前提下，尽量分给较多的小孩
 *
 * @className: ObtainCandies
 * @package: com.zoumx.algo.greedy
 * @author: admin
 * @date: 2020/2/23 15:20
 */
public class ObtainCandies {

    public static void main(String[] args) {
        //小孩对糖果需求的价值
          List<Integer> m = Arrays.asList(3,4,5,7,8);
        //糖果的价值
        List<Integer> n = Arrays.asList(2,4,6,7);
        //先满足较小的需求价值，并且最好取恰好等于小孩的需求价值的糖果。这样别的小孩可以获取到价值较大的谈过，就能匹配到较多的小孩子


    }

}
