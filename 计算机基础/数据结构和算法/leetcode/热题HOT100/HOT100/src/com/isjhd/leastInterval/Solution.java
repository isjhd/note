package com.isjhd.leastInterval;

//任务调度器

//给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表，用字母 A 到 Z 表示，
// 以及一个冷却时间 n。每个周期或时间间隔允许完成一项任务。任务可以按任何顺序完成，
// 但有一个限制：两个 相同种类 的任务之间必须有长度为 n 的冷却时间。

//返回完成所有任务所需要的 最短时间间隔 。

//示例 1：
//输入：tasks = ["A","A","A","B","B","B"], n = 2
//输出：8
//解释：
//在完成任务 A 之后，你必须等待两个间隔。对任务 B 来说也是一样。在第 3 个间隔，
// A 和 B 都不能完成，所以你需要待命。在第 4 个间隔，由于已经经过了 2 个间隔，你可以再次执行 A 任务。

//示例 2：
//输入：tasks = ["A","C","A","B","D","B"], n = 1
//输出：6
//解释：一种可能的序列是：A -> B -> C -> D -> A -> B。
//由于冷却间隔为 1，你可以在完成另一个任务后重复执行这个任务。

import java.util.*;

//示例 3：
//输入：tasks = ["A","A","A","B","B","B"], n = 3
//输出：10
//解释：一种可能的序列为：A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B。
//只有两种任务类型，A 和 B，需要被 3 个间隔分割。这导致重复执行这些任务的间隔当中有两次待命状态。
public class Solution {
    public int leastInterval(char[] tasks, int n) {

        int[] chars = new int[26];

        for(char c : tasks) {
            chars[c - 'A'] += 1;
        }

        Arrays.sort(chars);

        int max = (n + 1) * (chars[25] - 1) + 1;

        for(int i = 0; i < 25; i++) {
            if(chars[i] == chars[25]) {
               max++;
            }
        }

        return Math.max(max, tasks.length);
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int i = solution.leastInterval(tasks, 3);
        System.out.println(i);
    }
}
