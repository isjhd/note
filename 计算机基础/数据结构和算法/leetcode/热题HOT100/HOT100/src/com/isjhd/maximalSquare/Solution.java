package com.isjhd.maximalSquare;

//最大正方形

//在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。

//示例 1：
//输入：matrix = [["1","0","1","0","0"],
//               ["1","0","1","1","1"],
//               ["1","1","1","1","1"],
//               ["1","0","0","1","0"]]
//输出：4


public class Solution {
    public int maximalSquare(char[][] matrix) {

        if(matrix == null || matrix.length == 0) {
            return 0;
        }

        int length = matrix.length;
        int wide = matrix[0].length;
        int size = 0;

        int[][] dp = new int[length][wide];

        for(int i = 0; i < length; i++) {
            for(int j = 0; j < wide; j++) {
                if(matrix[i][j] == '1') {
                    if(i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    }
                    size = Math.max(size, dp[i][j]);
                }
            }
        }

        return size * size;

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] matrix = {{'1','0','1','0','0'},
                           {'1','0','1','1','1'},
                           {'1','1','1','1','1'},
                           {'1','0','0','1','0'}};
        int i = solution.maximalSquare(matrix);
        System.out.println(i);
    }
}
