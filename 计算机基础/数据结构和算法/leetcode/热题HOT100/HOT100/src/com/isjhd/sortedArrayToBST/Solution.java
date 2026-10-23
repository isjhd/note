package com.isjhd.sortedArrayToBST;

// 思路：
// 二分查找，递归
// 当前递归的节点为二分之一，右节点为二分之一 + 1，左节点为二分之一 - 1。
public class Solution {

    public TreeNode sortedArrayToBST(int[] nums) {

        return back(nums, 0, nums.length - 1);

    }

    private TreeNode back(int[] nums, int left, int right) {

        if(left > right) {
            return null;
        }

        int mid = (left + right) / 2;
        TreeNode treeNode = new TreeNode(nums[mid]);
        treeNode.left = back(nums, left, mid - 1);
        treeNode.right = back(nums, mid + 1, right);

        return treeNode;

    }




}

class Test {
    public static void main(String[] args) {

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