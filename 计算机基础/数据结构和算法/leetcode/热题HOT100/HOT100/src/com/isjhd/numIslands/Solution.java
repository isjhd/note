package com.isjhd.numIslands;


import java.util.LinkedList;
import java.util.Queue;

// 思路
// 前后左右递归，遇到陆地就变成水。
public class Solution {

    public int numIslands(char[][] grid) {

        int result = 0;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == '1') {
                    back(grid, i, j);
                    result++;
                }
            }
        }

        return result;

    }

    private void back(char[][] grid, int left, int right) {

        if(left >= grid.length || left < 0 || right >= grid[0].length || right < 0 || grid[left][right] == '0') {
            return;
        }

        grid[left][right] = '0';

        back(grid, left + 1, right);
        back(grid, left - 1, right);
        back(grid, left, right + 1);
        back(grid, left, right - 1);

    }


}

class Test{
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] grid = {{'1','1','1','1','0'},
                        {'1','1','0','1','0'},
                        {'1','1','0','0','0'},
                        {'0','0','0','0','0'}};
        int i = solution.numIslands(grid);
        System.out.println(i);
    }
}