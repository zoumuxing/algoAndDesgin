package com.zoumx.concurrent.interupted;

public class InterrupedSpec {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().isInterrupted());//false
                System.out.println(Thread.currentThread().isInterrupted());//false
            }
        },"t1");
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();

    }
}
