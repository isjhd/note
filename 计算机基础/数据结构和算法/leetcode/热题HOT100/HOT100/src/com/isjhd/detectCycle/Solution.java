package com.isjhd.detectCycle;

// 思路
// 依旧快慢指针
// 如果相等，判断下标
// 如果不等，while循环完后，返回null
public class Solution {
    public ListNode detectCycle(ListNode head) {

        if(head == null || head.next == null) {
            return null;
        }

        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while(fast != null && fast.next != null && slow != fast) {
            slow = slow.next;
            fast = fast.next.next;
        }

        while(head != slow) {
            head = head.next;
            slow = slow.next;
        }

        return head;

    }
}

class Test{
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode listNode3 = new ListNode(3);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode0 = new ListNode(0);
        ListNode listNode4 = new ListNode(-4);
        listNode3.next = listNode2;
        listNode2.next = listNode0;
        listNode0.next = listNode4;
        listNode4.next = listNode2;

        ListNode listNode1 = new ListNode(1);
        ListNode b = solution.detectCycle(listNode3);
        System.out.println(b);
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