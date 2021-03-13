package com.zoumx.algo.string;

/**
 * 〈功能概述〉字符串匹配枚举算法<br>
 *
 * @className: BF
 * @package: com.zoumx.algo.string
 * @author: admin
 * @date: 2020/1/18 22:24
 */
public class BF {

    public static void main(String[] args) {
        String parimary = "aaabcerdf";
        String pattern = "cer";
        int result = bfmatch(pattern,parimary);
        System.out.println(result);
    }

    /**
     * return the result of primary index
     * @param pattern
     * @param primary
     * @return
     */
    public static int bfmatch(String pattern,String primary) {

        int n = primary.length();
        int m = pattern.length();
        if (pattern.length() == 0 || primary.length() == 0) {
            return -1;
        }
        int i = 0;
        int j = 0;
        for (; i < n - m + 1;i++) {
            if(pattern.charAt(j) == primary.charAt(i)) {
                if(++j == m) {
                    return i - m;
                }
                continue;
            }else {
                j = 0;
            }
        }
        if(i == n - m + 1) return -1;
        return i;
    }
}
