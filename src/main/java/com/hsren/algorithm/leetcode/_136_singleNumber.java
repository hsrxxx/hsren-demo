package com.hsren.algorithm.leetcode;

/**
 *      给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *      你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例：
 *      输入: [2,2,1]
 *      输出: 1
 * 
 *      输入: [4,1,2,1,2]
 *      输出: 4
 *
 * 具体实现思路：
 *      异或符 "^" :
 *          运算法则：
 *              1. 归零律：a ^ a = 0
 *              2. 恒等律：a ^ 0 = a
 *              3. 交换律：a ^ b = b ^ a
 *              4. 结合律：a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c
 *              5. 自反：a ^ b ^ a = b
 *              6. d = a ^ b ^ c 可以推出 a = d ^ b ^ c
 *              7.若 x 是二进制数 0101，y 是二进制数 1011。
 *              则 x ^ y = 1110
 *              只有在两个比较的位不同时其结果是1，否则结果为0
 *              即 “两个输入相同时为0，不同则为1” ！
 *
 * 链接：https://leetcode-cn.com/problems/single-number/
 */
public class _136_singleNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{4,2,2,1,3,4,1,5,5};
        // 根据异或符的逻辑运算，先定义一个 a = 0;
        int a = 0;
        for(int num : nums){
            // 将 a 与 列表中所有值 进行异或运算。
            // 等于 4 ^ 2 ^ 2 ^ 1 ^ 3 ^ 4 ^ 1 ^ 5 ^ 5 （异或符运算法则中的自反：a ^ b ^ a = b）
            // 最终得出只出现一次的数字
            a = a ^ num;
        }
        System.out.println(a);

        // 扩展：
        // 1.交换变量：
        int x = 10;
        int y = 20;

        System.out.println("x:" + x + " | " + "y:" + y);
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        System.out.println("x:" + x + " | " + "y:" + y);
    }
}
