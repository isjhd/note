package com.isjhd.spiralOrder;

import java.util.ArrayList;
import java.util.List;

// 思路
// 一个有四种情况需要判断，
// 分别记录四个整数，left、right、top、bottom
// 每判断一层就++ or --
// 每遍历一列或一行，就判断是否超出left、right、top、bottom
// left、right、top、bottom，这四个值只要变动了就记得判断
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {


        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        int temp = 0;
        List<Integer> integers = new ArrayList<>();
        while(true) {

            temp = left;
            while(temp <= right) {
                integers.add(matrix[top][temp++]);
            }

            if(++top > bottom) {
               break;
            }

            temp = top;
            while(temp <= bottom) {
                integers.add(matrix[temp++][right]);
            }

            if(--right < left) {
                break;
            }

            temp = right;
            while(temp >= left) {
                integers.add(matrix[bottom][temp--]);
            }

            if(--bottom < top) {
                break;
            }


            temp = bottom;
            while(temp >= top) {
                integers.add(matrix[temp--][left]);
            }

            if(++left > right) {
                break;
            }

        }

        return integers;

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] i = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        solution.spiralOrder(i);
    }
}