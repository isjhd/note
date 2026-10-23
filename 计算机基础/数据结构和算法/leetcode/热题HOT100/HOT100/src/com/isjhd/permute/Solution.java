package com.isjhd.permute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 思路：
// 使用一个boolean数组记录每个节点是否被记录
// 递归，只要有没有被访问的boolean数组就可以继续往下递归
// 回溯时，将boolean数组置为原来状态。
// 只修改集合，不用删除和增加集合。
public class Solution {


    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        boolean[] booleans = new boolean[nums.length];
        for(int i = 0; i < nums.length; i++) {
            integers.add(i);
        }
        back(nums, lists, integers, booleans,0);
        return lists;

    }

    private void back(int[] nums, List<List<Integer>> lists, List<Integer> integers, boolean[] booleans, int k) {

        if(k == nums.length) {
            lists.add(new ArrayList<>(integers));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if(!booleans[i]) {
                booleans[i] = true;
                integers.set(k, nums[i]);
                back(nums, lists, integers, booleans, k + 1);
                booleans[i] = false;
            }
        }

    }

}

class Test{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,3};
        List<List<Integer>> permute = solution.permute(nums);
        for(List<Integer> list : permute) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }
}