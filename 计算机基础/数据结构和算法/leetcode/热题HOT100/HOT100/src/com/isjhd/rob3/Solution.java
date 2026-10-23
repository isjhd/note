package com.isjhd.rob3;


//打家劫舍 III

//小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。

//除了 root 之外，每栋房子有且只有一个“父“房子与之相连。
// 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
// 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。

//给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。

//示例 1:
//输入: root = [3,2,3,null,3,null,1]
//输出: 7
//解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7

//示例 2:
//输入: root = [3,4,5,1,3,null,1]
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
public class Solution {
    public int rob(TreeNode root) {

        int[] back = back(root);


        return Math.max(back[0], back[1]);
    }

    public int[] back(TreeNode root) {

        if (root == null) {
            return new int[]{0, 0};
        }

        int[] l = back(root.left);
        int[] r = back(root.right);

        int left = root.val + l[1] + r[1];
        int right = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);

        return new int[]{left, right};
    }

}

class Test {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode6 = new TreeNode(5);

        treeNode3.left = treeNode5;
        treeNode3.right = treeNode6;
        treeNode5.left = treeNode1;
        treeNode5.right = treeNode4;
        treeNode6.right = treeNode2;

        Solution solution = new Solution();
        int rob = solution.rob(treeNode3);
        System.out.println(rob);
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