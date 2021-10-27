package com.zoumx.algo.limit;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Executors;

public class RedisLimit {

    RedisCacheClient redisCacheClient = new RedisCacheClient();
    String jedisGroup = "test";

    public static void main(String[] args) {

    }


    /**
     * 方法一、setnx--配合lua脚本才能保证原子性
     * 而且是固定窗口
     * @param key
     * @return
     */
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
    }

    RedisTemplate redisTemplate = new RedisTemplate();
    Long intervalTime;

    /**
     * 滑动时间窗口
     * 我们可以将请求打造成一个zset数组，当每一次请求进来的时候，value保持唯一，可以用UUID生成，而score可以用当前时间戳表示，
     * 因为score我们可以用来计算当前时间戳之内有多少的请求数量。而zset数据结构也提供了range方法让我们可以很轻易的获取到2个时间戳内有多少请求

     */
    public void limitFlow1(){
        Long currentTime = new Date().getTime();
        System.out.println(currentTime);
        if(redisTemplate.hasKey("limit")) {
            Integer count = redisTemplate.opsFvoidorZSet().rangeByScore("limit", currentTime -  intervalTime, currentTime).size();        // intervalTime是限流的时间
            System.out.println(count);
            if (count != null && count > 5)
                System.out.println("每分钟最多只能访问5次");
        }
          redisTemplate.opsForZSet().add("limit",UUID.randomUUID(),currentTime);
        }


    /**
     * 第三种：基于Redis的令牌桶算法
     *
     *     提到限流就不得不提到令牌桶算法了。具体可以参照度娘的解释  令牌桶算法
     *
     *     令牌桶算法提及到输入速率和输出速率，当输出速率大于输入速率，那么就是超出流量限制了。
     *
     *     也就是说我们每访问一次请求的时候，可以从Redis中获取一个令牌，如果拿到令牌了，那就说明没超出限制，而如果拿不到，则结果相反。
     *
     *     依靠上述的思想，我们可以结合Redis的List数据结构很轻易的做到这样的代码，只是简单实现
     * @param id
     * @return
     */
    // 输出令牌
/*    public Response limitFlow2(Long id){
        Object result = redisTemplate.opsForList().leftPop("limit_list");
        if(result == null){
            return Response.ok("当前令牌桶中无令牌");
        }
        return Response.ok(articleDescription2);
    }
   // 再依靠Java的定时任务，定时往List中rightPush令牌，当然令牌也需要唯一性，所以我这里还是用UUID进行了生成

// 10S的速率往令牌桶中添加UUID，只为保证唯一性
    @Scheduled(fixedDelay = 10_000,initialDelay = 0)
    public void setIntervalTimeTask(){
        redisTemplate.opsForList().rightPush("limit_list",UUID.randomUUID().toString());
    }*/

}
