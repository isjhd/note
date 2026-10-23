package com.isjhd.partition;

import java.util.ArrayList;
import java.util.List;

// 思路
// 递归
// 利用递归找到所有left和right可以组合的可能性(递归的参数为left，for循环为right)
// for循环成功找到后，下一次递归是 right + 1。
// 递归完后记得还原状态。
class Solution {

    public List<List<String>> partition(String s) {

        List<String> strings = new ArrayList<>();
        List<List<String>> lists = new ArrayList<>();
        back(s, strings, lists, 0);
        return lists;

    }

    private void back(String s, List<String> strings, List<List<String>> lists, int k) {

        if(k == s.length()) {
            lists.add(new ArrayList<>(strings));
            return;
        }

        for(int i = k; i < s.length(); i++) {


            if(back1(s, k, i)) {
                strings.add(s.substring(k, i + 1));
                back(s, strings, lists, i + 1);
                strings.remove(strings.size() - 1);
            }

        }


    }

    private boolean back1(String s, int left, int right) {

        while(left <= right) {
            if(s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }

        return true;

    }


}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.partition("aab");
    }
}