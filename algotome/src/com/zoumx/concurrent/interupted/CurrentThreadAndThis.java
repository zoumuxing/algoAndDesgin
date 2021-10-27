package com.zoumx.concurrent.interupted;

public class CurrentThreadAndThis {

    public static void main(String[] args) {
        InterrupertdOne myThread = new InterrupertdOne();
        Thread t1 = new Thread(myThread,"t1");
        t1.start();
    }


}
