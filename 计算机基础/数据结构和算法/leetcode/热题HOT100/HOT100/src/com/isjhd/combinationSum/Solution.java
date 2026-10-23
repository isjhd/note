package com.isjhd.combinationSum;

import java.util.*;

// 思路：
// 递归
// 如果 减去下标还大于等于0，则继续向下递归。
// 递归使用 target - 下标的值
public class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        back(candidates, target, lists, integers, 0);
        return lists;
    }

    private void back(int[] candidates, int target, List<List<Integer>> lists, List<Integer> integers, int k) {

        if(target == 0) {
            lists.add(new ArrayList<>(integers));
        }

        for(int i = k; i < candidates.length; i++) {

            int temp = target - candidates[i];
            if(temp >= 0) {
                integers.add(candidates[i]);
                back(candidates, temp, lists, integers, i);
                integers.remove(integers.size() - 1);
            }

        }

    }

}

class Text{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates = {2,3,6,7};
        List<List<Integer>> lists = solution.combinationSum(candidates, 7);
        for(List<Integer> list : lists){
            System.out.println(Arrays.toString(list.toArray()));
        }

    }
}
