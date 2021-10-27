package com.zoumx.algo.limit;

import com.google.common.util.concurrent.RateLimiter;

public class GuavaRateLimiterTest {
    //比如每秒生产10个令牌，相当于每100ms生产1个令牌
    private com.google.common.util.concurrent.RateLimiter rateLimiter = RateLimiter.create(10);

    /**
     * 模拟执行业务方法
     */
    public void exeBiz() {
        if (rateLimiter.tryAcquire(1)) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + Thread.currentThread().getName() + "：执行业务逻辑");
        } else {
            System.out.println("线程" + Thread.currentThread().getName() + "：被限流");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        GuavaRateLimiterTest limiterTest = new GuavaRateLimiterTest();
        //Thread.sleep(1000);//等待500ms，让limiter生产一些令牌

        //模拟瞬间生产100个线程请求
        for (int i = 0; i < 100; i++) {
            new Thread(limiterTest::exeBiz).start();
        }
    }

    private void test(){

    }
}
