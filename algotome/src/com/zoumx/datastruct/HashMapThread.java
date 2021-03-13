package com.zoumx.datastruct;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  模拟hashmap出现死循环的问题  jdk1.7 该类是模拟的线程类<br>
 *
 * @className: HashMapThread
 * @package: com.zoumx.datastruct
 * @author: admin
 * @date: 2020/2/25 23:06
 */
public class HashMapThread extends Thread
{
    private static AtomicInteger ai = new AtomicInteger();
    private static Map<Integer, Integer> map = new HashMap<>();

    @Override
    public void run() {
        while (ai.get() < 1000000) {
            map.put(ai.get(), ai.get());
            ai.incrementAndGet();
        }
    }

}
