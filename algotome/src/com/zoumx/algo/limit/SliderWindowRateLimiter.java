package com.zoumx.algo.limit;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 滑动窗口为固定窗口的改良版，解决了固定窗口在窗口切换时会受到两倍于阈值数量的请求，
 * 滑动窗口在固定窗口的基础上，将一个窗口分为若干个等份的小窗口，每个小窗口对应不同的时间点，
 * 拥有独立的计数器，当请求的时间点大于当前窗口的最大时间点时，则将窗口向前平移一个小窗口（将第一个小窗口的数据舍弃，
 * 第二个小窗口变成第一个小窗口，当前请求放在最后一个小窗口），整个窗口的所有请求数相加不能大于阀值
 */
class SliderWindowRateLimiter  implements Runnable {
    private long[][] arr = { { 0 }, { 0 }, { 0 }, { 0 }, { 0 }, { 0 } };

    private final int max = 100;

    private long size = 600000;// 窗口大小

    private long time = new Date().getTime();// 窗口开始时间

    public boolean trafficMonitoring() {

        long nowTime = new Date().getTime();// 请求进来的时间

        // 计算坐标
        long result = (nowTime - time) % 10000;

        int index = 0;

        if (result == 0) {
            index = (int) (nowTime / time);
        } else {

            index = (int) (nowTime / time + 1);
        }

        if (index > arr.length) {
            // 不在窗口内，将滑动窗口平移
            for (int i = 0; i < index - arr.length; i++) {
                // 将数组平移
                for (int j = 0; j < arr.length - 1; j++) {

                    arr[j][0] = arr[j + 1][0];
                }
                // 将起始时间也向前推进一个窗口
                time += 10000;
            }
            // 本次插入的窗口为最后一个窗口
            index = arr.length - 1;
        }
        // 计算窗口总的请求数是否小于阈值
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i][0];
        }
        if (total > max) {
            return false;
        }
        // 获取小窗口目前的请求值
        long count = arr[index - 1][0];
        // 加上本次请求数
        arr[index - 1][0] = count + 1;

        return true;
    }



    //每秒允许的最大访问数
    private final long maxVisitPerSecond;
    //将每秒时间划分N个块
    private final int block;
    //每个块存储的数量
    private final AtomicLong[] countPerBlock;
    //滑动窗口划到了哪个块儿，可以理解为滑动窗口的起始下标位置
    private volatile int index;
    //目前总的数量
    private AtomicLong allCount;

    /**
     * 构造函数
     *
     * @param block，每秒钟划分N个窗口
     * @param maxVisitPerSecond 每秒最大访问数量
     */
    public SliderWindowRateLimiter(int block, long maxVisitPerSecond) {
        this.block = block;
        this.maxVisitPerSecond = maxVisitPerSecond;
        countPerBlock = new AtomicLong[block];
        for (int i = 0; i < block; i++) {
            countPerBlock[i] = new AtomicLong();
        }
        allCount = new AtomicLong(0);
    }

    /**
     * 判断是否超过最大允许数量
     *
     * @return
     */
    public boolean isOverLimit() {
        return currentQPS() > maxVisitPerSecond;
    }

    /**
     * 获取目前总的访问数
     *
     * @return
     */
    public long currentQPS() {
        return allCount.get();
    }

    /**
     * 请求访问进来，判断是否可以执行业务逻辑
     */
    public void visit() {
        countPerBlock[index].incrementAndGet();
        allCount.incrementAndGet();

        if (isOverLimit()) {
            System.out.println(Thread.currentThread().getName() + "被限流" + "，currentQPS：" + currentQPS() + "，index：" + index);
        } else {
            System.out.println(Thread.currentThread().getName() + "执行业务逻辑" + "，currentQPS：" + currentQPS() + "，index：" + index);
        }
    }

    /**
     * 定时执行器，
     * 每N毫秒滑块移动一次，然后再设置下新滑块的初始化数字0，然后新的请求会落到新的滑块上
     * 同时总数减掉新滑块上的数字，并且重置新的滑块上的数量
     */
    @Override
    public void run() {
        index = (index + 1) % block;
        long val = countPerBlock[index].getAndSet(0);
        System.out.println(val);
        allCount.addAndGet(-val);
    }

    public static void main(String[] args) {
        SliderWindowRateLimiter sliderWindowRateLimiter = new SliderWindowRateLimiter(10, 100);

        //固定的速率移动滑块
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(sliderWindowRateLimiter, 100, 100, TimeUnit.MILLISECONDS);

        //模拟不同速度的请求
        new Thread(() -> {
            while (true) {
                sliderWindowRateLimiter.visit();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //模拟不同速度的请求
        new Thread(() -> {
            while (true) {
                sliderWindowRateLimiter.visit();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
