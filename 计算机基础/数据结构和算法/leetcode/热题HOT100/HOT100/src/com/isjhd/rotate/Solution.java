package com.isjhd.rotate;


import java.util.Arrays;

// 思路
// 最外层判断一半
// 内层+1判断一半
// 正方形，无论是奇数还是偶数，都由四个长方形或四个正方形组成。
// 边长为5的正方形,只需要交换
// (0,0)(0,1)(0,2)
// (1,0)(1,1)(1,2)
// 这个长为2、宽为3的长方形就行。
public class Solution {
    public void rotate(int[][] matrix) {

        int temp;
        int num = matrix.length - 1;
        for(int i = 0; i < matrix.length / 2; i++) {
            for(int j = 0; j < (matrix.length + 1) / 2; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[num - j][i];
                matrix[num - j][i] = matrix[num - i][num - j];
                matrix[num - i][num - j] = matrix[j][num - i];
                matrix[j][num - i] = temp;
            }
        }


    }
}

class Test{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int matrix [][] =
                {{1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}};
        solution.rotate(matrix);
        for(int[] i : matrix) {
            System.out.println(Arrays.toString(i));
        }
    }
}
