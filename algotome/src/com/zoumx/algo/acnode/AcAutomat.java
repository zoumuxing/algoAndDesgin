package com.zoumx.algo.acnode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 〈功能概述〉字符串多模式串匹配法<br>
 *
 * @className: AcAutomat
 * @package: com.zoumx.algo.acnode
 * @author: admin
 * @date: 2020/2/18 0:10
 */
public class AcAutomat {

   private  static AcNode root = new AcNode('/');
   private static final Integer LEN = 26;

    public static void main(String[] args) {
        String a = "hello";
        String b = "he";
        String c = "old";
        String pattern = "heallo";
        //covert char[]
        insert(a.toCharArray());
        insert(b.toCharArray());
        insert(c.toCharArray());
        //构建失败指针
        buildFailurePointer();
        //进行匹配
        match(pattern.toCharArray());
    }


    /*
    public void bulidFailurePointer() {
        //把root加入到队列中
        Queue<AcNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            //取得子节点
            AcNode p = queue.remove();
            for (int i = 0; i < LEN; i++) {
                AcNode pc = p.children[i];
                //取得失败指针
                if(p == root) {
                    p.fail = root;
                    continue;
                }else {
                    AcNode q = p.fail;
                    while (q != null) {
                        AcNode qc = q.children[pc.data - 'a'];
                        if(qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if(q == null) {
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }*/


    public static void buildFailurePointer() {
        Queue<AcNode> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            AcNode p = queue.remove();
            for (int i = 0; i < LEN; ++i) {
                AcNode pc = p.children[i];
                if (pc == null) {continue;}
                if (p == root) {
                    pc.fail = root;
                } else {
                    AcNode q = p.fail;
                    while (q != null) {
                        AcNode qc = q.children[pc.data - 'a'];
                        if (qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if (q == null) {
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }


    public static void insert(char[] text) {
        AcNode p = root;
        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            if(p.children[index] == null) {
                AcNode trieNode = new AcNode(text[i]);
                p.children[index] = trieNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
        p.length = text.length;
    }

/*   public void match1(char[] text) {
        AcNode p = root;
       for (int i = 0; i < text.length; i++) {
           int idx = text[i] = 'a';
             //判断是否需要指向下一次的失效指针
           while(p != root && p.children[idx] == null) {
               p = p.fail;
           }
           //重新开始匹配
           if(p == null) {
               p = root;
           }
           //进行匹配
           AcNode temp = p;
           while (temp != root) {
             if(tmp.isEndingChar == true) {
                 int post = i - temp.length + 1;
                  System.out.println(post);
             }
             temp = temp.fail;
           }
       }
   }*/



    public static void match(char[] text) { // text是主串
        int n = text.length;
        AcNode p = root;
        for (int i = 0; i < n; ++i) {
            int idx = text[i] - 'a';
            while (p.children[idx] == null && p != root) {
                /**失败指针发挥作用的地方**/
                p = p.fail;
            }
            p = p.children[idx];
            /**如果没有匹配的，从root开始重新匹配**/
            if (p == null) {
                p = root;
            }
            AcNode tmp = p;
            /**打印出可以匹配的模式串**/
            while (tmp != root) {
                if (tmp.isEndingChar == true) {
                    int pos = i-tmp.length+1;
                    System.out.println("匹配起始下标" + pos + "; 长度" + tmp.length);
                }
                tmp = tmp.fail;
            }
        }
    }
}
