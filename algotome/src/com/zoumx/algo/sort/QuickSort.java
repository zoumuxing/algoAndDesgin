package com.zoumx.algo.sort;

import static com.zoumx.algo.practice.MoveZeroes.swap;

/**
 * 快速排序 是一种从上至下，找pivot得方法
 *也可以使用递归公司实现
 * quick_sort(p,r) = quick_sort(p,pivot-1) + quick_sort(pivot+1,r)
 */
public class QuickSort {


    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int [] arr = {4,3,6,1,3,2,5};
        quickSort.quickSort(arr,0,arr.length-1);
    }

    private void quickSort(int[] arr, int p, int r) {
        if(p>=r) {
            return;
        }
        //进行分区找中点
        int q = partition(arr,p,r);
        //继续二边快排
        quickSort(arr,p,q-1);
        quickSort(arr,q+1,r);
    }

    private int partition(int[] arr, int p, int r) {
        int pivot = arr[r];//自定义pivot为最右边
        int j = p;
        for (int i = p; i < r; i++) {
            if(arr[i] >= pivot&&i!=j) {
                swap(arr,j,i);
                j++;
            }
        }
        swap(arr,j,r);
      return j;
    }

}
