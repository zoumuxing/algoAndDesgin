package com.zoumx.concurrent.interupted;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionThreadTest {

    /**
     * @author 五月的仓颉http://www.cnblogs.com/xrq730/p/7067904.html
     */
    @Test
    public void testCondition() throws Exception {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        // 线程0的作用是signal
        Runnable runnable0 = new ConditionThread(lock, condition);
        Thread thread0 = new Thread(runnable0);
        thread0.setName("线程0");
        // 线程1的作用是await
        Runnable runnable1 = new ConditionThread(lock, condition);
        Thread thread1 = new Thread(runnable1);
        thread1.setName("线程1");
        // 线程2的作用是lock
        Runnable runnable2 = new ConditionThread(lock, condition);
        Thread thread2 = new Thread(runnable2);
        thread2.setName("线程2");

        thread1.start();
        Thread.sleep(1000);
        thread0.start();
        Thread.sleep(1000);
        thread2.start();

        thread1.join();
    }
}
