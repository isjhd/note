package com.isjhd.merge;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// 思路
// 先进行排序
// 设立一个ArrayList数组
// 开始循环
// 如果当前list不为空并且当前数组的数小于list里面的数则合并
public class Solution {
    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, (o1, o2) -> (o1[0] - o2[0]));

        List<int[]> integers = new ArrayList<>();
        for(int i = 0; i < intervals.length; i++) {
            if(!integers.isEmpty() && integers.get(integers.size() - 1)[1] >= intervals[i][0]) {
                if(integers.get(integers.size() - 1)[1] < intervals[i][1]) {
                    integers.get(integers.size() - 1)[1] = intervals[i][1];
                }
            } else {
                integers.add(intervals[i]);
            }
        }

        int[][] result = new int[integers.size()][2];
        for(int i = 0; i < integers.size(); i++) {
            int[] ints = integers.get(i);
            result[i][0] = ints[0];
            result[i][1] = ints[1];
        }

        return result;

    }
}

class Test{
    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        Solution solution = new Solution();
        int[][] merge = solution.merge(intervals);
        for(int[] i : merge) {
            System.out.println(Arrays.toString(i));
        }
    }
}