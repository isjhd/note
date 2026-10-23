package com.isjhd.lengthOfLIS;


// 思路：
// 先使用一个新数组记录单调递增的值
// 然后一个一个遍历，和新数组里面的值进行二分查找，该在什么位置在什么位置。
// 如果比新数组里面的值小，则替换，如果比新数组里的值大，则size+1。
class Solution {
    public int lengthOfLIS(int[] nums) {

        int[] nums_copy = new int[nums.length + 1];

        int result = 0;
        nums_copy[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {

            if(nums[i] > nums_copy[result]) {
                nums_copy[++result] = nums[i];
            } else if(nums[i] != nums_copy[result]) {
                int left = 0;
                int right = result;
                while (left < right) {

                    int mid = (left + right) / 2;
                    if (nums_copy[mid] < nums[i]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }

                }

                nums_copy[right] = nums[i];

            }

        }

        return result + 1;

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {10,9,2,5,3,7,101,18};
        int i = solution.lengthOfLIS(nums);
        System.out.println(i);
    }
}