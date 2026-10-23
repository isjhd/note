package com.isjhd.exist;

// 思路
// 递归遍历
// 递归前改变状态，递归后恢复状态。
public class Solution {

    public boolean exist(char[][] board, String word) {

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(back(board, word, 0, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean back(char[][] board, String word, int k, int left, int right) {

        if(word.length() == k) {
            return true;
        }

        if(left < 0 || left >= board.length || right < 0 || right >= board[0].length || word.charAt(k) != board[left][right]) {
            return false;
        }


        char temp = board[left][right];
        board[left][right] = '\u0000';

        boolean back = back(board, word, k + 1, left + 1, right);
        boolean back1 = back(board, word, k + 1, left - 1, right);
        boolean back2 = back(board, word, k + 1, left, right + 1);
        boolean back3 = back(board, word, k + 1, left, right - 1);

        board[left][right] = temp;

        return back || back1 || back2 || back3;

    }

}


class Test{
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = {{'A','B','C','E'},
                          {'S','F','C','S'},
                          {'A','D','E','E'}};
        String word = "SEE";
        boolean exist = solution.exist(board, word);
        System.out.println(exist);
    }
}