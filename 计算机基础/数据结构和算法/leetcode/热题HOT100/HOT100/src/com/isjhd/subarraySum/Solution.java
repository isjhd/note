package com.isjhd.subarraySum;


import java.util.HashMap;
import java.util.Map;


// 思路
// 子数组是指一段连续的数组，不连续的不算
// 前缀和 - 前面某个前缀和 = 这段区间的和
// 用map集合存储，存每次叠加出现的数字，value存出现的次数
public class Solution {
    public int subarraySum(int[] nums, int k) {



        return -1;
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.subarraySum(new int[]{1,2,3}, 3);
        System.out.println(i);
    }
}
