package com.zoumx.date;

import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest1 {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

      /*
        startTime=1628706600&endTime=1628793000
        Long  testDate23 = System.currentTimeMillis();
        System.out.println(testDate23);
        Long  testDate22 = System.currentTimeMillis() / 1000;*/
       // System.out.println(testDate22);
        String ee = "2021-08-15 13:00:00";
        long time = simpleDateFormat.parse(ee).getTime();
        System.out.println(time);
        long dates = 1626519221000l;

   /*     Date date2 = new Date(dates);
        String result1 = simpleDateFormat.format(date2);
        System.out.println(result1);*/

        long date1s = 1629083567l;
        //1629003600
        Date date3 = new Date(date1s*1000);
        String result2 = simpleDateFormat.format(date3);
        System.out.println(result2);

        //long time = 1627613001561l;
       // long formateday = 1627613001;

      /*  long testT = (long) (1627607178.00356*1000);
        Date date2 = new Date(testT);
        String result12 = simpleDateFormat.format(date2);
        //Date date3 = new Date(testDate22);
        System.out.println(result12);
        long testT1 = 1627615839141l;
        Date date3 = new Date(testT1);
        String result13 = simpleDateFormat.format(date3);
        //Date date3 = new Date(testDate22);
        System.out.println(result13);
*/
       // String result33 = simpleDateFormat.format(date3);
       // System.out.println(result33);
       // InetAddress address = new In
    /*    String userId = "10005/Server";
        String userId2 = "10005";

        String result = userId.substring(0,userId.indexOf("/"));
        String result1 = userId2.substring(0,userId2.indexOf("/"));
        System.out.println(result);
        System.out.println(result1);*/

        String tab_pos = "  ";
        int kkk = tab_pos.length();
        System.out.println(kkk);

    }
}
