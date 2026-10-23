package com.isjhd.longestCommonSubsequence;

/**
 * @Author: isjhd
 * @Date: 2025-12-09 21:41
 * @Description:
 */

// 动态规划
// 将其中一个字符串变为int数组，和另一个字符串一个一个判断
// 一维数组，需要先记录当前值，以便后续使用(怕重复的值相等导致递增)
// 两层for循环，如果两者之间为true，则+1，如果不同，取前一个值和当前值的最大值
class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.longestCommonSubsequence("abcde", "ace");
        System.out.println(i);
    }

}

public class Solution {
    public int longestCommonSubsequence(String text1, String text2) {

        int[] result = new int[text1.length() + 1];

        for(char c2 : text2.toCharArray()) {

            int pre = result[0];
            for(int i = 1; i < result.length; i++) {
                int temp = result[i];
                if(text1.charAt(i - 1) == c2) {
                    result[i] = pre + 1;
                } else {
                    result[i] = Math.max(result[i], result[i - 1]);
                }
                pre = temp;
            }
        }

        return result[text1.length()];

    }
}
