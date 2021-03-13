package com.zoumx.algo.divide;

/**
 *  求一个数组的逆序度个数，使用的分治法，
 *  分治法：首先把总得求逆序度的问题，分解成N个小问题，因为小问题跟总得问题求解一样。故可分
 *  再者，合并起来的复杂度也不是很大。故可以适应分治法<br>
 *   计算逆序度的值 = 分组后 之间的值
 * @className: InverseNumber
 * @package: com.zoumx.algo.divide
 * @author: admin
 * @date: 2020/2/29 12:53
 */
public class InverseNumber {
    private static int num = 0;

    public static void main(String[] args) {
        int a[] = {2,6,4,1,5,3};
        handerInverseNumber(a);
        print(a);
        System.out.println();
        System.out.println("逆序度个数为" + num);
    }

    private static void print(int[] a) {
        System.out.println("排序后的数组为 ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    private static void handerInverseNumber(int[] a) {
        mergeSort(a,0,a.length-1);
    }

    /**
     * @Author zoumx
     * @Description //进行 divide conquer
     * @Date 13:00 2020/2/29
     * @Param [a, i, i1]
     * @return void
     **/
    private static void mergeSort(int[] a, int p, int r) {
        if(p >= r) {
            return ;
        }
        int q = p + (r-p)/2;
        mergeSort(a,p,q);
        mergeSort(a,q+1,r);
        merge(a,p,q,r);
    }

    private static void merge(int [] a, int p, int q,int r) {
        int i = p; int j = q +1; int k = 0;
        //定义一个tmp临时数组
        int [] tmp = new int[r - p +1];
        while ( i <= q && j <= r) {
             if(a[i] <= a[j]) {
                 tmp[k++] = a[i++];
             }else {
                 tmp[k++] = a[j++];
                 num += q - i + 1;
             }
        }
        //计算剩余的数加入到tmp数组中
        while (i <= q) {
            tmp[k++] = a[i++];
        }
        while(j <= r) {
            tmp[k++] = a[j++];
        }

        //copy回原数组
        for (int l = 0; l <= r - p ; l++) {
            a[p +l] = tmp[l];
        }
    }
}
