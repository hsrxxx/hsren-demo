package com.hsren.algorithm.leetcode;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例：
 *      输入: s = "abcabcbb"
 *      输出: 3
 *      解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 *      输入: s = "bbbbb"
 *      输出: 1
 *      解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 *      输入: s = "pwwkew"
 *      输出: 3
 *      解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *           请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 *      输入: s = ""
 *      输出: 0
 *  cbaabc
 * 提示：
 *      0 <= s.length <= 5 * 104
 *      s 由英文字母、数字、符号和空格组成
 */
public class _3_lengthOfLongestSubstring {
    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring1(s));
        System.out.println(lengthOfLongestSubstring2(s));
        int[] last = new int[128];
        for (int i : last){
            System.out.println(i);
        }
    }

    /**
     * 硬磕成功
     * 执行用时：18 ms, 在所有 Java 提交中击败了17.21%的用户
     * 内存消耗：44.2 MB, 在所有 Java 提交中击败了5.02%的用户
     * @param s 字符串
     * @return 返回参数 s 不含有重复字符的 最长子串 的长度。
     */
    public static int lengthOfLongestSubstring1(String s) {
        if (s.equals("")) return 0;
        String result = "";
        int resultLength = 0;
        int strLength = 0;
        String[] strs = s.split("");
        for (String str : strs) {
            if (result.contains(str)){
                result = result.substring(result.indexOf(str) + 1) + str;
                strLength = result.length();
            }else{
                result += str;
                strLength += 1;
            }
            if (strLength > resultLength) resultLength = strLength;
        }
        return resultLength;
    }


    /**
     * 参考答案
     * 通过char字符数组巧妙的把每个字符的位置保存进行计算
     * 每当出现重复字符时，将长度减去字符最早出现的位置，以保证不存在相同字符。
     * @param s 字符串
     * @return 返回参数 s 不含有重复字符的 最长子串 的长度。
     */
    public static int lengthOfLongestSubstring2(String s) {
        // 此处的数组为一个下标为字符char数值的数组，包含字符串内的所有字符即可
        // 记录字符上一次出现的位置
        int[] last = new int[128]; // 默认值为0
        // 获取字符串长度用于循环
        int n = s.length();
        // 初始化长度
        int res = 0;
        // 初始化开始位置
        int start = 0;
        for(int i = 0; i < n; i++) {
            // 将字符的char数值获取
            int index = s.charAt(i);
            // 返回字符出现的位置，如果为0则代表还未出现重复字符
            start = Math.max(start, last[index]);
            // 根据已计算的长度和字符再次出现的位置计算目前长度
            // +1 是因为开始下标为 0 导致字符串长度为 1 时为了防止 res 为 0 的情况
            res   = Math.max(res, i - start + 1);
            // 将每个字符的位置保存
            // +1 是为了迎合上一步的问题
            last[index] = i + 1;
        }
        return res;
    }
}