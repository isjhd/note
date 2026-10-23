package com.isjhd.minStack;

import java.util.*;

// 思路
// 需要两个属性，一个存栈，一个存最小栈
// 注意：Integer和Integer之间不能使用 == 判断
public class Solution {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.getMin();
    }
}

class MinStack {

    Stack<Integer> stacks;
    Stack<Integer> min_stacks;

    public MinStack() {
        stacks = new Stack<>();
        min_stacks = new Stack<>();
    }

    public void push(int value) {
        stacks.push(value);
        if(min_stacks.isEmpty() || value < min_stacks.peek()) {
            min_stacks.push(value);
        }
    }

    public void pop() {

        Integer pop = stacks.pop();
        if(pop.equals(min_stacks.peek())) {
            min_stacks.pop();
        }
    }

    public int top() {
        return stacks.peek();
    }

    public int getMin() {

        return min_stacks.peek();
    }

}


