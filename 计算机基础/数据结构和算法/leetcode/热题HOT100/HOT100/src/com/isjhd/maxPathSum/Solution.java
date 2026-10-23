package com.isjhd.maxPathSum;

// 定义一个全局int变量
// 递归，后序遍历
// 全局变量记录 left + right + 当前节点
// 返回值记录哪一边更长，如果是负数返回0，因为返回0，所以不用担心全局会加到负值。
public class Solution {

    int result = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {

        back(root);
        return result;

    }

    private int back(TreeNode root) {

        if(root == null) {
            return 0;
        }

        int left = Math.max(back(root.left), 0);
        int right = Math.max(back(root.right), 0);
        result = Math.max(result, left + right + root.val);


        return Math.max(root.val + left, root.val + right);

    }

}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(-1);
//        TreeNode treeNode3 = new TreeNode(-1);
        treeNode1.left = treeNode2;
//        treeNode1.right = treeNode3;
        int i = solution.maxPathSum(treeNode1);
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