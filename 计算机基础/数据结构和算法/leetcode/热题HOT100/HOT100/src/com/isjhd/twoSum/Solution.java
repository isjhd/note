package com.isjhd.twoSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//思路：
// 只需要找到两个整数
// 所以使用hashmap集合存储一个个值
// 然后遍历一个个值，target再减去遍历的值就行
// 如果没找到就添加到hashmap集合
public class Solution {
    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> maps = new HashMap<>();
        maps.put(nums[0], 0);

        for(int i = 1; i < nums.length; i++) {
            if(maps.containsKey(target - nums[i])) {
                return new int[]{maps.get(target - nums[i]), i};
            }
            maps.put(nums[i], i);
        }

        return null;
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = solution.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(Arrays.toString(ints));
    }
}
