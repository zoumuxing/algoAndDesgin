package com.zoumx.datastruct.sourceanalyze;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zoumx
 * @Description //jdk 1.7
 * @Date 11:54 2020/2/26
 * @Param
 * @return
 **/
public class HashMapTest {

    public static void main(String[] args) {

        String key_Aa = "Aa";
        String key_BB = "BB";

        // 注意这里的hashCode值
        System.out.println("key_Aa hashCode=" + key_Aa.hashCode());
        System.out.println("key_BB hashCode=" + key_BB.hashCode());

        Map<String, String> hashMap = new HashMap<String, String>();

        hashMap.put(key_Aa,"Aa");
        hashMap.put(key_Aa,"Aa");
//        hashMap.put(key_BB,"Aa");
        System.out.println(hashMap);
    }
}