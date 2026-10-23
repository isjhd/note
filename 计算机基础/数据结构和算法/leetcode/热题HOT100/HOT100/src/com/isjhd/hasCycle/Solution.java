package com.isjhd.hasCycle;

// 思路：
// 快慢指针
public class Solution {
    public boolean hasCycle(ListNode head) {

        if(head == null || head.next == null) {
            return false;
        }

        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while(fast.next != null && slow != fast) {
            slow = slow.next;
            fast = fast.next.next;
        }

        while(slow != null && head != slow) {
            head = head.next;
            slow = slow.next;
        }

        return slow != null ? true : false;

    }
}

class Test{
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode listNode3 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode3.next = listNode2;
        boolean b = solution.hasCycle(listNode3);
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