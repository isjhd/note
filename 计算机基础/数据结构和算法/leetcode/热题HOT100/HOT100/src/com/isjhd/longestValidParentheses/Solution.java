package com.isjhd.longestValidParentheses;

/**
 * @description:
 * @author: isjhd
 * @date: 2025/12/2 22:09
 */
// 思路：动态规划
// 两种情况：
//   情况1：()()()；0,2,0,4,0,6
//   情况2：((()))；0,0,0,2,4,6
//      情况2.1：()()((()))：0,2,0,4,0,0,0,2,4,10
// 如果有)，就可以向前查看是否有(。
// (())
public class Solution {
    public int longestValidParentheses(String s) {

        int[] ints = new int[s.length()];
        int result = 0;
        for(int i = 0; i < s.length(); i++) {

            if(s.charAt(i) == ')') {
                if(i > 0 && s.charAt(i - 1) == '(') {
                    ints[i] = 2 + (i > 2 ? ints[i - 2] : 0);
                    result = Math.max(result, ints[i]);
                } else if(i > 2 && i - ints[i - 1] > 0 && ints[i - 1] != 0 && s.charAt(i - ints[i - 1] - 1) == '(') {
                    ints[i] = 2 + ints[i - 1] + (i - ints[i - 1] - 2 > 0 ? ints[i - ints[i - 1] - 2] : 0);
                    result = Math.max(result, ints[i]);
                }
            }

        }

        return result;

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.longestValidParentheses("()()((()))");
    }
}
