package com.zoumx.algo.limit;

import java.util.concurrent.Semaphore;

/**
 * 信号量模型
 * 可以简单的概括为：一个计数器、一个等待队列、三个方法。 在信号量模型里，计数器和等待队列对外是透明的，
 * 只能通过信号量模型提供的三个方法访问它们，init()、acquire()、release()。
 * 使用场景  令牌桶  、对象池，连接池等池化场景
 */
public class SemaphoreDemo {

        public static final int THREAD_SIZE = 10;

        public static void runSomething() throws InterruptedException {
            //模拟处理什么事
            Thread.sleep(1000);
            System.out.println(String.format("current threadId is %d,current time is %d",
                    Thread.currentThread().getId(), System.currentTimeMillis() / 1000));
        }

        public static void main(String[] args) throws InterruptedException {
            //创建一个包含4个许可证的信号量实例
            Semaphore semaphoreDemo = new Semaphore(4);
            for (int i = 0; i < THREAD_SIZE; i++) {
                //获取许可
                Thread demoThread = new Thread(() -> {
                    try {
                        //获取许可
                        semaphoreDemo.acquire();
                        //操作资源
                        runSomething();
                    } catch (InterruptedException e) {
                        //抛出InterruptedException 会将该线程的中断标志设置为false
                        Thread.currentThread().interrupt();
                    } finally {
                        semaphoreDemo.release();
                    }
                });
                //开启demo线程
                demoThread.start();

            }

        }
}
