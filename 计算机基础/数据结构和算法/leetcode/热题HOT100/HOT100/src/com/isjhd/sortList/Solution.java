package com.isjhd.sortList;

// 思路：
// 递归实现归并排序，快慢指针找到中间
// slow.next 是右边，然后slow.next = null, 这样头节点就是左边
// 由于是归并，找到左右两个节点是顺序的，就可以合并并返回
public class Solution {

    public ListNode sortList(ListNode head) {

        if(head == null || head.next == null) {
            return head;
        }

        return back(head);

    }

    private ListNode back(ListNode listNode) {

        if(listNode == null || listNode.next == null) {
            return listNode;
        }

        ListNode slow = listNode;
        ListNode fast = listNode;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode temp = slow.next;
        slow.next = null;
        ListNode left = back(listNode);
        ListNode right = back(temp);

        ListNode result = new ListNode();
        temp = result;
        while(left != null && right != null) {

            if(left.val < right.val) {
                temp.next = left;
                left = left.next;
            } else {
                temp.next = right;
                right = right.next;
            }

            temp = temp.next;

        }

        temp.next = left != null ? left : right;

        return result.next;

    }


}

class Test{
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        listNode4.next = listNode2;
        listNode2.next = listNode1;
        listNode1.next = listNode3;
        Solution solution = new Solution();
        ListNode listNode = solution.sortList(listNode4);
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