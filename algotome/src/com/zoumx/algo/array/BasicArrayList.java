package com.zoumx.algo.array;

import java.util.ArrayList;

/**
 * 实现一个支持动态扩容的数组
 * 实现一个大小固定的有序数组，支持动态增删改操作
 * 实现两个有序数组合并为一个有序数组
 */

public class BasicArrayList {








    private void mergeArray(int[] a,int[] b) {
        //初始化一个大的数组
        int size = a.length + b.length;
        int[] c = new int[size];
        int i = 0,j = 0,k=0;
        while (i!=a.length&&j!=b.length) {
            if(a[i] >= b[j]) {
                c[k++] = b[j++];
            }else {
                c[k++] = a[i++];
            }
        }
        if(i==a.length &&j!=b.length) {
            while (j!=b.length) {
                c[k++] = b[j++];
            }
        }else if(i!=a.length&&j==b.length) {
             while ((i!=a.length)) {
                 c[k++] = a[i++];
             }
        }
    }






    public static class DynamicArray<E> {
        private Object[] objectsArray;
        private int size = 16;
        private int index = 0;
        public void DynamicArray() {
            objectsArray = new Object[size];
        }

        private void add(E e) {
            //如果加到size,则扩容
            if(index >= size) {
                expan(objectsArray);
            }
            objectsArray[index++] = e;
        }

        private synchronized void expan(Object[] objectsArray) {
            size <<= 1;
            Object[] dest = new Object[size];
            System.arraycopy(objectsArray,0,dest,0,objectsArray.length);
        }
    }


    public static class FixOrderArray {
        private int[] objectsArray;
        private int size = 16;
        private int index = 0;
        public void FixOrderArray() {
            objectsArray = new int[size];
        }
        private void add(int e) {
            if(index == 0) {
                objectsArray[0] = e;
             //进行合适位置插入
            }else {
                //如果加到size,则扩容
                if(index >= size) {
                    expan(objectsArray);
                }
                int pos = findEnablePos(e);
                if(0 == pos) {

                }
                //进行元素迁移
             /*   for (int i = 0; i < ; i++) {

                }
*/

                objectsArray[index++] = e;
            }
        }

        private int findEnablePos(int e) {
            for (int i = 0; i <= index; i++) {
                if(objectsArray[i]> e) {
                    return i;
                }
            }
            return index + 1;
        }

        private synchronized void expan(int[] objectsArray) {
            size <<= 1;
            Object[] dest = new Object[size];
            System.arraycopy(objectsArray,0,dest,0,objectsArray.length);
        }



    }


}
