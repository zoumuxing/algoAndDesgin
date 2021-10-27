package com.zoumx.algo.array;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {

    public static void main(String[] args) {
        long start = 1632035879009l;
        Date date = new Date(start);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd ss");
    }
}
