package com.zoumx.concurrent.interupted;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class CachedData {
    Object data;
    volatile boolean cacheValid;    //缓存是否有效
    ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    void processCachedData() {
        rwl.readLock().lock();    //获取读锁
        //如果缓存无效，更新cache;否则直接使用data
        if (!cacheValid) {
            // Must release read lock before acquiring write lock
            //获取写锁前须释放读锁
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            // Recheck state because another thread might have acquired
            //   write lock and changed state before we did.
            if (!cacheValid) {
                data = "eeee";
                cacheValid = true;
            }
            // Downgrade by acquiring read lock before releasing write lock
            //锁降级，在释放写锁前获取读锁
            rwl.readLock().lock();
            rwl.writeLock().unlock(); // Unlock write, still hold read
        }

        use(data);
        rwl.readLock().unlock();    //释放读锁
    }

    private void use(Object data) {
    }
}