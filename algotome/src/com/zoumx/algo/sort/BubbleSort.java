package com.zoumx.algo.sort;

/**
 * 俩俩元素进行比较交换
 * 每次都将最后一位进行冒泡到最后
 */
public class BubbleSort {


    // 冒泡排序，a表示数组，n表示数组大小
    public void bubbleSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 0; i < n; ++i) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < n - i - 1; ++j) {
                if (a[j] > a[j+1]) { // 交换
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;  // 表示有数据交换
                }
            }
            if (!flag) break;  // 没有数据交换，提前退出
        }
    }



















     public void bubbleSort(int nums[]) {
        int n = nums.length;
         for (int i = 0; i < n; i++) {
             boolean flag = false;//冒泡退出循环标志，如果没有数据交换，说明已经有序
             //减一是因为要和它的下一个元素比较 不然会溢出
             for (int j = 0; j < n -i -1; j++) {
                   if(nums[j] > nums[j+1]) {
                       int tmp = nums[j];
                       nums[j] = nums[j+1];
                       nums[j+1] = tmp;
                       flag = true;
                   }
             }
             if(!flag) {
                 break;
             }
         }
     }




















}
