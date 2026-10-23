package com.isjhd.inorderTraversal;



import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// 思路：
// 向下递归 干活 向下递归
class Solution {

    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> integers = new ArrayList<>();
        back(integers, root);

        return integers;
    }

    private void back(List<Integer> integers, TreeNode root) {

        if(root == null) {
            return;
        }


        back(integers, root.left);
        integers.add(root.val);
        back(integers, root.right);


    }

}



class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
