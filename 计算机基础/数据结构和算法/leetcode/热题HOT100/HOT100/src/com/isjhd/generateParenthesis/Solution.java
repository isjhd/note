package com.isjhd.generateParenthesis;

import java.util.ArrayList;
import java.util.List;


class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> strings = solution.generateParenthesis(3);
        for(String s : strings) {
            System.out.print(s + "\t");
        }
    }
}

// 思路：
// 递归，并且递归完后还原
// 如果左边大于右边，return
// 如果有一边小于0，return
// 如果两边都等于0，return

// 注意：添加左边，递归减去 右边size。
class Solution {

    public List<String> generateParenthesis(int n) {

        List<String> lists = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        back(n, n, lists, sb);
        return lists;


    }

    private void back(int left, int right, List<String> lists, StringBuilder sb) {

        if(left <= 0 && right <= 0) {
            lists.add(new String(sb));
            return;
        }

        if (left < 0 || right < 0) {
            return;
        }

        if(left > right) {
            return;
        }

        sb.append('(');
        back(left - 1, right, lists, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append(')');
        back(left, right - 1, lists, sb);
        sb.deleteCharAt(sb.length() - 1);

    }

}