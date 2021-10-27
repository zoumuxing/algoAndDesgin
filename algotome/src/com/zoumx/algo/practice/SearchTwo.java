package com.zoumx.algo.practice;

/**
 * 二分查询
 */
public class SearchTwo {


    public static void main(String[] args) {
        int[] nums = new int[] {-1,0,3,5,9,12};
        int target = -1;
        SearchTwo searchTwo = new SearchTwo();
        int result = searchTwo.search(nums,target);
        System.out.println(result);
    }
        //二分查找的条件是查找范围不为空，left <= right
        public int search(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;//防止值溢出
                // int mid = l + r >> 1;  移位代替除，更高效
                int num = nums[mid];
                if (num == target) {
                    return mid;
                } else if (num > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return -1;
    }


    public int firstBadVersion1(int n) {
        int low = 1;
        int high = n;
        int mid;
        while(low < high){
            mid = low +(high - low) /2;
            if(isBadVersion(mid)){
                high = mid; //这里不收缩，因为 lov < high 如果错误版本恰好是high 循环结束返回low = high
            }else{
                low = mid + 1;
            }
        }
        return low;
    }


    public int firstBadVersion(int n) {
        int low = 1;
        int high = n;
        int mid;
        while(low <= high){
            mid = low +(high - low) /2;
            if(isBadVersion(mid)){
                high = mid - 1; // 收缩右边界，锁定左边界 -- 因为第一个错误吧版本之后的版本都是错误版本，收缩过后，
                                // high就是错误版本前一个版本，故low <= high  等号过后 low = high + 1
            }else{
                low = mid + 1;
            }
        }
        // if(low > n) return -1; // 在此可以判断是否越界或者 left位置的值是否等于目标值等
        return low;
    }

    private boolean isBadVersion(int mid) {
        return false;
    }


    /**
     * 返回目标值的索引，如果目标值不在数组中，则返回应插入数组中的索引位置
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (high-low)/2 + low;
            if(nums[mid] == target) {
                return mid;
            }else if(nums[mid] < target) {
                low = mid + 1;
            }else {
                high = mid -1;
            }
        }
       return low;
    }













}
