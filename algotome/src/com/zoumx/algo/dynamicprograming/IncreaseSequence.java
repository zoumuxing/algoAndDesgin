package com.zoumx.algo.dynamicprograming;

import java.util.Arrays;

/**
 * 求最大的递增的序列长度，例如 2，9,3,6,5,7 最大递增长度为 2 3  5 7
 * 所以增大递增序列为4
 * <br>
 * @className: Lwst
 * @package: com.zoumx.algo.dynamicprograming
 * @author: admin
 * @date: 2020/3/7 21:41
 */
public class IncreaseSequence {


    private static int MAX_INCREASE_SEQUENCE_LENGTH = Integer.MIN_VALUE;

   /**
    /** @Author zoumx
    * @Description //TODO admin
    * @Date 22:06 2020/3/7
    * @Param [sequenceArray, len, n]序列，当前遍历最大递增序列长度，遍历长度
    * @return void
    **//*
    private static void getMaxIncreaseSequenceLength(int[] sequenceArray,int i,int len) {
        if(i == sequenceArray.length - 1) {
            System.out.println(len + 1 );
            return;
        }
        if(sequenceArray[i + 1] > sequenceArray [i]) {
            getMaxIncreaseSequenceLength(sequenceArray,i + 1,len + 1);
        }else {
            getMaxIncreaseSequenceLength(sequenceArray,i + 1,len);
        }

    }*/

    /**
     * @Author zoumx
     * @Description //递归解法
     * @Date 23:43 2020/3/7
     * @Param [nums]
     * @return int
     **/
    public static int lengthOfLIS(int[] nums) {
        return lengthofLIS(nums, Integer.MIN_VALUE, 0);
    }

    public static int lengthofLIS(int[] nums, int prev, int curpos) {
        if (curpos == nums.length) {
            return 0;
        }
        int taken = 0;
        //如果当前值大于上个值，就把当前值当做上个值
        if (nums[curpos] > prev) {
            //然后我们长度还要加1
            taken = 1 + lengthofLIS(nums, nums[curpos], curpos + 1);
        }
        //然后我们依然把上个值传进去
        int nottaken = lengthofLIS(nums, prev, curpos + 1);
        //这二种情况进行比较
        return Math.max(taken, nottaken);
    }


    /**
     * @Author zoumx
     * @Description //动态规划
     * @Date 13:04 2020/3/8
     * @Param [nums]
     * @return int
     **/
    public int lengthOfLIS1(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int res = 0;
        Arrays.fill(dp, 1);
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }


    /**
     * @Author zoumx
     * @Description //二分法加动态规划
     * @Date 13:19 2020/3/8
     * @Param [nums]
     * @return int
     **/
    public static int lengthOfLIS2(int[] nums) {
        int[] tails = new int[nums.length];
        int res = 0;
        for(int num : nums) {
            int i = 0, j = res;
            while(i < j) {
                int m = (i + j) / 2;
                if(tails[m] < num) {
                    i = m + 1;
                }
                else {
                    j = m;
                }
            }
            tails[i] = num;
            if(res == j) {
                res++;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] sqa = {9,10,2,11,7,101,18};
        int result = lengthOfLIS2(sqa);
        System.out.println(result);
    }



}
