package com.isjhd.setZeroes;

// 思路：
// 一共循环遍历两遍
// 第一遍循环放入0到上方和左方，记得第一列记录一个boolean值是否有0
// 第二遍循环根据上方和下方放入其他位置0，记得倒序遍历，记得最左排单独判断
public class Solution {
    public void setZeroes(int[][] matrix) {


        boolean b = false;
        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i][0] == 0){
                b = true;
            }
            for(int j = 1; j < matrix[i].length; j++) {
                if(matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }


        }

        for(int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[i].length - 1; j >= 0; j--) {

                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }

            }

            if(b) {
                matrix[i][0] = 0;
            }

        }

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[][] i = {{1,2,3,4},
//                     {5,0,7,8},
//                     {0,10,11,12},
//                     {13,14,15,0}};
        int[][] i = {{-4,-2147483648,6,-7,0},
                     {-8,6,-8,-6,0},
                     {2147483647,2,-9,-6,-10},};
        solution.setZeroes(i);
        System.out.println(i);
    }
}