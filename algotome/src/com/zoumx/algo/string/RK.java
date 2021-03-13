package com.zoumx.algo.string;


/**
 * RK implementing string matching<br>
 *
 * @className: RK   algo complexity log(n)
 * @package: com.zoumx.algo.string
 * @author: admin
 * @date: 2020/1/21 14:25
 */
public class RK {

    public static void main(String[] args) {
        String pattern = "abc";
        String primary = "uuueamabc";
        int result = rkmatch(pattern,primary);
        System.out.println(result);
    }

    /**
     * @Author zoumx
     * @Description //get the index of primary
     * if return -1 no result
     * @Date 15:00 2020/1/21
     * @Param [pattern, primary]
     * @return int
     **/
    public static int rkmatch(String pattern,String primary) {
        int n = primary.length();
        int m = pattern.length();
        //check
        if(n < m) {
            throw new RuntimeException("the primary string cannot be less than the pattern string!");
        }
        if(pattern.length() == 0 || primary.length() == 0) {
            throw new RuntimeException("the computed string cannot be empty");
        }
        //calc pattern hash
        int phash = rkhash(pattern);
        int nhash = rkhash(primary.substring(0,m).toString());
        if(phash == nhash) {
            return 0;
        }
        for (int i = 1; i < n - m + 1; i++) {
             nhash = nextHash(i,nhash,m-1,primary);
            if(phash == nhash && compareString(i,primary,pattern)) {
                return i;
            }
            if(i > n - m) {
                return -1;
            }
        }

        return 0;
    }

    /**
     * @Author zoumx
     * @Description //compare the really value
     * @Date 15:53 2020/1/21
     * @Param [i, m, phash]
     * @return boolean
     **/
    private static boolean compareString(int i, String primary, String pattern) {
          String result = primary.substring(i,i + pattern.length());
          return result.equals(pattern)?true:false;
    }

    /**
     * @Author zoumx
     * @Description //calculate the next hashcode of String
     * @Date 15:32 2020/1/21
     * @Param [i, nhash, m, primary]
     * @return int
     **/
    private static int nextHash(int i, int nhash,int m, String primary) {
        return nhash - calcStringHashCodeByIndex(primary,i-1) + calcStringHashCodeByIndex(primary,i + m);
    }

    /**
     * @Author zoumx
     * @Description //calclate the hashcode by comparing with a
     * @Date 15:32 2020/1/21
     * @Param [obj, i]
     * @return int
     **/
    private static int calcStringHashCodeByIndex(String obj, int i) {
        return obj.charAt(i) - 'a' + 1;
    }

    /**
     * @Author zoumx
     * @Description //Simple hash calculation, a equal 1,z equal 26
     * @Date 15:05 2020/1/21
     * @Param [pattern]
     * @return int
     **/
    private static int rkhash(String pattern) {
        int sum = 0;
        for (int i = 0; i < pattern.length(); i++) {
            sum += calcStringHashCodeByIndex(pattern,i);
        }
        return sum;
    }

}
