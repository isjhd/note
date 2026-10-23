package com.isjhd.canFinish;

import java.util.*;


// 思路：
// ArrayList 记录 课程 能解锁 几门课程
// int[] 记录哪个课程被锁了几次
// new Queue，记录 哪些课程已经被解锁了

// 循环 Queue，如果Queue循环完后的size，和numCourses一样大，则true
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        List<List<Integer>> lists = new ArrayList<>();
        int[] ints = new int[numCourses];
        Queue<Integer> queue = new ArrayDeque<>();

        for(int i = 0; i < numCourses; i++) {
            lists.add(new ArrayList<>());
        }

        for(int i = 0; i < prerequisites.length; i++) {
            List<Integer> integers = lists.get(prerequisites[i][1]);
            integers.add(prerequisites[i][0]);
            ints[prerequisites[i][0]]++;
        }

        for(int i = 0; i < ints.length; i++) {
            if(ints[i] == 0) {
                queue.add(i);
            }
        }

        int nums = 0;
        while(!queue.isEmpty()) {
            nums++;

            int poll = queue.poll();
            List<Integer> integers = lists.get(poll);
            for(int i : integers) {
                if(--ints[i] == 0) {
                    queue.add(i);
                }
            }

        }


        return nums == numCourses;

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] prerequisites = {{0,1}};
        boolean b = solution.canFinish(2, prerequisites);
        System.out.println(b);
    }
}
