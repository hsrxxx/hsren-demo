package com.hsren.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.两数之和
 *      给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值的那两个整数，并返回它们的数组下标。
 *      你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *      你可以按任意顺序返回答案。
 *
 * 示例：
 *      输入：nums = [2,7,11,15], target = 9
 *      输出：[0,1]
 *      解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 *      输入：nums = [3,2,4], target = 6
 *      输出：[1,2]
 *
 *      输入：nums = [3,3], target = 6
 *      输出：[0,1]
 *
 * 提示：
 *      2 <= nums.length <= 103
 *      -109 <= nums[i] <= 109
 *      -109 <= target <= 109
 *      只会存在一个有效答案
 *
 * 链接：https://leetcode-cn.com/problems/two-sum
 */
public class _1_twoSum {

    public static void main(String[] args) {
        // 整数数组
        int[] nums = new int[]{3551,123,4312,331,33,123,1,2,3,4,5,6,7,8,9,11,189,582,790,263,225,124,578,317,685,673,111,33334,251765,12311};
        // 整数目标值
        int target = 3551+1;

        int[] array;
        array = twoSum1(nums,target);
        System.out.println(Arrays.toString(array));

        array = twoSum2(nums,target);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 参考：HaskMap特性
     * @param nums 整数数组
     * @param target 整数目标值
     * @return 返回长度为2的 nums 下标数组，下标对应 nums 的值相加等于整数目标值 target
     */
    public static int[] twoSum1(int[] nums, int target) {
        // 定义一个 HaskMap
        Map<Integer, Integer> existMap = new HashMap<>();
        // 循环整数数组
        for(int i = 0; i < nums.length; i++){
            // 判断整数目标值减去循环的值是否是否存在 HaskMap 中
            // 如果不存在则将循环的值添加到 HaskMap 中
            // 随着 HaskMap 的增加，后续循环只要找到存在 HaskMap 中的差异即可出结果
            if (existMap.containsKey(target - nums[i])){
                return new int[]{existMap.get(target - nums[i]), i};
            }
            existMap.put(nums[i], i);
        }
        return new int[]{-1,-1};
    }

    /**
     * 硬磕解决
     * @param nums 整数数组
     * @param target 整数目标值
     * @return 返回长度为2的 nums 下标数组，下标对应 nums 的值相加等于整数目标值 target
     */
    public static int[] twoSum2(int[] nums, int target) {
        String string = "";
        for(int i = 0; i < nums.length; i++){
            int isIndexOf = string.indexOf("_" + (target - nums[i]) + "_");
            string += "_" + nums[i] + "_" + i + ",";
            if(isIndexOf != -1){
                String index = string.substring(isIndexOf).split(",")[0].split("_")[2];
                return new int[]{Integer.parseInt(index),i};
            }
        }
        return new int[]{-1,-1};
    }
}
