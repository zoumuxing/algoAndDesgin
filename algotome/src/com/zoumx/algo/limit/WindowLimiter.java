package com.zoumx.algo.limit;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class WindowLimiter {

    private long time = new Date().getTime();

    private Integer count = 0; // 计数器

    private final Integer max = 100; // 请求阈值

    private final Integer interval = 1000; // 窗口大小

    public boolean trafficMonitoring() {

        long nowTime = new Date().getTime();

        if (nowTime < time + interval) {
            // 在时间窗口内
            count++;

            return max > count;

        } else {
            time = nowTime; // 开启新的窗口

            count = 1; // 初始化计数器,由于这个请求属于当前新开的窗口，所以记录这个请求

            return true;
        }
    }











    //本地缓存，以时间戳为key，以原子类计数器为value
    private LoadingCache<Long, AtomicLong> counter =
            CacheBuilder.newBuilder()
                    .expireAfterWrite(10, TimeUnit.SECONDS)
                    .build(new CacheLoader<Long, AtomicLong>() {
                        @Override
                        public AtomicLong load(Long seconds) throws Exception {
                            return new AtomicLong(0);
                        }
                    });
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
    //设置限流阈值为15
    private long limit = 15;

    /**
     * 固定时间窗口
     * 每隔5s，计算时间窗口内的请求数量，判断是否超出限流阈值
     */
    private void fixWindow() {
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                // time windows 5 s
                long time = System.currentTimeMillis() / 5000;
                //每秒发送随机数量的请求
                int reqs = (int) (Math.random() * 5) + 1;
                long num = counter.get(time).addAndGet(reqs);
                System.out.println("time=" + time + ",num=" + num);
                if (num > limit) {
                    System.out.println("限流了,num=" + num);
                }
            } catch (Exception e) {
                System.out.println("fixWindow error"+e.getMessage());
            } finally {
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) {
        WindowLimiter windowLimiter = new WindowLimiter();
        windowLimiter.fixWindow();
    }


/*
    //每次请求进来，查询一下当前的计数值，如果超出请求数阈值，则拒绝请求，返回系统繁忙提示
    private long limitFlow(String key) {
        //Setnx（SET if Not eXists） 命令在指定的 key 不存在时，为 key 设置指定的值。设置成功返回1，设置失败返回0
        Long lng = redisCacheClient.setnx(jedisGroup, key, "1");
        if (lng == 1) {
            //设置时间窗口，redis-key时效为10秒
            redisCacheClient.expire(jedisGroup, key, 10);
            return 1L;
        } else {
            //Redis Incrby 命令将 key 中储存的数字加上指定的增量值。相当于放在redis中的计数器，每次请求到来计数器自增1
            long val = redisCacheClient.incrBy(jedisGroup, key, 1);

            return val;
        }
    }*/



}
