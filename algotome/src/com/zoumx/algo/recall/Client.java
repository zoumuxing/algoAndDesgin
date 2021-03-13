package com.zoumx.algo.recall;

/**
 * 〈功能概述〉<br>
 *
 * @className: Client
 * @package: com.zoumx.algo.recall
 * @author: admin
 * @date: 2020/2/29 13:56
 */
public class Client {
    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen();
        eightQueen.cal8queens(0);
    }
}
