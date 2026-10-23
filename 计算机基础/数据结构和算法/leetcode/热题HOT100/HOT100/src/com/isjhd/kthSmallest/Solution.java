package com.isjhd.kthSmallest;


// 思路：
// 中序遍历，这样就是从小到大遍历
public class Solution {


    int result = 0;

    public int kthSmallest(TreeNode root, int k) {

        back(root, k);
        return result;


    }

    private void back(TreeNode root, int k) {
        if(root == null) {
            return;
        }

        back(root.left, k);

        if(k-- == 0) {
            this.result = root.val;
        }

        back(root.right, k);
    }



}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();

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
