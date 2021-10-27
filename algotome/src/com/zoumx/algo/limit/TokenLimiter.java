package com.zoumx.algo.limit;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TokenLimiter {

    private ArrayBlockingQueue<String> blockingQueue;
    private int limit;
    private TimeUnit timeUnit;
    private int period;


    public TokenLimiter(int limit,int period,TimeUnit timeUnit){
        this.limit = limit;
        this.timeUnit = timeUnit;
        this.period = period;
        blockingQueue = new ArrayBlockingQueue<>(limit);
        init();
        start();
    }



    /**
     * 初始化令牌操作
     */
    private void init() {
        for (int i = 0; i < limit; i++) {
            blockingQueue.add("1");
        }
    }

    /**
     * 获取令牌为空，返回false
     * @return
     */
    public boolean tryAcquire(){
        return blockingQueue.poll()==null?false:true;
    }

    private void addToken(){
        blockingQueue.offer("1");
    }




    private void start() {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(()->{
            addToken();
        },10,period,timeUnit);
    }

}
