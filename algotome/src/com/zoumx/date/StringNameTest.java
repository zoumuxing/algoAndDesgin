package com.zoumx.date;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

public class StringNameTest {

    public static void main(String[] args) {
        String a= "11酷酷酷444";
        String b= "11酷e酷444";
        String c= "11酷e酷444";
        if(a.contains("4酷酷酷4")) {
            System.out.println(1);
        }

        if(b.contains("e")) {
            System.out.println(2);
        }

        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue(10);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }


    }
}
