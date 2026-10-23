package com.isjhd.removeNthFromEnd;


// 思路
// 利用一个完整的复制链表
// 先循环 n 次
// 再复制一个链表
// 两个链表同时循环即可找到
public class Solution {

    public static void main(String[] args){
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        ListNode listNode = removeNthFromEnd(listNode1, 2);
        ListNode temp = listNode;
        while(temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }


    public static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode result = new ListNode();
        result.next = head;

        ListNode temp1 = result;
        ListNode temp2 = head;

        for(int i = 0; i < n; i++) {
            temp2 = temp2.next;
        }

        while(temp2 != null) {
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        temp1.next = temp1.next.next;

        return result.next;

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
