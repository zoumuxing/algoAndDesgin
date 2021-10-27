package com.zoumx.algo.sort;

/**
 * 算法思想很简单利用分治思想，从上到下，逐步分解，然后又进行合并
 * merge_sort(p,r) = merge(merge_sort(p,q),merge_sort(q+1,r))
 * 利用递归比较容易求解 因为把问题分解为子额外难题 所有if (p>=r) return;
 */
public class MergeSort {


    public static void main(String[] args) {
        int [] arr = {4,3,6,1,3,2,5};
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(arr,0,arr.length-1);
    }




    public void mergeSort(int[] arr,int p,int r) {

        if(p>=r) return;
        int q = (p+r)/2;
        //进行拆分
        mergeSort(arr,p,q);
        mergeSort(arr,q+1,r);
        //进行二个区间合并
        merge(arr,p,q,r);

    }

    /**
     * 双指针法--》本质
     * @param arr
     * @param p
     * @param q
     * @param r
     */
    private void merge(int[] arr,int p, int q, int r) {
       //申请一个大得数组
        int [] tmp = new int[r-p +1];
        int i = p;
        int j = q + 1;
        int k =0;
        //将二个未合并得数组放入临时数组中
        while (i<=q&& j<=r) {
            if(arr[i] <= arr[j]) {
                arr[i++]=tmp[k++];
            }else {
                arr[j++] = tmp[k++];
            }
        }
        //判断哪个临时数组还有值
        int start = i,end = q;
        if(j<=r) {
            start = j;
            end = r;
        }

        while (start!=end) {
            tmp[start++] =  arr[start++];
        }

        //将临时数组copy回原来数组
        for (int l = 0; k < tmp.length; l++) {
            arr[p] = tmp[l];
        }
    }



    /**
     * 合并(哨兵)
     *
     * @param arr
     * @param p
     * @param q
     * @param r
     */
    private static void mergeBySentry(int[] arr, int p, int q, int r) {
        int[] leftArr = new int[q - p + 2];
        int[] rightArr = new int[r - q + 1];

        for (int i = 0; i <= q - p; i++) {
            leftArr[i] = arr[p + i];
        }
        // 第一个数组添加哨兵（最大值）
        leftArr[q - p + 1] = Integer.MAX_VALUE;

        for (int i = 0; i < r - q; i++) {
            rightArr[i] = arr[q + 1 + i];
        }
        // 第二个数组添加哨兵（最大值）
        rightArr[r-q] = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;
        int k = p;
        while (k <= r) {
            //为了让所有数组元素能迭代下去，所有才有了哨兵
            // 当左边数组到达哨兵值时，i不再增加，直到右边数组读取完剩余值，同理右边数组也一样
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
    }
















}
