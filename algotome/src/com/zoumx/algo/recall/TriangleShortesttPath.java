package com.zoumx.algo.recall;

/**
 * 求三角形最短路径<br>
 *
 * @className: TriangleShortesttPath
 * @package: com.zoumx.algo.recall
 * @author: admin
 * @date: 2020/3/2 19:57
 */
public class TriangleShortesttPath {
    //定义层数
    private static int n = 5;
    //每层数值
    static int[][] matrix = {{5},{7,8},{2,3,4},{4,9,6,1},{2,7,9,4,5}};

    private static int minValue = 100000000;

    /**
     * @Author zoumx
     * @Description //回溯
     * @Date 13:17 2020/3/5
     * @Param [i, path, matrix, j]
     * @return void
     **/
    private void getMinTrangleValuePath(int i,int path,int[][] matrix,int j) {
        if(i == 5 && j == 5) {
            if(path < minValue) {
                System.out.println(path);
                minValue = path;
            }
            return;
        }
        for (; j < matrix.length; j++) {
            for (int k = 0; k < matrix[j].length; k++) {
                System.out.println(path + matrix[j][k]);
                    getMinTrangleValuePath(i + 1,path + matrix[j][k],matrix,j + 1);
            }
        }
    }

    /**
     * @Author zoumx
     * @Description //贪心
     * @Date 14:07 2020/3/5
     * @Param [i, path, matrix, j]
     * @return void
     **/
    private void getMinTrangleValuePath2(int i,int path,int[][] matrix,int j) {
        if(i == 5 && j == 5) {
            if(path < minValue) {
                System.out.println(path);
                minValue = path;
            }
            return;
        }
        for (; j < matrix.length; j++) {
            for (int k = 0; k < matrix[j].length; k++) {
                System.out.println(path + matrix[j][k]);
                getMinTrangleValuePath(i + 1,path + matrix[j][k],matrix,j + 1);
            }
        }
    }

    private int getMinValue(int[] matrix) {
        int min = 100000000;
        for (int i = 0;i< matrix.length;i++) {
            //进行比较
            if(matrix[i] < min) {
                min = matrix[i];
            }
        }
        return min;
    }



    /**
     * @Author zoumx
     * @Description //动态规划
     * @Date 14:07 2020/3/5
     * @Param [i, path, matrix, j]
     * @return void
     **/
    private int getMinTrangleValuePath1(int path,int[][] matrix) {

        for (int j = 0; j < matrix.length; j++) {
            //算出一维数组中最小值
            path += getMinValue(matrix[j]);
        }
        return path;
    }



    public static void main(String[] args) {
        TriangleShortesttPath triangleShortesttPath = new TriangleShortesttPath();
        triangleShortesttPath.getMinTrangleValuePath(0,0,matrix,0);
        System.out.println(minValue);
    }

}
