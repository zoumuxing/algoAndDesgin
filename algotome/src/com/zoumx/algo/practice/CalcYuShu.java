package com.zoumx.algo.practice;

public class CalcYuShu {

    public static void main(String[] args) {
        int a = 42;
        int b = 21;
        int result = gcd(b,a);
        System.out.println(result);
        int result1 =divisionAlgorithm(b,a);
        System.out.println(result1);
    }


    /**
     * 使用辗转相除法求两个正整数的最大公约数
     * @param num1 正整数1
     * @param num2 正整数2
     * @return 两个正整数的最大公约数
     */
    public static int divisionAlgorithm(int num1, int num2) {
        // 求两个正整数中最大的数
        int result = 0;
            // 较大的数除以较小的数并取余数
            int remainder = num1 % num2;
            while (remainder != 0) {
                num1 = num2;
                num2 = remainder;
                remainder = num1 % num2;
            }
            result = num2;
        return result;
    }



    public static int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }
}
