package com.zoumx.algo.sort;

/**
 * 插入排序
 */
public class InsertSort {


    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort();
        insertSort.insertSortDesc();
        int [] testArray = new int[] {31,41,59,26,41,58};
        insertSort.insertSort(testArray);
        insertSort.insertSort1(testArray);
        int [] shellArray = {2,8,1,7,3,6,4,5};
        InsertSort.shellSort(shellArray);
    }


    /**
     * 插入排序降序排序
     */
    private void insertSortDesc () {
        int [] testArray = new int[] {31,41,59,26,41,58};
        for (int j = 1; j < testArray.length; j++) {
            int key = testArray[j];
            int i = j -1;
            while (i >=0 && testArray[i] < key) {
                testArray[i+1] = testArray[i];
                i = i -1;
            }
            testArray[i+1] = key;
        }
       // printArray(testArray);
    }

    private void printArray(int[] testArray) {
        for (int i = 0; i < testArray.length; i++) {
            System.out.print(testArray[i] + " ");
        }
    }


    /**
     * 插入排序从已排序空间寻找合适位置插入，从小到大排列
     * @param nums
     */
    private void insertSort(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int j = i-1;
            int value = nums[i];
            for (;j >=0;j--) {
                if(nums[j] > value) {
                   nums[j+1] = nums[j];//向右移动
                }else {
                    break;
                }
            }
            nums[j+1]=value;
        }
        printArray(nums);
    }

    /**
     * 从大到小排列
     * @param nums
     */
    private void insertSort1(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int j = i-1;
            int value = nums[i];
            for (;j >=0;j--) {
                if(value > nums[j]) {
                    nums[j+1] = nums[j];//向右移动
                }else {
                    break;
                }
            }
            nums[j+1]=value;
        }
        printArray(nums);
    }







    public static void shellSort(int[] arr) {

        int length = arr.length;
        int temp;
        for (int step = length / 2; step >= 1; step /= 2) {
            for (int i = step; i < length; i++) {
                temp = arr[i];
                int j = i - step;
                //不停得找到合适得步长进行插入--把值不停得移动在合适得位置。知道找到合适卫视插入
                //希尔排序算是插入排序得扩展
                while (j >= 0 && arr[j] > temp) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j + step] = temp;
            }
        }
    }







    private void shellSort1(int[] nums) {
        int len = nums.length;
        //第一层循环控制步长
        for (int step = len/2; step >0; step/=2) {
            //第二层进行插入排序--以步长为维度进行插入排序
            for (int i = step; step < len; i++) {
                int j = i - step;
                int tmp = nums[i];
                //不停得交换步长元素
                while (j >=0 && nums[j] > tmp) {
                    nums[j+step] = nums[j];
                    j-=step;
                }
                //寻找合适元素插入
                nums[j+step] = tmp;
            }
        }
    }






}
