package com.isjhd.insertionSortList;

//对链表进行插入排序

//给定单个链表的头 head ，使用 插入排序 对链表进行排序，并返回 排序后链表的头 。

//插入排序 算法的步骤:
// 1.插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
// 2.每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
// 3.重复直到所有输入数据插入完为止。

//下面是插入排序算法的一个图形示例。部分排序的列表(黑色)最初只包含列表中的第一个元素。
// 每次迭代时，从输入数据中删除一个元素(红色)，并就地插入已排序的列表中。

//对链表进行插入排序。

//示例 1：
//输入: head = [4,2,1,3]
//输出: [1,2,3,4]

//示例 2：
//输入: head = [-1,5,3,4,0]
//输出: [-1,0,3,4,5]
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode temp = new ListNode(0);
        temp.next = head;

        ListNode first = head;
        ListNode second = head.next;

        while (second != null) {
            if (first.val <= second.val) {
                first = first.next;
            } else {
                ListNode temp1 = temp;
                while (temp1.next.val <= second.val) {
                    temp1 = temp1.next;
                }

                first.next = second.next;
                second.next = temp1.next;
                temp1.next = second;
            }
            second = first.next;
        }
        return temp.next;
    }
}

class Test {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(-1);
        ListNode listNode2 = new ListNode(5);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(0);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        Solution solution = new Solution();
        ListNode listNode = solution.insertionSortList(listNode1);
        while (listNode != null) {
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