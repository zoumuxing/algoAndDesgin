package com.zoumx.algo.graph;

/**
 * 邻接矩阵--图
 */
public class Graph_array {

    private int v;
    private int[] [] graph;

    public void Graph_array(int v) {
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                graph[i][j]=0;
            }
        }
    }

    public void addEdge(int s,int t) {
          graph[s-1][t-1]=1;
    }

}
