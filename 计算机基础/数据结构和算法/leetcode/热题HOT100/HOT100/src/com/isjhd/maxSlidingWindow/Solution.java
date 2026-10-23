package com.isjhd.maxSlidingWindow;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// 思路
// 使用LinkedList双向链表，存储下标，添上取下
// 如果当前最底层的值小于当前值，则从底部移除，while循环直到最上层的值大于当前值
// 然后添加值至最底层
// 如果超出滑动窗口最大值则移除顶部
// 最顶部的值添加至返回值
public class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {

        Deque<Integer> deque = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];
        for(int i = 0; i < nums.length; i++) {

            while(!deque.isEmpty() && nums[i] >= nums[deque.peekFirst()]) {
                deque.pollFirst();
            }

            deque.addFirst(i);
            if(i >= k - 1 && deque.peekLast() <= i - k) {
                deque.removeLast();
            }

            if(i >= k - 1) {
                result[i - (k - 1)] = nums[deque.peekLast()];
            }



        }

        return result;

    }

}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.maxSlidingWindow(new int[]{7,2,4}, 2);
    }
}