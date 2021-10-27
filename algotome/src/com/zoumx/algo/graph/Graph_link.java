package com.zoumx.algo.graph;

import java.util.*;

/**
 * 邻接表
 * 每个顶点维护一个链表，如果二个顶点产生关系，就分别作为值加入该链表顶点
 */
public class Graph_link { // 无向图

  private int v; // 顶点的个数
  private LinkedList<Integer> adj[]; // 邻接表

  public Graph_link(int v) {
    this.v = v;
    adj = new LinkedList[v];
    for (int i=0; i<v; ++i) {
      adj[i] = new LinkedList<>();
    }
  }

  public void addEdge(int s, int t) { // 无向图一条边存两次
    adj[s].add(t);
    adj[t].add(s);
  }




  public void bfs(int s, int t) {
    if (s == t) return;
    boolean[] visited = new boolean[v];
    visited[s]=true;
    Queue<Integer> queue = new LinkedList<>();
    queue.add(s);
    int[] prev = new int[v];
    for (int i = 0; i < v; ++i) {
      prev[i] = -1;
    }
    while (queue.size() != 0) {
      int w = queue.poll();
      for (int i = 0; i < adj[w].size(); ++i) {
        int q = adj[w].get(i);
        if (!visited[q]) {
          prev[q] = w;
          if (q == t) {
            print(prev, s, t);
            return;
          }
          visited[q] = true;
          queue.add(q);
        }
      }
    }
  }

  private void print(int[] prev, int s, int t) { // 递归打印s->t的路径
    //一直向上找到路径 知道t==s 此时向下递归打印路径 就是正向路径，因为存的是逆向路径
    if (prev[t] != -1 && t != s) {
      print(prev, s, prev[t]);
    }
    System.out.print(t + " ");
  }





  public void bfs1(int s,int t) {
    if(s==t) return;
    //定义一个已经访问的端点路径
    boolean visit[] = new boolean[v];
    //初始化访问路径
    for (int i = 0; i < v; i++) {
       visit[i] = false;
    }
    //定义一个存在路径结果的数组
    int [] pre = new int[v];
    for (int i = 0; i < v; i++) {
      pre[i] = -1;
    }
    //定义一个队列
    Queue<Integer> queue = new LinkedList<>();
    //增加初始化值
    queue.add(s);
    //进行队列的广度遍历
    while (!queue.isEmpty()) {
      int w = queue.poll();
      for (int i = 0; i < adj[w].size(); i++) {
        int q = adj[w].get(i);
        //如果这个顶点没有访问过
        if(!visit[q]) {
          //存一下访问路径
          pre[q] = w;
          //判断是否已经是目标系节点
          if(q==t) {
            //打印目标节点
            print1(pre,s,t);
            return;
          }
          //加入队列中
          queue.add(q);
          visit[q] = true;
        }
      }
    }
  }

  /**
   * 进行递归打印访问路径
   * @param pre
   * @param s
   * @param t
   */
  private void print1(int[] pre, int s, int t) {
    if(pre[t]!=-1&&s!=t) {
      print1(pre,s,pre[t]);
    }
    System.out.println(t + "");
  }








  boolean found = false; // 全局变量或者类成员变量

  public void dfs(int s, int t) {
    found = false;
    boolean[] visited = new boolean[v];
    int[] prev = new int[v];
    for (int i = 0; i < v; ++i) {
      prev[i] = -1;
    }
    recurDfs(s, t, visited, prev);
    print(prev, s, t);
  }

  private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
    if (found == true) return;
    visited[w] = true;
    if (w == t) {
      found = true;
      return;
    }
    for (int i = 0; i < adj[w].size(); ++i) {
      int q = adj[w].get(i);
      if (!visited[q]) {
        prev[q] = w;
        recurDfs(q, t, visited, prev);
      }
    }
  }








  /**
   * 深度优先--递归回溯
   */
  private void dfs1(int s,int t) {
   //跟广度搜索一样初始化前置条件
    boolean[] visit = new boolean[v];
    //初始化路径
    int[] pre = new int[v];
    for (int i = 0; i < v; i++) {
      pre[i] = -1;
    }
    //进行回溯递归遍历
    recurDfs1(pre,visit,s,t);
    //打印结果
    print(pre,s,t);

  }

  private boolean flag=false;
  private void recurDfs1(int[] pre,boolean[] visit, int s, int t) {
    //终止条件
    if(flag) {
      return;
    }
    if(s==t) {
      flag = true;
      return;
    }
    visit[s] = true;

    for (int i = 0; i < adj[s].size(); i++) {
           int q = adj[s].get(i);
           if(!visit[q]) {
             pre[q] = s;
             recurDfs1(pre,visit,q,t);
          }
    }

  }


  /**
   * 深度遍历栈的代码
   * @param start
   * @param end
   */
  public void dfsbyIteration(int start, int end) {
    if (start == end) {
      return;
    }
// 定义一个visited数组，保存是否访问过
    boolean[] visited = new boolean[v];
// 定义一个栈，作为一个后进先出栈，保存已访问，但相连节点未访问的节点
    Stack<Integer> stack = new Stack<Integer>();
// 先将start压入栈中
    visited[start] = true;
    stack.push(start);
    retry:
    while (!stack.isEmpty()) {
// 取出，但不出栈
      int step = stack.peek();
      for (int i = 0; i < adj[step].size(); i++) {
        int nextStep = adj[step].get(i);
// 存在未走过节点，则flag = false,将nextStep压入栈中
        if (visited[nextStep] == false) {
          visited[nextStep] = true;
          stack.push(nextStep);
          if (nextStep == end) {
            break retry;//找到终点，中断外循环
          }
          continue retry;//push了一个顶点就以该顶点为始点开始找，继续外循环
        }
      }
//step的最后一个顶点都没有到达终点end，将其弹出
      stack.pop();
    }
// 遍历输出栈中存储的节点，即路径
    if (!stack.isEmpty()) {
      Iterator<Integer> it = stack.iterator();
      while (it.hasNext()) {
        System.out.print(it.next() + " ");
      }
      System.out.println();
    }else{
      System.out.println(String.format("顶点[%d]到[%d]没有路径", new Object[]{start,end}));
    }
  }


  public static void main(String[] args) {
    Graph_link graph_link = new Graph_link(4);
    //graph_link.threeDegreeRelationDfs(5)

  }




  /**
   * 广度优先搜索
   * @param s 起始顶点
   * @return
   */
  public List<Integer> threeDegreeRelationBfs(int s) {
    boolean[] visited = new boolean[v];
    visited[s] = true;
    Queue<Integer> queue = new LinkedList<>();
    queue.add(s);
    List<Integer> result = new ArrayList<>();
    // 记录每个顶点到起始顶点的距离
    int[] degree = new int[v];
    // 记录总共遍历的次数
    int cycleNum = 0;
    // 广度优先搜索，遍历整张图
    while (!queue.isEmpty()) {
      Integer w = queue.poll();
      // 顶点离起始顶点距离超过 3 时则退出
      if (degree[w] == 3) {
        continue;
      }
      // 遍历其关注的顶点
      for (Integer q : adj[w]) {
        // 没有访问过的顶点进行记录
        if (!visited[q]) {
          visited[q] = true;
          // 添加到队列
          queue.add(q);
          // 记录当前顶点到起始顶点的距离，查找其前驱顶点的距离
          degree[q] = degree[w] + 1;
          cycleNum++;
          // 记录结果
          result.add(q);
        }
      }
    }
    System.out.println("广度优先搜索三度好友关系一共遍历的次数：" + cycleNum);
    return result;
  }

  /**
   * 深度优先搜索
   * <p>
   *
   * @param s 起始顶点
   */
  public List<Integer> threeDegreeRelationDfs(int s) {
    // 采用广度优先搜索算法
    boolean[] visited = new boolean[v];
    visited[s] = true;
    List<Integer> relations = new ArrayList<>();
    // 递归寻找顶点的关系
    int cycleNum = findDegreeRelation(visited, relations, s, 0);
    System.out.println("深度优先搜索三度好友关系一共遍历的次数：" + cycleNum);
    return relations;
  }

  /**
   * 递归寻找顶点关系——深度优先搜索
   *
   * @param visited
   * @param relations
   * @param p
   * @param level
   * @return
   */
  private int findDegreeRelation(boolean[] visited, List<Integer> relations, Integer p, int level) {
    if (level == 3) {
      return 1;
    }
    int cycleNum = 0;
    for (int h = 0; h < adj[p].size(); h++) {
      Integer t = adj[p].get(h);
      if (!visited[t]) {
        relations.add(t);
        visited[p] = true;
        cycleNum += findDegreeRelation(visited, relations, t, level + 1);
      }
      cycleNum++;
    }
    return cycleNum;
  }















}