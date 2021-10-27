package com.zoumx.algo.tree.binary;

import java.util.*;

public class BinaryTree {

    private Node root;


    private class Node {
        private Node left;
        private Node right;
        private int data;

        public Node(Node left, Node right, int data) {
            this.left = left;
            this.right = right;
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        Node root = binaryTree.init();
        //所谓得各个顺序遍历是针对根元素得得顺序，
        //前序遍历 --先遍历根  代码是思想得体现，遍历得节点也说从相对节点来讲每个作为根节点的顺序
        //而且每个节点最多遍历2次
        binaryTree.preOrder(root);
        System.out.println("前序遍历结束======》");
        //中序遍历--中间遍历根
        binaryTree.inOrder(root);
        System.out.println("中序遍历结束======》");
        //后序遍历--最后遍历根
        binaryTree.postOrder(root);
        System.out.println("后序遍历结束======》");
        //层级遍历--广度优先--借助队列实现
        binaryTree.bfsOrder(root);
        System.out.println("bfs层级遍历结束=====》");
        //深度优先遍历---递归
        binaryTree.dfsResuOrder(root);
        System.out.println("dfs遍历结束=====》");
        //深度优先遍历---栈
        binaryTree.dfsStackOrder(root);
        System.out.println("dfs stack遍历结束=====》");
        //判断二数之和是否等于目标值
        boolean result = binaryTree.twoSumTree(root,15);
        System.out.println("目标值：" + result);
        boolean result1 = binaryTree.twoSumTree(root,3);
        System.out.println("目标值：" + result1);
        //判断二数之和是否等于目标值
        boolean result2 = binaryTree.twoSumDoublePointTree(root,15);
        System.out.println("目标值：" + result2);
        boolean result3 = binaryTree.twoSumDoublePointTree(root,3);
        System.out.println("目标值：" + result3);
    }

    List<Integer> list = new ArrayList<>();
    /**
     * 进行排序  在采用双指针
     * @param root
     * @param k
     * @return
     */
    private boolean twoSumDoublePointTree(Node root, int k) {
        //进行中序遍历
        preOrderList(root);
        int start = 0;
        int end = list.size() -1;
        while (start<end) {
            int tmp = list.get(start) + list.get(end);
            if(tmp>k) {
                end--;
            }else if(tmp<k) {
                start++;
            }else {
                return true;
            }
        }
      return false;
    }

    private void preOrderList(Node root) {
        if(null == root) {
            return;
        }
        list.add(root.data);
        preOrderList(root.left);
        preOrderList(root.right);
    }

    private void dfsStackOrder(Node root) {
       Stack stack = new Stack();
       stack.add(root);
       while (!stack.empty()) {
           Node q = (Node) stack.pop();
           System.out.println(q.data);
           if(null!=q.left) {
               stack.add(q.left);
           }
           if(null!=q.right) {
               stack.add(q.right);
           }
       }

    }

    private void dfsResuOrder(Node root) {
        if(null == root) {
            return;
        }
        System.out.println(root.data);
        dfsResuOrder(root.left);
        dfsResuOrder(root.right);
    }

    private void bfsOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node q = queue.poll();
            System.out.println(q.data);
            if(q.left!=null) {
                queue.add(q.left);
            }
            if(q.right!=null) {
                queue.add(q.right);
            }
        }

    }


    private void preOrder(Node root) {
        if(root==null){
            return;
        }
        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }


    private void inOrder(Node root) {
        if(root==null){
            return;
        }
        inOrder(root.left);
        System.out.println(root.data);
        inOrder(root.right);
    }

    private void postOrder(Node root) {
        if(root==null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);

    }




    private Node init() {
        Node left1 = new Node(null,null,4);
        Node right1 = new Node(null,null,5);
        Node left = new Node(left1,right1,2);

        Node left2 = new Node(null,null,6);
        Node right2 = new Node(null,null,7);
        Node right = new Node(left2,right2,3);
        Node root = new Node(left,right,1);
        return root;
     }



    Set<Integer> set = new HashSet<>();

    /**
     * 查寻二数之和等于目标数
     * @param node
     * @param target
     */
    public boolean twoSumTree(Node node,int target) {
       if(null == node) {
        return false;
       }
       if(set.contains(target-node.data)) {
           return true;
       }
       set.add(node.data);
      return twoSumTree(node.left,target) | twoSumTree(node.right,target);

    }

















}
