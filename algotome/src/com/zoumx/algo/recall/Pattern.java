package com.zoumx.algo.recall;

/**
 * @Author zoumx
 * @Description //正则表达式的匹配算法用的就是回溯算法
 * @Date 21:23 2020/2/29
 * @Param
 * @return
 **/
public class Pattern {
  private boolean matched = false;
  private char[] pattern; // 正则表达式
  private int plen; // 正则表达式长度

  public static void main(String[] args) {
    char[] pchar = {'a','*'};
    Pattern pattern = new Pattern(pchar,pchar.length);
    //主串
    char [] primaryChar = {'a','b','c','a'};
    boolean result = pattern.match(primaryChar,primaryChar.length);
    System.out.println(result);
  }

  public Pattern(char[] pattern, int plen) {
    this.pattern = pattern;
    this.plen = plen;
  }

  public boolean match(char[] text, int tlen) { // 文本串及长度
    matched = false;
    rmatch(0, 0, text, tlen);
    return matched;
  }

  private void rmatch(int ti, int pj, char[] text, int tlen) {
    // 如果已经匹配了，就不要继续递归了
    if (matched) {return;}
    // 正则表达式到结尾了
    if (pj == plen) {
      // 文本串也到结尾了
      if (ti == tlen) {matched = true;}
      return;
    }
    // *匹配任意个字符
    if (pattern[pj] == '*') {
      for (int k = 0; k <= tlen-ti; ++k) {
        rmatch(ti+k, pj+1, text, tlen);
      }
      // ?匹配0个或者1个字符
    } else if (pattern[pj] == '?') {
      rmatch(ti, pj+1, text, tlen);
      rmatch(ti+1, pj+1, text, tlen);
      // 纯字符匹配才行
    } else if (ti < tlen && pattern[pj] == text[ti]) {
      rmatch(ti+1, pj+1, text, tlen);
    }
  }
}