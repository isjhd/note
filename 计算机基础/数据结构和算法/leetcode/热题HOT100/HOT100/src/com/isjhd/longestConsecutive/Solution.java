package com.isjhd.longestConsecutive;

import java.util.HashSet;
import java.util.Set;

// 思路
// 遍历每个数，利用两个变量++和--去寻找是否有连续的数。
// 注意：++ 和 -- 的值可以直接移除，因为用不上了。
class Solution {
    public int longestConsecutive(int[] nums) {

        Set<Integer> sets = new HashSet<>();

        for(int i : nums) {
            sets.add(i);
        }

        int result = 0;
        for(int i : nums) {

            int t_left = i - 1;
            int t_right = i + 1;

            while(sets.remove(t_left)) {
                t_left--;
            }

            while(sets.remove(t_right)) {
                t_right++;
            }

            result = Math.max(result, t_right - t_left - 1);
            if(sets.size() < result) {
                return result;
            }

        }

        return result;
    }
}

class Test{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {100,4,200,1,3,2};
        int i = solution.longestConsecutive(nums);
        System.out.println(i);
    }
}