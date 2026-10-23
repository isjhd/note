package com.isjhd.mergeTwoLists;


// 思路：
// while每次只用掉一个节点
public class Solution {

    public static void main(String[] args) {
        ListNode listNode4 = new ListNode(4);
        ListNode listNode2 = new ListNode(2, listNode4);
        ListNode listNode = new ListNode(1, listNode2);

        ListNode listNode5 = new ListNode(4);
        ListNode listNode3 = new ListNode(3, listNode5);
        ListNode listNode1 = new ListNode(1, listNode3);

        ListNode listNode6 = mergeTwoLists(listNode, listNode1);
        while(listNode6 != null){
            System.out.println(listNode6.val);
            listNode6 = listNode6.next;
        }

    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode result = new ListNode();
        ListNode temp = result;
        while(l1 != null && l2 != null) {

            if(l1.val > l2.val) {
                temp.next = l2;
                l2 = l2.next;
            } else {
                temp.next = l1;
                l1 = l1.next;
            }

            temp = temp.next;

        }

        temp.next =  l1 != null ? l1 : l2;

        return result.next;

    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {};

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
