package com.isjhd.flatten;


import java.util.ArrayList;
import java.util.List;

// 思路：
// 1.树的右边移到左边的最右边
// 2.然后树的左边移到树的右边
// 3.然后树的左边 = null
// 4.然后树不断向右移动，移动一步判断一次
public class Solution {
    public void flatten(TreeNode root) {

        TreeNode result = root;
        while(result != null) {

            TreeNode left = result.left;
            if(left != null) {

                TreeNode temp = left;
                while(temp.right != null) {
                    temp = temp.right;
                }

                temp.right = result.right;

                result.right = left;
                result.left = null;

            }

            result = result.right;

        }

    }
}

class Test{
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        treeNode4.left = treeNode1;
        treeNode1.right = treeNode2;
        treeNode2.right = treeNode3;
        solution.flatten(treeNode4);
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