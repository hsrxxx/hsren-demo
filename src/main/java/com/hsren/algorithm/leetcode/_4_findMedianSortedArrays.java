package com.hsren.algorithm.leetcode;

import java.util.Arrays;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 * 示例：
 *      输入：nums1 = [1,3], nums2 = [2]
 *      输出：2.00000
 *      解释：合并数组 = [1,2,3] ，中位数 2
 *
 *      输入：nums1 = [1,2], nums2 = [3,4]
 *      输出：2.50000
 *      解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 *      输入：nums1 = [0,0], nums2 = [0,0]
 *      输出：0.00000
 *
 *      输入：nums1 = [], nums2 = [1]
 *      输出：1.00000
 *
 *      输入：nums1 = [2], nums2 = []
 *      输出：2.00000
 *
 * 提示：
 *      nums1.length == m
 *      nums2.length == n
 *      0 <= m <= 1000
 *      0 <= n <= 1000
 *      1 <= m + n <= 2000
 *      -106 <= nums1[i], nums2[i] <= 106
 *
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 */
public class _4_findMedianSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{3,2};
        System.out.println(findMedianSortedArrays1(nums1, nums2));
        System.out.println(findMedianSortedArrays2(nums1, nums2));
    }

    /**
     * 硬磕成功
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 返回2个数组合并后的中位数
     */
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        // 获取2数组的长度作初始化
        int numLength = nums1.length + nums2.length;
        int[] nums = new int[numLength];
        // 将2个数组合并 并 排序
        System.arraycopy(nums1, 0 ,nums,0,nums1.length);
        System.arraycopy(nums2, 0 ,nums,nums1.length,nums2.length);
        Arrays.sort(nums);
        // 返回中位数
        if (nums.length == 1) return nums[0];
        return (nums[(numLength + 1) / 2 - 1] + nums[(numLength + 2) / 2 - 1]) / 2.0;
    }

    /**
     * 参考答案
     * 思路：
     * 这道题让我们求两个有序数组的中位数，而且限制了时间复杂度为O(log (m+n))，
     * 看到这个时间复杂度，自然而然的想到了应该使用二分查找法来求解。
     * 那么回顾一下中位数的定义，如果某个有序数组长度是奇数，那么其中位数就是最中间那个，
     * 如果是偶数，那么就是最中间两个数字的平均值。这里对于两个有序数组也是一样的，
     * 假设两个有序数组的长度分别为m和n，由于两个数组长度之和 m+n 的奇偶不确定，
     * 因此需要分情况来讨论，对于奇数的情况，直接找到最中间的数即可，偶数的话需要求最中间两个数的平均值。
     * 为了简化代码，不分情况讨论，我们使用一个小trick，我们分别找第 (m+n+1) / 2 个，和 (m+n+2) / 2 个，
     * 然后求其平均值即可，这对奇偶数均适用。加入 m+n 为奇数的话，那么其实 (m+n+1) / 2 和 (m+n+2) / 2 的值相等，
     * 相当于两个相同的数字相加再除以2，还是其本身。
     *
     * 这里我们需要定义一个函数来在两个有序数组中找到第K个元素，
     * 下面重点来看如何实现找到第K个元素。首先，为了避免产生新的数组从而增加时间复杂度，
     * 我们使用两个变量i和j分别来标记数组nums1和nums2的起始位置。然后来处理一些边界问题，
     * 比如当某一个数组的起始位置大于等于其数组长度时，说明其所有数字均已经被淘汰了，相当于一个空数组了，
     * 那么实际上就变成了在另一个数组中找数字，直接就可以找出来了。还有就是如果K=1的话，
     * 那么我们只要比较nums1和nums2的起始位置i和j上的数字就可以了。难点就在于一般的情况怎么处理？
     * 因为我们需要在两个有序数组中找到第K个元素，为了加快搜索的速度，我们要使用二分法，对K二分，
     * 意思是我们需要分别在nums1和nums2中查找第K/2个元素，注意这里由于两个数组的长度不定，
     * 所以有可能某个数组没有第K/2个数字，所以我们需要先检查一下，数组中到底存不存在第K/2个数字，
     * 如果存在就取出来，否则就赋值上一个整型最大值。如果某个数组没有第K/2个数字，那么我们就淘汰另一个数字的前K/2个数字即可。
     * 有没有可能两个数组都不存在第K/2个数字呢，这道题里是不可能的，因为我们的K不是任意给的，而是给的m+n的中间值，
     * 所以必定至少会有一个数组是存在第K/2个数字的。最后就是二分法的核心啦，比较这两个数组的第K/2小的数字midVal1和midVal2的大小，
     * 如果第一个数组的第K/2个数字小的话，那么说明我们要找的数字肯定不在nums1中的前K/2个数字，所以我们可以将其淘汰，
     * 将nums1的起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归。反之，我们淘汰nums2中的前K/2个数字，
     * 并将nums2的起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归即可。
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 返回2个数组合并后的中位数
     */
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        // 记录长度
        int m = nums1.length;
        int n = nums2.length;
        // 获取中位数下标 2 2
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    //i: nums1的起始位置 j: nums2的起始位置
    public static int findKth(int[] nums1, int i, int[] nums2, int j, int k){
        // 如果其中一个数组为空，则返回另外一个数组的中位数 因为下标开始为 0 需要 -1
        if( i >= nums1.length) return nums2[j + k - 1];//nums1为空数组
        if( j >= nums2.length) return nums1[i + k - 1];//nums2为空数组
        // 如果 K 等于 1 表明总长度只有 1 返回
        if(k == 1){
            return Math.min(nums1[i], nums2[j]);
        }
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if(midVal1 < midVal2){
            return findKth(nums1, i + k / 2, nums2, j , k - k / 2);
        }else{
            return findKth(nums1, i, nums2, j + k / 2 , k - k / 2);
        }
    }
}
