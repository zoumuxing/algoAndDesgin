package com.zoumx.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DateTest {

    public static void main(String[] args) throws InterruptedException, ParseException {


        Integer[] sexArray = new Integer[]{0,0,1,0,0,0};

        Random random = new Random();
        int sex = sexArray[random.nextInt(5)];

        //生日随机函数 生日最好在1980-1996年之间生日日期
        //1980年1月1日时间戳    1996年 12月31日


         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         long startTime = simpleDateFormat.parse("1980-01-01 00:00:00").getTime();
         long endTime = simpleDateFormat.parse("1996-12-31 23:59:59").getTime();
         System.out.println(endTime);
         System.out.println(startTime);
         long diff = endTime - startTime;
         System.out.println((diff + startTime)/1000);

/*
         String start = simpleDateFormat.format(new Date(startTime));
         String end =  simpleDateFormat.format(new Date(endTime));
*/


      /*  System.out.println(start);
         System.out.println(end);*/
        long tody = 584519714L*1000;
        System.out.println(tody);
        String test = simpleDateFormat.format(new Date(tody));
        System.out.println(test);
        //long startTime = simpleDateFormat.


    }
}
