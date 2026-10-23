package com.isjhd.convert;


//Z 字形变换

//将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。

//比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
//P   A   H   N
//A P L S I I G
//Y   I   R

//之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。

//示例 1：
//输入：s = "PAYPALISHIRING", numRows = 3
//输出："PAHNAPLSIIGYIR"

//示例 2：
//输入：s = "PAYPALISHIRING", numRows = 4
//输出："PINALSIGYAHRPI"
//解释：
//P     I    N
//A   L S  I G
//Y A   H R
//P     I

import java.util.ArrayList;
import java.util.List;

//示例 3：
//输入：s = "A", numRows = 1
//输出："A"
public class Solution {

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 4;
        String convert = convert(s, numRows);
        System.out.println(convert);
    }

    public static String convert(String s, int numRows) {

        if(numRows < 2) {
            return s;
        }

        List<StringBuilder> rows = new ArrayList<>();

        for(int i = 0; i < numRows; i++){
            rows.add(new StringBuilder());
        }

        int i = 0;
        int flag = -1;
        for(char c : s.toCharArray()) {
            rows.get(i).append(c);

            if(i == 0 || i == numRows - 1) {
                //到达低点后，在上面就往下，在下面就往上。
                flag = -flag;
            }

            i += flag;

        }

        StringBuilder res = new StringBuilder();
        for(StringBuilder row : rows) {
            res.append(row);
        }

        return res.toString();
    }

}
