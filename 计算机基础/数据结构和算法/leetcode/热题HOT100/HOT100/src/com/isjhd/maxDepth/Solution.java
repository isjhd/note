package com.isjhd.maxDepth;

// 思路
// 递归新添一个参数int，记录遍历到第几层
// 返回时比比谁最大
public class Solution {
    public int maxDepth(TreeNode root) {

        return back(root, 0);
    }

    private int back(TreeNode root, int i) {

        if(root == null) {
            return 0;
        }

        return Math.max(back(root.left, i), back(root.right, i)) + 1;


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