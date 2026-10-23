package com.isjhd.addTwoNumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


// 思路
// 两个链表：如果不为空，则输出当前节点值；如果为空，输出0。
// 如果大于10，用一个变量记录即可
public class Solution {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(9);
        ListNode listNode2 = new ListNode(9);
        ListNode listNode3 = new ListNode(9);
        ListNode listNode4 = new ListNode(9);
        ListNode listNode5 = new ListNode(9);
        ListNode listNode6 = new ListNode(9);
        ListNode listNode7 = new ListNode(9);

        ListNode listNode8 = new ListNode(9);
        ListNode listNode9 = new ListNode(9);
        ListNode listNode10 = new ListNode(9);
        ListNode listNode11 = new ListNode(9);

        LinkedList l1 = new LinkedList();
        LinkedList l2 = new LinkedList();

        l1.add(listNode1);
        l1.add(listNode2);
        l1.add(listNode3);
        l1.add(listNode4);
        l1.add(listNode5);
        l1.add(listNode6);
        l1.add(listNode7);

        l2.add(listNode8);
        l2.add(listNode9);
        l2.add(listNode10);
        l2.add(listNode11);

        l1.list();
        l2.list();
        System.out.println(listNode1);
        System.out.println(listNode4);

        ListNode listNode = addTwoNumbers(listNode1, listNode4);
        System.out.println(listNode);
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result = new ListNode();
        ListNode temp = result;
        int next = 0;
        while(l1 != null || l2 != null) {

            int int1 = l1 != null ? l1.val : 0;
            int int2 = l2 != null ? l2.val : 0;

            int sum = int1 + int2 + next;

            if(sum < 10) {
                temp.next = new ListNode(sum);
            } else {
                int ge = sum % 10;
                temp.next = new ListNode(ge);
            }

            next = sum / 10 % 10;
            temp = temp.next;

        }

        if(next > 0) {
            temp.next = new ListNode(next);
        }

        return result.next;


    }

}


class LinkedList{
    public ListNode head = new ListNode();

    //添加
    public void add(ListNode listNode) {
        ListNode temp = head;

        while(true) {
            if(temp.next == null) {
                break;
            }

            temp = temp.next;
        }

        temp.next = listNode;
    }

    //遍历
    public void list() {

        if(head.next == null) {
            return;
        }

        ListNode temp = head.next;

        while(true) {
            if(temp == null) {
                break;
            }

            System.out.println(temp.val);
            temp = temp.next;
        }

    }

}

class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {

    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}

