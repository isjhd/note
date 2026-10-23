package com.isjhd.searchMatrix;

// 思路：
// 二维数组当成一维数组，进行二分查找
// [i][j],i/n,j%n，一个取行，一个取列。
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        int i = matrix[0].length - 1;
        int j = 0;
        while(i >= 0 && j < matrix.length) {

            if(target > matrix[j][i]) {
                j++;
            } else if(target < matrix[j][i]) {
                i--;
            } else {
                return true;
            }

        }

        return false;

    }
}

class Test{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {{1,1}};
        boolean b = solution.searchMatrix(matrix, 0);
        System.out.println(b);
    }
}