package com.isjhd.swapPairs;

// 思路
// 需要四个链表
// 其中一个链表的next指向参数链表的第一位
// 一个temp链表用于遍历

// 两个链表用于指向左边和右边(可以写在循环里节约空间)
// 之后相互交换即可
public class Solution {

    public ListNode swapPairs(ListNode head) {

        ListNode result = new ListNode(0, head);


        ListNode temp = result;

        while(temp.next != null && temp.next.next != null) {

            ListNode left = temp.next;
            ListNode right = temp.next.next;

            left.next = left.next.next;
            right.next = left;
            temp.next = right;

            temp = temp.next.next;

        }

        return result.next;

    }

}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        solution.swapPairs(listNode1);
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}