package com.zoumx.concurrent.interupted;

import com.zoumx.concurrent.spliterator.Atest;

public class InterruptedTest {
 /*   public static void main(String[] args) {
         MyThread myThread = new MyThread();
         myThread.start();
         myThread.interrupt();
         System.out.println("第一次调用返回值："+Thread.interrupted());
         System.out.println("第一次调用返回值："+Thread.interrupted());
         System.out.println("===================end===================");
    }*/




 /*   public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        myThread.interrupt();
        Thread.currentThread().interrupt();
        System.out.println("第一次调用返回值："+Thread.interrupted());
        System.out.println("第一次调用返回值："+Thread.interrupted());
        System.out.println("===================end===================");
    }*/


   /* public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        myThread.interrupt();
        System.out.println("第一次调用返回值："+myThread.isInterrupted());
        System.out.println("第一次调用返回值："+myThread.isInterrupted());
        System.out.println("===================end===================");
    }
*/

  /*  public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        myThread.interrupt();
        System.out.println("第一次调用返回值："+Thread.currentThread().isInterrupted());
        System.out.println("第一次调用返回值："+Thread.currentThread().isInterrupted());
        System.out.println("===================end===================");
    } */

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.currentThread().interrupt();
        System.out.println("第一次调用返回值："+Thread.currentThread().isInterrupted());
        System.out.println("第一次调用返回值："+Thread.currentThread().isInterrupted());
        System.out.println("===================end===================");
    }

}
