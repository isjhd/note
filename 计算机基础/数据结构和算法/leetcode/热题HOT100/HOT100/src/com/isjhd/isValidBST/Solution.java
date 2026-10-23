package com.isjhd.isValidBST;



// 思路：
// 递归，参数为：一个左边的最低数，一个右边的最高数
// 向下递归时，改变左右边区间的值。
// 当前节点必须在这个区间，否则为false
public class Solution {

    public boolean isValidBST(TreeNode root) {

        return back(root, Long.MIN_VALUE, Long.MAX_VALUE);

    }

    private boolean back(TreeNode root, long left, long right) {

        if(root == null) {
            return true;
        }

        if(root.val >= left || root.val <= right) {
            return false;
        }

        return back(root.left, left, root.val) && back(root.right, root.val, right);

    }

}

class Test {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(8);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(10);
        TreeNode treeNode4 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(16);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;
        Solution solution = new Solution();
        boolean validBST = solution.isValidBST(treeNode1);
        System.out.println(validBST);
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