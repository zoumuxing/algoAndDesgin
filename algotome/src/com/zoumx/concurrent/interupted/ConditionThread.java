package com.zoumx.concurrent.interupted;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionThread  implements Runnable{
    private Lock lock;

    private Condition condition;

    public ConditionThread(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }



    @Override
    public void run() {

        if ("线程0".equals(JdkUtil.getThreadName())) {
            thread0Process();
        } else if ("线程1".equals(JdkUtil.getThreadName())) {
            thread1Process();
        } else if ("线程2".equals(JdkUtil.getThreadName())) {
            thread2Process();
        }

    }

    private void thread0Process() {
        try {
            lock.lock();
            System.out.println("线程0休息5秒");
            JdkUtil.sleep(5000);
            condition.signal();
            System.out.println("线程0唤醒等待线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void thread1Process() {
        try {
            lock.lock();
            System.out.println("线程1阻塞");
            condition.await();
            System.out.println("线程1被唤醒");
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }

    private void thread2Process() {
        try {
            System.out.println("线程2想要获取锁");
            lock.lock();
            System.out.println("线程2获取锁成功");
        } finally {
            lock.unlock();
        }
    }





}
