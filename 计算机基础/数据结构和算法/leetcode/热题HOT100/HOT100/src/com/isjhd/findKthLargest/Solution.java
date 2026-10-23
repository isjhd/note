package com.isjhd.findKthLargest;

// 思路
// 采取分治算法(快速排序)

// 每次 向左找 和 向右找 都得直到找到比 指定值 还小 或 还大 的值

// 不过区别是需要向 k 值靠拢，总长 - k 就是找到第K大的元素
// 如果右下标比 k 大，就说明需要向左找
// 如果右下标比 k 小，就说明需要向右找

// 中间值不要让它在循环中发生变化
public class Solution {
    public int findKthLargest(int[] nums, int k) {

        return back(nums, nums.length - k, 0, nums.length - 1);

    }

    private int back(int[] nums, int k, int left, int right) {

        if(right == left) {
            return nums[k];
        }


        int l = left - 1;
        int r = right + 1;
        int mid = nums[(left + right) / 2];
        while(l < r) {

            do {
                l++;
            } while(nums[l] < mid);

            do {
                r--;
            } while(nums[r] > mid);

            if(l < r && nums[l] > nums[r]) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
            }

        }

        if(k <= r) {
            return back(nums, k, left, r);
        } else {
            return back(nums, k, r + 1, right);
        }
    }

}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] _nums = {3,2,3,1,2,4,5,5,6};
        int k = 2;
        int kthLargest = solution.findKthLargest(_nums, k);
        System.out.println(kthLargest);
    }
}