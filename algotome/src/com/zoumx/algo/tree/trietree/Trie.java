package com.zoumx.algo.tree.trietree;

/**
 *  implement for trietree<br>
 *
 * @className: Trie
 * @package: com.zoumx.algo.tree.trietree
 * @author: admin
 * @date: 2020/2/1 23:19
 */
public class Trie {

    private static TrieNode trieNode = new TrieNode('/');

    public static void main(String[] args) {
        String a = "hello";
        String b = "he";
        String c = "old";
        String pattern = "he";
        //covert char[]
        insert(a.toCharArray());
        insert(b.toCharArray());
        insert(c.toCharArray());
         if(find(pattern.toCharArray())) {
             System.out.println("find!");
         }
    }


    public static boolean find(char[] pattern) {
        TrieNode t = trieNode;
        for (int i = 0; i < pattern.length; i++) {
            int index = pattern[i] - 'a';
            if(t.children[index] == null) {
               return false;
            }
            t = t.children[index];
        }
        if(t.isEndingChar == false) {
            return false;
        }
        return true;
    }

    public static void insert(char[] text) {

        TrieNode p = trieNode;
        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
             if(p.children[index] == null) {
                 TrieNode trieNode = new TrieNode(text[i]);
                 p.children[index] = trieNode;
             }
             p = p.children[index];
        }
        p.isEndingChar = true;
    }
}
