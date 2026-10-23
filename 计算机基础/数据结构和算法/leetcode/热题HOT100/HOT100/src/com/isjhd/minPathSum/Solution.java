package com.isjhd.minPathSum;


// 思路
// 左边 或 上边，取两者之间最小值
public class Solution {
    public int minPathSum(int[][] grid) {

        if(grid == null || grid.length == 0) {
            return 0;
        }

        for(int i = 1; i < grid.length; i++) {
            grid[i][0] += grid[i - 1][0];
        }

        for(int i = 1; i < grid[0].length; i++) {
            grid[0][i] += grid[0][i - 1];
        }

        for(int i = 1; i < grid.length; i++) {
            for(int j = 1; j < grid[i].length; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }

        return grid[grid.length - 1][grid[0].length - 1];

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        int i = solution.minPathSum(grid);
        System.out.println(i);
    }
}