package com.zoumx.algo.recall;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
 
/**
 * @author hungteshun
 * @description:
 * @date 2018/11/12 17:53
 */
public class TestMap {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "张三");
        map.put(2, "李四");
        map.put(3, "王五");
        map.put(4, "王五");
        map.put(4, "王五");
        System.out.println("map的大小：" + map.size());

        System.out.println();
        System.out.println("方式一：通过Map.keySet遍历key和value：");
        for (Integer key : map.keySet()) {
            String s = map.get(key);
            System.out.println(key + ": " + s);
        }
        System.out.println();

        System.out.println("方式二：通过Map.entrySet遍历key和value：");
        System.out.println("推荐，尤其是容量大时");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println();

        System.out.println("方式三：通过Map.entrySet使用iterator遍历key和value：");
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println();

        System.out.println("方式四：通过Map.values()遍历所有的value，但不能遍历key");
        for (String value : map.values()) {
            System.out.println(value);
        }
    }
}