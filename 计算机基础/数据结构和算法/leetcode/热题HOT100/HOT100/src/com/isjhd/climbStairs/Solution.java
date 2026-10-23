package com.isjhd.climbStairs;

// 思路
// 当前的台阶 = 上一个台阶 + 上上个台阶
public class Solution {
    public int climbStairs(int n) {

        int preLeft = 0;
        int left = 1;
        for(int i = 1; i < n; i++) {
            int temp = left;
            left = preLeft + left;
            preLeft = temp;
        }

        return left + preLeft;

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i1 = solution.climbStairs(1);
        int i2 = solution.climbStairs(2);
        int i3 = solution.climbStairs(3);
        int i4 = solution.climbStairs(4);
        int i5 = solution.climbStairs(5);
        int i6 = solution.climbStairs(6);
        int i7 = solution.climbStairs(7);
        int i8 = solution.climbStairs(8);
        int i9 = solution.climbStairs(9);

        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
        System.out.println(i4);
        System.out.println(i5);
        System.out.println(i6);
        System.out.println(i7);
        System.out.println(i8);
        System.out.println(i9);


    }
}
