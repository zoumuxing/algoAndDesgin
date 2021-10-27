package com.zoumx.algo.practice;


import java.util.HashMap;
import java.util.Map;

/**
 * 有序数组，二数之和，返回下标。
 */
public class TwoSum {

    public static void main(String[] args) {
        int [] object = {1,2,3,4,4,9,56,90};
        int target = 8;
        int[] result = twoSum(object,target);
        if(result!=null) {
            System.out.println(result);
        }
    }

    private static int [] twoSum(int[] object,int target) {
        for (int i = 0; i < object.length; i++) {
            int num1 = object[i];
            int index2 = findNum2(object,num1,target,i);
            if(index2 != -1) {
                return new int[]{i+1,index2+1};
            }
        }
        return null;
    }

    /**
     * 因为有序，所以二分查询
     * @param object
     * @param num1
     * @return
     */
    private static int findNum2(int[] object, int num1,int target,int i) {
        int start = 0;
        int end = object.length-1;
        int num2 = target - num1;
        while (start <= end) {
            int mid = (end - start)/2  + start;
            if(num2 == object[mid] && i != mid) {
                return mid;
            } else if(num2 >= object[mid]) {
                start = mid + 1;
            }else {
                end = mid -1;
            }
        }
       return -1;
    }


    /**
     * 因为是有序的，所以可以采取双指针法，进行逐一判断
     * @param object
     * @param target
     * @return
     */
    private static int [] twoSum1(int[] object,int target) {

        int left = 0;
        int right = object.length -1;
        while (left < right) {
           if(object[left] + object[right] == target) {
               return new int[]{++left,++right};
           }else if(object[left] + object[right] > target) {
               right--;
           }else {
               left--;
           }
        }
        return null;
    }


    /**
     * 二数之和，基于hash表这样一个事实。不管该元素在哪个地方。遍历的时候，总会找到其另外一个元素！
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[0];
        int n = nums.length;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) { // O(n)
            int x = nums[i];
            // 哈希查找
            if (map.containsKey(target - x)) {
                int index = map.get(target - x);
                return new int[]{i, index};
            }
            map.put(x, i);
        }
        return new int[0];
    }







    }
