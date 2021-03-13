package com.zoumx.datastruct;

/**
 * 〈功能概述〉<br>
 *
 * @className: HashMapTest
 * @package: com.zoumx.datastruct
 * @author: admin
 * @date: 2020/2/25 23:08
 */
public class HashMapTest {

    public static void main(String[] args) {
        HashMapThread thread0 = new HashMapThread();
        HashMapThread thread1 = new HashMapThread();
        HashMapThread thread2 = new HashMapThread();
        HashMapThread thread3 = new HashMapThread();
        HashMapThread thread4 = new HashMapThread();
        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }


}
