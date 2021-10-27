package com.zoumx.sources;



import java.util.ArrayList;
import java.util.List;

public class IterableRead {

    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.forEach(
                e-> System.out.println(e.equals(1)?1:2)
        );



    }

}
