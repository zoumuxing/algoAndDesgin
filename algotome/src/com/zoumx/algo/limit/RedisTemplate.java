package com.zoumx.algo.limit;

import java.util.Random;
import java.util.UUID;

public class RedisTemplate {
    public boolean hasKey(String limit) {
        return false;
    }

    public RedisTemplate opsFvoidorZSet() {
        return this;
    }

    public RedisTemplate rangeByScore(String limit, long l, Long currentTime){
     return this;
    }

   public RedisTemplate opsForZSet(){
      return this;
    }


    public Integer size() {
        return 0;
    }

    public void add(String limit, UUID randomUUID, Long currentTime) {
    }
}
