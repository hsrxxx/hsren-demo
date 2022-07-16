package com.hsren.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 示例：
 *      输入：s = "babad"
 *      输出："bab"
 *      解释："aba" 同样是符合题意的答案。
 *
 *      输入：s = "cbbd"
 *      输出："bb"
 *
 *      输入：s = "a"
 *      输出："a"
 *
 *      输入：s = "ac"
 *      输出："a"
 *
 * 提示：
 *      1 <= s.length <= 1000
 *      s 仅由数字和英文字母（大写和/或小写）组成
 *
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 */
public class _5_longestPalindrome {
    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        //System.out.println(new ArrayList<>().size());
        System.out.println(longestPalindrome1(s));
        System.out.println(longestPalindrome1(s).length());
        System.out.println(s.length());
    }

    /**
     * 硬磕勉强成功，超出限制时间不通过。
     * @param s 字符串
     * @return 字符串的最长回文子串
     */
    public static String longestPalindrome1(String s){
        HashMap<Character,ArrayList<Integer>> lastMap = new HashMap<>();

        int n = s.length();

        String result = "";

        int lengthS = 0;
        for(int i = 0; i < n; i++){
            ArrayList<Integer> arr;

            if (!lastMap.containsKey(s.charAt(i))){
                arr = new ArrayList<>();
            } else {
                arr = lastMap.get(s.charAt(i));
            }

            if (arr.size() > 0){
                for (int a:arr) {
                    String x = s.substring(a, i + 1);
                    if(x.equals(new StringBuilder(x).reverse().toString())){
                        if(x.length() > lengthS){
                            lengthS = x.length();
                            result = x;
                        }
                    }
                }
            }

            arr.add(i);
            lastMap.put(s.charAt(i), arr);
        }
        if (lengthS == 0){
            return s.substring(0,1);
        }
        return result;
    }


}
