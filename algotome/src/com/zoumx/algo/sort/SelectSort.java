package com.zoumx.algo.sort;

public class SelectSort {

    public static void main(String[] args) {

        SelectSort selectSort = new SelectSort();
        selectSort.selectSort();

    }

    private void selectSort() {
        int [] testArray = new int[] {31,41,59,26,41,58};
        for (int i = 0; i < testArray.length; i++) {
            int k = i;
            for (int j = k + 1; j < testArray.length; j++) {
                if(testArray[j] < testArray[k]) {
                   k = j;
                }
            }
          if(k!=i) {
              int tmp = testArray[i];
              testArray[i] = testArray[k];
              testArray[k] = tmp;
          }
        }
        printArray(testArray);
    }

    private void printArray(int[] testArray) {
        for (int i = 0; i < testArray.length; i++) {
            System.out.print(testArray[i] + " ");
        }
    }


    /**
     * 跟插入排序一样，区分未排序和已排序区间，不过是在未排序区间寻找最小值放到已排序末尾，存在比较交换，、
     * 不是稳定排序算法   5 8 5 2 9 会将第一个5位置交换改变
     * @param nums 比较的是索引，因为涉及到元素交换
     */
    private void selectSort1(int []nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            int j = i +1;
            for (; j < n; i++) {
                if(nums[j] < nums[min]) {
                    min = j;//找出最小值索引
                }
            }
            //进行交换
            if(min != i) {
                int tmp = nums[i];
                nums[i] = nums[min];
                nums[min] = tmp;
            }
        }

    }





















}
