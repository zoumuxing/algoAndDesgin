package com.zoumx.algo.practice;

/**
 * 有序数组的平方
 * 将一个非降序的目标数组 平方后仍然为非降序数组
 *                     降序      升序
 * 双指针法  平方后    0--->j    j+1---->n
 *                     升序       降序
 *                     0<---j    j+1<----n
 *
 *                     0<---j    j+1---->n  升序
 */
public class SortSquares {





    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int negative = -1;
        for (int i = 0; i < n; ++i) {
            if (nums[i] < 0) {
                negative = i;//找出负数的临界值
            } else {
                break;
            }
        }

        int[] ans = new int[n]; //开辟新的内存数组，进行逐一放入元素
        int index = 0, i = negative, j = negative + 1; //订阅左右二边的位置，和开始位置
        //因为存在左右二边都找到了合适的位置 所以用|
        while (i >= 0 || j < n) {
            if (i < 0) {
                ans[index] = nums[j] * nums[j]; //可能比较完，左边已经没有数据了。所以一直移动右边的指针
                ++j;
            } else if (j == n) {
                ans[index] = nums[i] * nums[i];//可能比较完，右边已经没有数据了。所以一直移动左边的指针
                --i;
            } else if (nums[i] * nums[i] < nums[j] * nums[j]) {//比较左右二边，如果右边大于左边，把左边放置合适位置，移动左边指针
                ans[index] = nums[i] * nums[i];
                --i;
            } else {//如果左边大于右边，把右边的放置合适位置，移动右边指针
                ans[index] = nums[j] * nums[j];
                ++j;
            }
            ++index;
        }
        return ans;
    }


    /**
     * 此方法是上个方法中的改进版本，去除了边界条件判断 分别判断二端
     * 取最大或者最小值，倒序或者正序放入
     * @param nums
     * @return                         -4 -3 0 3 4
     */
    public int[] sortedSquares1(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0, j = n - 1, pos = n - 1; i <= j;) {//这里的判断临界值要 i<=j 因为i=j时这个元素也要放置合适的位置
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                ans[pos] = nums[i] * nums[i];
                ++i;
            } else {
                ans[pos] = nums[j] * nums[j];
                --j;
            }
            --pos;
        }
        return ans;
    }


}
