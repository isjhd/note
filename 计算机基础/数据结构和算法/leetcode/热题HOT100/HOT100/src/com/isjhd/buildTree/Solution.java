package com.isjhd.buildTree;



// 思路：
// 设置两个int，分别记录前序遍历和中序遍历
// 前序遍历用于返回每次递归的new TreeNode
// left向下递归时，传入当前节点的值
// right向下递归时，传入参数的值
// 如果中序数组等于传入的参数，下标++，并返回null;

// preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
// 规律：3,9 倒叙就是 9,3
//      15,20 倒叙就是 20,15

// 前序向下遍历3,9 然后中序通过9,3倒序返回。

public class Solution {

    int pre = 0;
    int in = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        return back(preorder, inorder, Integer.MAX_VALUE);

    }

    private TreeNode back(int[] preorder, int[] inorder, int node) {

        if(pre == preorder.length) {
            return null;
        }

        if(node == inorder[in]) {
            in++;
            return null;
        }

        TreeNode treeNode = new TreeNode(preorder[pre]);

        treeNode.left = back(preorder, inorder, preorder[++pre]);
        treeNode.right = back(preorder, inorder, node);

        return treeNode;

    }

}

class Test{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int preorder[] = {3,9,20,15,7};
        int inorder[] = {9,3,15,20,7};
        TreeNode treeNode = solution.buildTree(preorder, inorder);
        back(treeNode);
    }

    public static void back(TreeNode treeNode) {
        if(treeNode == null) {
            return;
        }
        System.out.println(treeNode.val);
        back(treeNode.left);
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