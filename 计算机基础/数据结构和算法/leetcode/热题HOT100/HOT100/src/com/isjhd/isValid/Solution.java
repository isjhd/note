package com.isjhd.isValid;



import java.util.*;

// 思路：
// 使用栈的特性
public class Solution {

    public static boolean isValid(String s) {

        Map<Character, Character> maps = new HashMap();
        maps.put('(', ')');
        maps.put('[', ']');
        maps.put('{', '}');

        Stack<Character> stacks = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if(maps.get(temp) != null) {
                stacks.push(temp);
            } else if(stacks.isEmpty() || stacks.pop() != maps.get(s.charAt(i))) {
                return false;
            }
        }

        return stacks.isEmpty();

    }

}

class Test {
    public static void main(String[] args) {
        String s = "()";
        Solution solution = new Solution();
        boolean valid = solution.isValid(s);
        System.out.println(valid);
    }
}