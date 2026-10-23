package com.isjhd.reverseKGroup;

// 思路：
// 找到k长的那一段链表，然后写一个翻转链表方法(返回的是链表)
// 然后用几个链表进行交换
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode result = new ListNode(0, head);
        ListNode next = result;
        ListNode left = head;
        ListNode right = result;

        while(left != null && right != null) {

            for(int i = 0; i < k; i++) {
                if(right != null) {
                    right = right.next;
                } else {
                    break;
                }
            }

            if(right == null) {
                return result.next;
            }

            ListNode temp = right.next;
            right.next = null;
            right = left;
            next.next = back(left);
            next = left;
            left.next = temp;
            left = temp;

        }
        return result.next;

    }

    private ListNode back(ListNode node) {

        ListNode result = new ListNode();
        while(node != null) {
            ListNode temp = node.next;
            node.next = result;
            result = node;
            node = temp;
        }

        return result;

    }

}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        solution.reverseKGroup(listNode1, 2);
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