package com.zoumx.algo.tree.trietree;

/**
 *  the dictorynary tree<br>
 *
 * @className: TrieTree
 * @package: com.zoumx.algo.tree.trietree
 * @author: admin
 * @date: 2020/2/1 23:15
 */
public class TrieNode {
    char data;
    public TrieNode[] children = new TrieNode[26];
    public boolean isEndingChar = false;

    public TrieNode(char data) {
        this.data = data;
    }
}
