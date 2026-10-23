package com.isjhd.mergeTrees;

//合并二叉树

//给你两棵二叉树： root1 和 root2 。

//想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。
// 你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，
// 那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。

//返回合并后的二叉树。

//注意: 合并过程必须从两个树的根节点开始。

//示例 1：
//输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
//输出：[3,4,5,5,4,null,7]

//示例 2：
//输入：root1 = [1], root2 = [1,2]
//输出：[2,2]
public class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null) {
            return null;
        }

        if(t1 == null && t2 != null) {
            return t2;
        }

        if(t1 != null && t2 == null) {
            return t1;
        }

        TreeNode result = new TreeNode(t1.val + t2.val);
        result.left = mergeTrees(t1.left, t2.left);
        result.right = mergeTrees(t1.right, t2.right);

        return result;
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(2);
        TreeNode treeNode4 = new TreeNode(5);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        TreeNode treeNode5 = new TreeNode(2);
        TreeNode treeNode6 = new TreeNode(1);
        TreeNode treeNode7 = new TreeNode(3);
        TreeNode treeNode8 = new TreeNode(4);
        TreeNode treeNode9 = new TreeNode(7);
        treeNode5.left = treeNode6;
        treeNode5.right = treeNode7;
        treeNode6.right = treeNode8;
        treeNode7.right = treeNode9;
        TreeNode treeNode = solution.mergeTrees(treeNode1, treeNode5);
        back(treeNode);
    }

    public static void back(TreeNode treeNode) {
        if(treeNode == null) {
            return;
        }

        back(treeNode.left);
        back(treeNode.right);

        System.out.print(treeNode.val + " ");
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