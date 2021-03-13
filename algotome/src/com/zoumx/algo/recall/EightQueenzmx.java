package com.zoumx.algo.recall;

import java.util.*;

/**
 * 〈功能概述〉<br>
 *
 * @className: EightQueenzmx
 * @package: com.zoumx.algo.recall
 * @author: admin
 * @date: 2020/2/29 14:02
 */
public class EightQueenzmx {
    final static int EIGHT = 8 ;
      //首先定义一个全局变量，以便保存中间状态
     //数组索引是表示行，值表示列，值表示queen存储在哪一列
    static int queen[] = new int[EIGHT];
    //定义一个计算变量记录总得个数
    static int num = 0;
    public static void main(String[] args) {
        //从零开始依次往下放棋子 0表示第一行
        callQueen(0);
        System.out.println("总共有 " + num + "种放法");
    }

    private static void callQueen(int row) {
        //表示已经放满了不相交的棋子
        if(row == EIGHT) {
            printQueen();
            return;
        }
        //进行棋子放置，并且穷举所有的情况
        for (int i = 0; i < EIGHT; i++) {
            //进行回溯查找是否有不满足条件的情况
            if(putIsOk(row,i)) {
                //如果没有 那么我们就在这里放置
                queen[row] = i;
                //进行下一行放置
                callQueen(row + 1);
            }
        }
    }

    private static boolean putIsOk(int row, int column) {
         int left = column - 1;
         int right = column + 1;
         //进行向上查找是否满足条件的情况
         for (int j = row - 1; j >= 0; j--) {
             //正上方查找
             if(queen[j] == column) {
                 return false;
             }
             //斜左方查找
             if(left > 0 && queen[j] == left) {
                 return false;
             }
             //斜右方查找
             if(right < 8 && queen[j] == right) {
                 return false;
             }
             left--;right++;
         }
         return true;
    }

    private static void printQueen() {
        for (int row = 0; row < EIGHT ; row++) {
            for (int column = 0; column < 8; column++) {
                if(queen[row] == column) {
                    System.out.print("Q ");
                }else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        ++num;
        System.out.println();
    }
}
