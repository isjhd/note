package com.isjhd.solveNQueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 思路：很简单
// 用boolean数组确定是否处在同一行或同一列或同一斜线上

// 总结规律：1-1=0；2-2=0
public class Solution {
    public List<List<String>> solveNQueens(int n) {

        int[] strings = new int[n];
        List<List<String>> lists = new ArrayList<>();
        boolean[] boolean1 = new boolean[n];
        boolean[] boolean2 = new boolean[2 * n - 1];
        boolean[] boolean3 = new boolean[2 * n - 1];
        back(lists, strings, boolean1, boolean2, boolean3, 0);
        return lists;

    }

    private void back(List<List<String>> lists, int[] strings, boolean[] boolean1, boolean[] boolean2, boolean[] boolean3, int k) {

        if(k == strings.length) {

            List<String> str = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for(int i : strings) {

                for(int j = 0; j < strings.length; j++) {
                    if(i == j) {
                        sb.append('Q');
                    } else {
                        sb.append('.');
                    }
                }

                str.add(new String(sb));
                sb = new StringBuilder();

            }

            lists.add(str);
            return;
        }

        for(int i = 0; i < strings.length; i++) {

            if(!boolean1[i] && !boolean2[i + k] && !boolean3[i - k + strings.length - 1]) {
                boolean1[i] = true;
                boolean2[i + k] = true;
                boolean3[i - k + strings.length - 1] =  true;
                strings[k] = i;
                back(lists, strings, boolean1, boolean2, boolean3, k + 1);
                strings[k] = 0;
                boolean1[i] = false;
                boolean2[i + k] = false;
                boolean3[i - k + strings.length - 1] =  false;
            }

        }

    }


}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solveNQueens(4);
    }
}
