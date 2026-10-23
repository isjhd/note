package com.isjhd.copyRandomList;

// 思路
// 复制节点，复制在每个节点的后面
// 这样每个节点后面就是一个新的深拷贝的节点
// 根据链表的特性，random的后面也是一个新的深拷贝的链表
// 最后记得还原原来链表，不能做任何破坏，否则不能通过
public class Solution {
    public Node copyRandomList(Node head) {

        Node head1 = head;
        while(head1 != null) {

            Node temp = head1.next;
            head1.next = new Node(head1.val);
            head1.next.next = temp;
            head1 = head1.next.next;

        }

        head1 = head;
        while(head1 != null) {

            head1.next = head1.random != null ? head1.random.next : null;
            head1 = head1.next.next;

        }


        Node result = new Node(0);
        Node temp = result;
        head1 = head;
        while(head1 != null) {
            temp.next = head1.next;
            head1.next = head1.next.next;
            head1 = head1.next;
            temp = temp.next;
        }

        return result.next;

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Node node7 = new Node(7);
        Node node13 = new Node(13);
        Node node11 = new Node(11);
        Node node10 = new Node(10);
        Node node1 = new Node(1);
        node7.next = node13;
        node13.next = node11;
        node11.next = node10;
        node10.next = node1;
        node13.random = node7;
        node11.random = node1;
        node10.random = node11;
        node1.random = node7;
        solution.copyRandomList(node7);
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}