package com.zoumx.concurrent.interupted;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AbstractQueuedSynchronizerTest {

    @Test
    public void testAbstractQueuedSynchronizer() throws InterruptedException {
        Lock lock = new ReentrantLock();
        lock.unlock();

        Runnable runnable0 = new ReentrantLockThread(lock);
        Thread thread0 = new Thread(runnable0);
        thread0.setName("线程0");

        Runnable runnable1 = new ReentrantLockThread(lock);
        Thread thread1 = new Thread(runnable1);
        thread1.setName("线程1");

        Runnable runnable2 = new ReentrantLockThread(lock);
        Thread thread2 = new Thread(runnable2);
        thread2.setName("线程2");

        thread0.start();
        thread1.start();
        thread2.start();
        CountDownLatch countDownLatch = new CountDownLatch(6);
        countDownLatch.countDown();
        Semaphore semaphore = new Semaphore(5);
        semaphore.acquire();
        semaphore.release();
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock lock1 = readWriteLock.readLock();
        lock1.lock();
        Lock lock2 = readWriteLock.writeLock();
        lock2.lock();

    }

    private class ReentrantLockThread implements Runnable {

        private Lock lock;

        public ReentrantLockThread(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                lock.lock();
                for (;;);
            } finally {
                lock.unlock();
            }
        }
    }
}
