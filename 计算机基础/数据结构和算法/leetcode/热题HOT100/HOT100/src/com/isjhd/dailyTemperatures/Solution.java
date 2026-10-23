package com.isjhd.dailyTemperatures;



import java.util.*;

// 思路
// 利用栈的特性
// 一个一个慢慢for循环
public class Solution {
    public int[] dailyTemperatures(int[] temperatures) {

        Stack<Integer> stacks = new Stack<>();
        int[] result = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; i++) {

            while(!stacks.isEmpty() && temperatures[stacks.peek()] < temperatures[i]) {
                int temp = stacks.pop();
                result[temp] = i - temp;
            }

            stacks.push(i);

        }

        return result;

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = solution.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        System.out.println(Arrays.toString(ints));
    }
}
