package com.zoumx.algo.practice;

/**
 * 将一个数组移动K位数组
 */
public class RotateArray {


    public static void main(String[] args) {
        RotateArray rotateArray = new RotateArray();
        int []nums = {1,2,3,4,5,6,7};
        int k = 3;
        rotateArray.rotate2(nums,k);
        System.out.println(System.currentTimeMillis());
    }


    /**
     * 方法一  穷举遍历 存储到另一容器中 ===》最后进行数组复制
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }

    /**
     * 方法二 反转法 基于一个这样的事实 设k=2    1 2 3 4 5  -》5 4 3 2 1-》4 5 3 2 1 => 4 5 1 2 3
     * @param nums
     * @param k
     */
    public void rotate1(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }


    /**
     * 轮转替换法  基于数学原理 很容易想到 保留先前 位置，然后不断循环替换后面位置直到回到原来位置，但是原来位置不一定完成了全部替换
     * 设 a 单次回到远点的循环圈数  k 为要替换的长度  b为单次遍历的元素个数
     * 假设单次替换回到原点 那么 an = bk an为 n和k的公倍数，那么要使回到循环圈数最小，则 an应该为 n和k的最小公倍数
     * b = an/k   lcm(n,k)/k （单次遍历会访问这么多）   那么访问所有 count = nk/lcm(n,k) = gcd(n,k)
     *
     *
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = gcd(k, n);
        for (int start = 0; start < count; ++start) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
            } while (start != current);
        }
    }



    public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }
























    public void rotate3(int[] nums, int k) {
        int n = nums.length;
        k = k%n;
        int count = gcd1(k,n);
        for (int start = 0; start < count; start++) {
          int current = start;
          int pre = nums[start];
           do {
                int next = (k + current)/n;
                int tmp = nums[next];
                nums[next] = pre;
                pre = tmp;
                current = next;
            }while (start!=current);
        }
    }

    private int gcd1(int k, int n) {
        return n > 0?gcd1(n,k%n):k;
    }


}
