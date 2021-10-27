package com.zoumx.concurrent.interupted;

public class JdkUtil {
    public static String getThreadName() {
       return Thread.currentThread().getName();
    }

    public static void sleep(int i) throws InterruptedException {
        Thread.sleep(i);
    }
}
