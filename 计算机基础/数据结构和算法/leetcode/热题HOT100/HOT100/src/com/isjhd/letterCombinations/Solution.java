package com.isjhd.letterCombinations;

import java.util.*;

// 思路：
// 先拿一个String数组，下标记录数字，值记录
// new char[]数组，长度就是形参的长度
// 然后递归，for循环
public class Solution {

    private String[] strings = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {

        List<String> lists = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        back(digits, 0, sb, lists);
        return lists;

    }

    private void back(String digits, int k, StringBuilder sb, List<String> lists) {

        if(k == digits.length()) {
            lists.add(new String(sb));
            return;
        }

        String string = strings[digits.charAt(k) - '0'];

        for(char c : string.toCharArray()) {
            sb.append(c);
            back(digits, k + 1, sb, lists);
            sb.deleteCharAt(sb.length() - 1);
        }

    }

}


class Test {
    public static void main(String[] args) {

        Solution solution = new Solution();
        String digits = "23";
        List<String> strings = solution.letterCombinations(digits);
        for(String s : strings) {
            System.out.print(s + "\t");
        }
    }
}