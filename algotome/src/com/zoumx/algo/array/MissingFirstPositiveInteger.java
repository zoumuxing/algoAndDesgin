package com.zoumx.algo.array;

import java.util.HashMap;
import java.util.Map;

public class MissingFirstPositiveInteger {

    public static void main(String[] args) {
        int [] testArray = new int[]{5,2,10,4,1};
        //存在1，和不存在1  存在1，就去Map找到1+1的元素看是否存在，不存在则返回，存在则继续
        int result = findMissFirstPositiveInteger(testArray);
        //替换将数组中的数放到原有位置
        int result1 = findMissFirstPositiveInteger1(testArray);
        System.out.println(result1);
        System.out.println(result);
    }

    private static int findMissFirstPositiveInteger1(int[] testArray) {
        int n = testArray.length;
        for (int i = 0; i < testArray.length ; i++) {
            while (testArray[i] > 0 && testArray[i] <= n &&testArray[testArray[i] -1] != testArray[i]) {
                swap(testArray[testArray[i] -1],testArray[i]);
            }
        }

        for (int i = 0; i < testArray.length; i++) {
            if(testArray[i] != i +1) {
                return i+1;
            }
        }
        return 1;
    }

    private static void swap(int i, int j) {

    }

    private static int findMissFirstPositiveInteger(int[] testArray) {
        Map<Integer,Integer> numMap = new HashMap<>();
        int result = 1;
        int min = 1;
        for (int i=0;i<testArray.length;i++) {
            numMap.put(testArray[i],i);
            //求最小值
            if(testArray[i] <= min) {
                min = testArray[i];
            }
        }
        //求得最小值 如果等于1,就去map里面找
        if(min < 1) {
            return 1;
        }
        if(min == 1) {
            while (true) {
                if(!numMap.containsKey(++min)) {
                    return min;
                }
            }
        }
        return result;
    }




}
