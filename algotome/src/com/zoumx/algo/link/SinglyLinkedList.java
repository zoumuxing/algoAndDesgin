package com.zoumx.algo.link;

/**
 *  单例表涉及到的知识点<br>
 *
 * @className: SinglyLinkedList
 * @package: com.zoumx.algo.link
 * @author: admin
 * @date: 2020/2/26 14:36
 */
public class SinglyLinkedList {

    private Node head;

    private  class Node {
        private Node next;
        private int data;
        public Node(Node next, int data) {
            this.data = data;
            this.next = next;
        }
    }

    //无头结点插入法
    private void insertToHead(int value) {
        Node newNode = new Node(null,value);
        insertToHead(head);
    }

    private void insertToHead(Node newNode) {
        if(head == null) {
            head = newNode;
        }else {
            newNode.next = head;
            head = newNode;
        }
    }

    //尾部插入法
    private void insertToTail(int value) {
        Node newNode = new Node(null,value);
        if(head == null) {
            head = newNode;
        }else {
            Node q = head;
            //查找到尾节点
            while(q .next != null) {
                q = q.next;
            }
            q.next = newNode;
        }
    }

    //在某个节点之后插入
    private void insertAfter(Node p,int value) {
        Node newNode = new Node(null,value);
        insertAfter(p,newNode);
    }

    private void insertAfter(Node p,Node newNode) {
        if(p == null) {
            return;
        }
        newNode.next = p.next;
        p.next = newNode;
    }


    //在某个节点之前插入
    private void insertBefore(Node p,int value) {
        Node newNode = new Node(null,value);
        insertBefore(p,newNode);
    }

    private void insertBefore(Node p,Node newNode) {
        if(p == null) {
            return;
        }
        if(head == p) {
            insertToHead(newNode);
            return;
        }else {
            Node q = head;
            while (q.next != p) {
                q = q.next;
            }
            if(q == null) {
                return;
            }
            insertAfter(q,newNode);
        }
    }

}
