package com.isjhd.orangesRotting;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

// 思路：
// 使用队列
// 先全部遍历找到烂橘子有几个和坐标(放入队列里面)
// 然后就可以使用while循环，如果队列不为空就继续循环
// 每循环一次相当于过了一分钟。
public class Solution {
    public int orangesRotting(int[][] grid) {

        int goods = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    goods++;
                }
            }

        }

        int time = 0;
        while (!queue.isEmpty() && goods != 0) {
            time++;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] poll = queue.poll();

                if (poll[0] + 1 < grid.length && grid[poll[0] + 1][poll[1]] == 1) {
                    grid[poll[0] + 1][poll[1]] = 2;
                    goods--;
                    queue.add(new int[]{poll[0] + 1, poll[1]});
                }

                if (poll[0] - 1 >= 0 && grid[poll[0] - 1][poll[1]] == 1) {
                    grid[poll[0] - 1][poll[1]] = 2;
                    goods--;
                    queue.add(new int[]{poll[0] - 1, poll[1]});
                }

                if (poll[1] - 1 >= 0 && grid[poll[0]][poll[1] - 1] == 1) {
                    grid[poll[0]][poll[1] - 1] = 2;
                    goods--;
                    queue.add(new int[]{poll[0], poll[1] - 1});
                }

                if (poll[1] + 1 < grid[0].length && grid[poll[0]][poll[1] + 1] == 1) {
                    grid[poll[0]][poll[1] + 1] = 2;
                    goods--;
                    queue.add(new int[]{poll[0], poll[1] + 1});
                }
            }

        }

        return goods != 0 ? -1 : time;

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] i = new int[][]{{2, 1, 1},
                {1, 1, 1},
                {0, 1, 2}};

        solution.orangesRotting(i);
    }
}
