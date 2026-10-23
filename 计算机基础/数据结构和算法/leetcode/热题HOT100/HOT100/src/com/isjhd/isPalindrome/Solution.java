package com.isjhd.isPalindrome;

// 思路：
// 快慢指针，找到一半
// 翻转链表翻转前一半
// 然后和慢指针一比一比较
public class Solution {
    public boolean isPalindrome(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        while(fast != null || fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode head2 = null;
        while(slow != null) {
            ListNode temp = slow.next;
            slow.next = head2;
            head2 = slow;
            slow = temp;
        }

        while(head2 != null) {
            if(head.val != head2.val) {
                return false;
            }
            head = head.next;
            head2 = head2.next;
        }

        return true;

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
