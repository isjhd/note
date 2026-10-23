package com.isjhd.mergeKLists;

// 思路：
// 归并排序的思想，这道题需要考虑集合里面的值为空的情况
// 递归的时候，如果right-left为0时，则返回null; 如果right-left为1时，则返回left
// 这样即可找到所有值。
public class Solution {

    public ListNode mergeKLists(ListNode[] lists) {

        return back(lists, 0, lists.length - 1);

    }

    private ListNode back(ListNode[] lists, int left, int right) {

        if (right - left == 0) {
            return lists[left];
        }

        ListNode leftNode = back(lists, left, (left + right) / 2);
        ListNode rightNode = back(lists, (left + right) / 2 + 1, right);

        return back1(leftNode, rightNode);

    }

    private ListNode back1(ListNode left, ListNode right) {

        ListNode result = new ListNode();
        ListNode temp = result;
        while(left != null && right != null) {

            if(left.val > right.val) {
                temp.next = right;
                right = right.next;
            } else {
                temp.next = left;
                left = left.next;
            }

            temp = temp.next;

        }

        temp.next = left != null ? left : right;

        return result.next;

    }

}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(5);

        ListNode listNode4 = new ListNode(1);
        ListNode listNode5 = new ListNode(3);
        ListNode listNode6 = new ListNode(4);

        ListNode listNode7 = new ListNode(2);
        ListNode listNode8 = new ListNode(6);

        listNode1.next = listNode2;
        listNode2.next = listNode3;

        listNode4.next = listNode5;
        listNode5.next = listNode6;

        listNode7.next = listNode8;

        ListNode[] list = {listNode1, listNode4, listNode7};
        ListNode listNode9 = solution.mergeKLists(list);
        while(listNode9 != null) {
            System.out.println(listNode9.val);
            listNode9 = listNode9.next;
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