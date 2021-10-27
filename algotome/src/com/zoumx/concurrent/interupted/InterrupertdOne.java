package com.zoumx.concurrent.interupted;

public class  InterrupertdOne extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());//t1
            System.out.println(this.getName());//Thread-0
        }
}
