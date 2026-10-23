package com.isjhd.subsets;

import java.util.ArrayList;
import java.util.List;

// 思路：
// 递归
// 每次递归都向前加一
public class Solution {

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        back(nums, lists, integers, 0);
        return lists;

    }

    private void back(int[] nums, List<List<Integer>> lists, List<Integer> integers, int k) {

        if(k == nums.length) {
            lists.add(new ArrayList<>(integers));
            return;
        }

        lists.add(new ArrayList<>(integers));

        for(int i = k; i < nums.length; i++) {
            integers.add(nums[i]);
            back(nums, lists, integers, i + 1);
            integers.remove(integers.size() - 1);
        }


    }

}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,3};
        List<List<Integer>> subsets = solution.subsets(nums);
        for(List<Integer> subset : subsets) {
            System.out.println(subset.toString());
        }
    }
}