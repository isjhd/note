package com.isjhd.generate;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: isjhd
 * @date: 2025/11/26 21:30
 */

// 只要是 最左边 或 最右边 都添加为1。
public class Solution {
    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> lists = new ArrayList<>();
        for(int i = 1; i <= numRows; i++) {
            List<Integer> integers = new ArrayList<>();
            for(int j = 1; j <= i; j++) {
                if(j == 0 || j == i) {
                    integers.add(1);
                } else {
                    integers.add(lists.get(i - 1).get(j - 1) + lists.get(i - 1).get(j));
                }
            }
            lists.add(integers);
        }

        return lists;
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.generate(5);
    }
}
