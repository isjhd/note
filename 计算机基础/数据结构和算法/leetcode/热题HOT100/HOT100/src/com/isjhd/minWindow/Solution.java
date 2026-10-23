package com.isjhd.minWindow;

// 思路：
// 为两个String设立两个char数组
// 设一个int数组，大小为128，记录t字符串的变化
// 设一个变量，记录t字符串，非重复字母出现的次数
// 再设置一个left值，记录左边，用在滑动窗口，
// 如果最左边的值不为0，则left++,并且值++，并继续循环，直到int数组值为0，说明找到左边了
// 递归然后复原的思想。
class Solution {
    public String minWindow(String s, String t) {

        if(s.length() < t.length()) {
            return "";
        }

        int number = 0;
        int[] ints = new int[128];
        for(int i = 0; i < t.length(); i++) {
            if(ints[t.charAt(i)]++ == 0) {
                number++;
            }
        }

        int result_left = 0;
        int result_right = s.length();
        int left = 0;
        for(int i = 0; i < s.length(); i++) {

            if(--ints[s.charAt(i)] == 0) {
                number--;
            }

            while(number == 0) {

                if(++ints[s.charAt(left)] > 0) {
                    number++;

                    if(result_right - result_left > i - left) {
                        result_left = left;
                        result_right = i;                   }
                }

                left++;


            }



        }

        return result_right == s.length() ? "" : s.substring(result_left, result_right + 1);

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.minWindow("ADOBECODEBANC", "ABC");
    }
}
