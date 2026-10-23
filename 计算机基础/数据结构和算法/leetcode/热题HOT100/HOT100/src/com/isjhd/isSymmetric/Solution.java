package com.isjhd.isSymmetric;


// 思路：
// 需要两个参数，分别代表left, right
public class Solution {
    public boolean isSymmetric(TreeNode root) {

        return back(root.left, root.right);

    }

    private boolean back(TreeNode left, TreeNode right) {

        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        if (left != right) {
            return false;
        }

        return back(left.left, right.right) || back(left.right, right.left);

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