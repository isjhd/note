package com.isjhd.rotate1;

// 先翻转整个数组
// 然后按照k的下标，分成左右边，分别翻转两次
public class Solution {
    public void rotate(int[] nums, int k) {

        k %= nums.length;
        back(nums, 0, nums.length - 1);
        back(nums, 0, k - 1);
        back(nums, k, nums.length - 1);

    }

    private void back(int[] nums, int left, int right) {
        while(left < right) {
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
            left++;
            right--;
        }
    }

}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.rotate(new int[]{1,2,3,4,5,6,7}, 10);
    }
}