package com.zoumx.algo.practice;

public class MoveZeroes {


    public static void main(String[] args) {
        int test[] = {0,1,0,3,12};
        moveZeroes1(test);
    }


    public static void moveZeroes(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if(nums[n-1] == 0) {
                continue;
            }
           int j = i;
           int tmp = nums[i];
            if(nums[i] == 0) {
                int tamp = nums[i];
                nums[i] = nums[n-1];
                nums[n-1] = tamp;
                n--;
            }

           //进行交换与最后一项
           if(nums[j-1] == 0) {
               int tamp = nums[j-1];
               nums[j-1] = nums[n-1];
               nums[n-1] = tamp;
               n--;
           }
           while (j > 0 && nums[j-1] >tmp) {
               nums[j] = nums[j-1];
               j--;
           }
           nums[j] = tmp;
        }
        System.out.println(nums);
    }


    /**
     * 双指针 left 指针始终指向0，right指针一直走，中间如果不等于则进行交换。
     * @param nums
     */
    public static void moveZeroes1(int[] nums) {
            int n = nums.length, left = 0, right = 0;
            while (right < n) {
                if (nums[right] != 0) {
                    swap(nums, left, right);
                    left++;
                }
                right++;
            }
        }

        public static void swap(int[] nums, int left, int right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }


}
