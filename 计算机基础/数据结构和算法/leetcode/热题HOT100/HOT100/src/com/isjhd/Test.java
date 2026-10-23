package com.isjhd;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        System.out.println(convert(BigDecimal.valueOf(0.05)));
    }

    public static double convert(BigDecimal min) {
        if (BigDecimal.ZERO.compareTo(min) == 0) return 1D;

        String minStr = min.toString();

        // 小数点前
        String beginVale = minStr.substring(0, minStr.indexOf("."));
        int beginLength = 0;
        if (Double.parseDouble(beginVale) > 0) {
            beginLength = minStr.substring(0, minStr.indexOf(".")).length();
        }

        // 小数点后
        String endValue = minStr.substring(minStr.indexOf(".") + 1);
        int endLength = 0;
        if (Double.parseDouble(endValue) > 0) {
            endLength = minStr.substring(minStr.indexOf(".") + 1).length();
        }

        return Math.pow(10, beginLength + endLength);
    }

}

class Solution {

    public void back(ListNode listNode1, ListNode listNode2) {

        for(int i = 0; i < 2; i++) {
            ListNode temp = listNode1.next;
            listNode1.next = listNode2;
            listNode2 = listNode1;
            listNode1 = temp;
        }

    }

}

class ListNode{
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

