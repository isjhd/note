package com.isjhd.convertBST;


//把二叉搜索树转换为累加树

//给出二叉 搜索 树的根节点，该树的节点值各不相同，
// 请你将其转换为累加树（Greater Sum Tree），
// 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。

//提醒一下，二叉搜索树满足下列约束条件：
//节点的左子树仅包含键 小于 节点键的节点。
//节点的右子树仅包含键 大于 节点键的节点。
//左右子树也必须是二叉搜索树。


public class Solution {
    public TreeNode convertBST(TreeNode root) {

        int sum = 0;
        TreeNode node = root;

        while(node != null) {
            if(node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            } else {
                TreeNode temp = back(node);
                if(temp.left == null) {
                    temp.left = node;
                    node = node.right;
                } else {
                    temp.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }

        return root;
    }

    public TreeNode back(TreeNode node) {
        TreeNode temp = node.right;
        while(temp.left != null && temp.left != node) {
            temp = temp.left;
        }

        return temp;
    }

}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode treeNode0 = new TreeNode(0);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);

        treeNode4.left = treeNode1;
        treeNode4.right = treeNode6;
        treeNode6.left = treeNode5;
        treeNode6.right = treeNode7;
        treeNode7.right = treeNode8;
        treeNode1.left = treeNode0;
        treeNode1.right = treeNode2;
        treeNode2.right = treeNode3;

        TreeNode treeNode = solution.convertBST(treeNode4);
        back(treeNode);
    }

    public static void back(TreeNode treeNode) {
        if(treeNode == null) {
            return;
        }

        back(treeNode.left);
        System.out.print(treeNode.val + " ");
        back(treeNode.right);
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