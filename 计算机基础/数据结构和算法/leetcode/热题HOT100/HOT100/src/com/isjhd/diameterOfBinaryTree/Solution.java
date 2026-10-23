package com.isjhd.diameterOfBinaryTree;

// 思路
// 用一个全局变量记录最大值
// return 返回两边最长值加1
public class Solution {

    int result = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        back(root);
        return result;
    }

    private int back(TreeNode root) {

        if(root == null) {
            return 0;
        }

        int left = back(root.left);
        int right = back(root.right);
        if(result < left + right) {
            result = left + right;
        }

        return Math.max(left, right) + 1;

    }

}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        int i = solution.diameterOfBinaryTree(treeNode1);
        System.out.println(i);
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