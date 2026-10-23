package com.isjhd.reverseList;

// 思路：
// 递归
// 只要还是用的是原链表，
// 就可以把上一个节点放到当前节点的下面，然后再把上一个节点的下一个数制空
// 然后新的链表只要是 = 原链表，也会受到改变
public class Solution {
    public ListNode reverseList(ListNode head) {

        ListNode temp = head;
        ListNode result = new ListNode();
        while(temp != null) {

            ListNode temp1 = temp.next;
            temp.next = result.next;
            result.next = temp;
            temp = temp1;

        }

        return result.next;


    }
}

class Test{
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        Solution solution = new Solution();
        ListNode listNode = solution.reverseList(listNode1);
        while(listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
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