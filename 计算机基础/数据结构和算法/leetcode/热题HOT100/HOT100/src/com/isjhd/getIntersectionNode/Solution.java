package com.isjhd.getIntersectionNode;

// 思路：
// 第一条链表加上第二条链表合在一起，while循环即可找到交叉处
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode headA1 = headA;
        ListNode headB1 = headB;

        while(headA1 != headB1) {
            headA1 = headA1 != null ? headA1.next : headB;
            headB1 = headB1 != null ? headB1.next : headA;
        }

        return headB1;
    }
}

class Test{
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode11 = new ListNode(1);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode44 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode55 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode8 = new ListNode(8);
        listNode8.next = listNode44;
        listNode44.next = listNode5;

        listNode55.next = listNode6;
        listNode6.next = listNode1;
        listNode1.next = listNode8;

        listNode4.next = listNode11;
        listNode11.next = listNode8;
        Solution solution = new Solution();
        solution.getIntersectionNode(listNode55, listNode4);
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}