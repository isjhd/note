package com.isjhd.findMedianSortedArrays;

// 思路
// 二分查找
// 根据短的那边数组的left和right，来找到两个数组的中间的下标

// 思路：
// 通过 nums1数组 的中间值和 nums2数组 的中间值进行对比
// 对 nums2数组进行微调。

//    08 09 | 10 11
// 02 03 04 | 05 06

//          08 | 09 10 11
// 02 03 04 05 | 06

// 2  3  4  5
// 7  8  9  10 11

//     7 | 8
// 1 2 3 | 4 5 6
//         | 7 8
// 1 2 3 4 | 5 6

// 需要的是分隔符左边上面最大值和右边下面最小值进行比对
// 如果 左边 < 右边，left=middle+1，因为左边小于右边，所有可以确定最后的返回值。
// 如果 左边 > 右边，right=middle-1

// 返回值为 左边的最大 和 右边的最小
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if(nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int result_left = 0;
        int result_right = 0;
        int left = 0;
        int right = nums1.length - 1;
        while(left <= right) {

            int n1_mid = (left + right) / 2;
            int n2_mid = (nums1.length + nums2.length) / 2 - n1_mid;

            int left_n1 = n1_mid > 0 ? nums1[n1_mid - 1] : Integer.MIN_VALUE;
            int right_n1 = n1_mid < nums1.length ? nums1[n1_mid] : Integer.MAX_VALUE;
            int left_n2 = n2_mid > 0 ? nums2[n2_mid - 1] : Integer.MIN_VALUE;
            int right_n2 = n2_mid < nums2.length ? nums2[n2_mid] : Integer.MAX_VALUE;

            if(left_n1 < right_n2) {

                result_left = Math.max(left_n1, right_n1);
                result_right = Math.min(left_n2, right_n2);
                left = n1_mid + 1;
            } else {
                right = n1_mid - 1;
            }

        }

        return (nums1.length + nums2.length) % 2 == 0 ? (result_left + result_right) / 2.0 : result_right;

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findMedianSortedArrays(new int[]{}, new int[]{1});
    }
}