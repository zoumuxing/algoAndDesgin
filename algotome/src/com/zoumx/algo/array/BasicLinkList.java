package com.zoumx.algo.array;


/**
 * 实现单链表、循环链表、双向链表，支持增删操作
 * 实现单链表反转
 * 实现两个有序的链表合并为一个有序链表
 * 实现求链表的中间结点
 */
public class BasicLinkList {





    private class ListTwoWayNode<E> {
        Node head;
        private class Node {
            int data;
            Node next;
            Node prev;
            public void Node(int data) {
                this.data = data;
            }
        }

        //尾插法
        public void add(Node node) {
            if(null==head) {
                head = node;
            }else {
                Node p = head;
                if(p.next == null) {
                    p.next = node;
                    node.prev = p;
                }else {
                    while (p.next!=null) {
                        p = p.next;
                    }
                    p.next = node;
                    node.prev =p;
                }
            }

        }

        public void remove(Node node) {
            if(null==head) {
                return;
            }else {
                Node p = head;
                if(p.next == null) {
                    return;
                }
                if(p.next.data == node.data) {
                    p.next = null;
                }
               /* if(p.next.next.data == node.data) {
                    p.next.next = null;
                }*/
                while (p.next!=null) {
                    if(p.next.data == node.data) {
                        p = p.next.next;
                    }
                }
            }
        }


    }













    private class ListNode<E> {
       Node head;
       private class Node {
           int data;
           Node next;
           public void Node(int data) {
              this.data = data;
           }
       }

       public void add(Node node) {
           if(null==head) {
               head = node;
           }else {
               Node p = head;
               if(p.next == null) {
                   p.next = node;
               }else {
                   while (p.next!=null) {
                       p = p.next;
                   }
                   p.next = node;
               }
           }

       }

       public void remove(Node node) {
            if(null==head) {
                return;
            }else {
                Node p = head;
                if(p.next == null) {
                    return;
                }
                if(p.data == node.data) {
                    p = null;
                }else {
                  // Node q = findNode(p,node);

                }


                while (p.next!=null) {
                    if(p.next.data == node.data) {
                        p = p.next.next;
                    }
                }
            }
       }
        private Node findNode(Node node) {
            Node p = head;
            if(p.data == node.data) {
                head = null;
            }
            if(p.next == null) {
                return null;
            }

            while (p.next!=null) {
                if(p.next.data == node.data) {

                }
            }
            return null;
        }

        private void reverse() {
            Node cur = head;
            Node pre = null;
            while (cur!=null) {
                Node temp = pre;
                pre = cur;
                cur = cur.next;
                pre.next = temp;
            }
            head = pre;
        }
    }

}
