package com.zoumx.algo.dynamicprograming;

/**
 * 〈功能概述〉<br>
 *
 * @className: LwstDistance
 * @package: com.zoumx.algo.dynamicprograming
 * @author: admin
 * @date: 2020/3/7 13:15
 */
public class LwstDistance {

    private char[] a = "mitcmu".toCharArray();
    private char[] b = "mtacnu".toCharArray();
    private int n = 6;
    private int m = 6;
    private static int minDist = Integer.MAX_VALUE; // 存储结果


    public static void main(String[] args) {
        LwstDistance lwstDistance = new LwstDistance();
        lwstDistance.lwstBT(0,0,0);
        System.out.println(minDist);
    }

    // 调用方式 lwstBT(0, 0, 0);
    public void lwstBT(int i, int j, int edist) {
        if (i == n || j == m) {
            if (i < n) {
                edist += (n - i);
            }
            if (j < m) {
                edist += (m - j);
            }
            if (edist < minDist) {
                minDist = edist;
            }
            return;
        }
        if (a[i] == b[j]) { // 两个字符匹配
            lwstBT(i+1, j+1, edist);
        } else { // 两个字符不匹配
            //列举各种情况，进行递归判断最小距离情况
            lwstBT(i + 1, j, edist + 1); // 删除a[i]或者b[j]前添加一个字符
            lwstBT(i, j + 1, edist + 1); // 删除b[j]或者a[i]前添加一个字符
            lwstBT(i + 1, j + 1, edist + 1); // 将a[i]和b[j]替换为相同字符
        }
    }



    public int lwstDP(char[] a, int n, char[] b, int m) {
        int[][] minDist = new int[n][m];
        for (int j = 0; j < m; ++j) { // 初始化第0行:a[0..0]与b[0..j]的编辑距离
            if (a[0] == b[j]) {
                minDist[0][j] = j;
            }
            else if (j != 0) {
                minDist[0][j] = minDist[0][j-1]+1;
            }
            else {
                minDist[0][j] = 1;
            }
        }
        for (int i = 0; i < n; ++i) { // 初始化第0列:a[0..i]与b[0..0]的编辑距离
            if (a[i] == b[0]) {
                minDist[i][0] = i;
            }
            else if (i != 0) {
                minDist[i][0] = minDist[i-1][0]+1;
            }
            else {
                minDist[i][0] = 1;
            }
        }
        for (int i = 1; i < n; ++i) { // 按行填表
            for (int j = 1; j < m; ++j) {
                if (a[i] == b[j]) {
                    minDist[i][j] = min(
                            minDist[i-1][j]+1, minDist[i][j-1]+1, minDist[i-1][j-1]);
                }
                else {
                    minDist[i][j] = min(
                            minDist[i-1][j]+1, minDist[i][j-1]+1, minDist[i-1][j-1]+1);
                }
            }
        }
        return minDist[n-1][m-1];
    }

    private int min(int x, int y, int z) {
        int minv = Integer.MAX_VALUE;
        if (x < minv) {
            minv = x;
        }
        if (y < minv) {
            minv = y;
        }
        if (z < minv) {
            minv = z;
        }
        return minv;
    }



    public int lcs(char[] a, int n, char[] b, int m) {
        int[][] maxlcs = new int[n][m];
        for (int j = 0; j < m; ++j) {//初始化第0行：a[0..0]与b[0..j]的maxlcs
            if (a[0] == b[j]) {
                maxlcs[0][j] = 1;
            }
            else if (j != 0) {
                maxlcs[0][j] = maxlcs[0][j-1];
            }
            else {
                maxlcs[0][j] = 0;
            }
        }
        for (int i = 0; i < n; ++i) {//初始化第0列：a[0..i]与b[0..0]的maxlcs
            if (a[i] == b[0]) {
                maxlcs[i][0] = 1;
            }
            else if (i != 0) {
                maxlcs[i][0] = maxlcs[i-1][0];
            }
            else {
                maxlcs[i][0] = 0;
            }
        }
        for (int i = 1; i < n; ++i) { // 填表
            for (int j = 1; j < m; ++j) {
                if (a[i] == b[j]) {
                    maxlcs[i][j] = max(
                            maxlcs[i-1][j], maxlcs[i][j-1], maxlcs[i-1][j-1]+1);
                }
                else {
                    maxlcs[i][j] = max(
                            maxlcs[i-1][j], maxlcs[i][j-1], maxlcs[i-1][j-1]);
                }
            }
        }
        return maxlcs[n-1][m-1];
    }

    private int max(int x, int y, int z) {
        int maxv = Integer.MIN_VALUE;
        if (x > maxv) {
            maxv = x;
        }
        if (y > maxv) {
            maxv = y;
        }
        if (z > maxv) {
            maxv = z;
        }
        return maxv;
    }
}
