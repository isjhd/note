package com.isjhd.minDistance;

// 动态规划
// word2 -> new int[word2.length]
// 因为用一维数组，所以int[0]作为每次word1循环的长度叠加
// 因为用一维数组，所以int数组下标即为值内容。

// 三种状态：增、删、改
// 增：int[n + 1]
// 删：上一个int[n]
// 改：当前int[n]

// int[0]位置作为会出现的最大值。

// 解决重复值问题：回退到上一个int[n]即可。

class Solution {
    public int minDistance(String word1, String word2) {

        int[] words = new int[word2.length() + 1];

        for(int i = 1; i < words.length; i++) {
            words[i] = i;
        }

        for(int i = 1; i < word1.length(); i++) {
            int pre = words[0];
            words[0]++;
            for(int j = 0; j < word2.length(); j++) {

                int temp = words[j + 1];

                if(word1.charAt(i) == word2.charAt(j)) {
                    words[j + 1] = pre;
                } else {
                    words[j + 1] = Math.min(Math.min(words[j], words[j + 1]), pre) + 1;
                }

                pre = temp;

            }
        }

        return words[word2.length()];
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String word1 = "";
        String word2 = "a";
        int i = solution.minDistance(word1, word2);
        System.out.println(i);
    }
}