package com.isjhd.pathSum;

import java.util.HashMap;
import java.util.Map;

// 思路：
// 动态规划 + 前缀和 + 递归
// 把树看成一条条路线
// 递归之前添加到前缀和的maps集合里
// 递归完成后还原状态

// 顺序千万不要搞错了
public class Solution {

    int k = 0;
    Map<Integer, Integer> maps = new HashMap();

    public int pathSum(TreeNode root, int targetSum) {

        maps.put(0,1);
        back(root, targetSum, 0);
        return k;

    }

    private void back(TreeNode root,
                     int targetSum,
                     int sum) {

        if (root == null) {
            return;
        }

        sum += root.val;

        k += maps.getOrDefault(sum - targetSum, 0);
        maps.put(sum, maps.getOrDefault(sum, 0) + 1);

        back(root.left, targetSum, sum);
        back(root.right, targetSum, sum);
        maps.put(sum, maps.get(sum) - 1);


    }

}

class Test {
    public static void main(String[] args) {
        TreeNode treeNode10 = new TreeNode(10);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode_3 = new TreeNode(-3);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode11 = new TreeNode(11);
        TreeNode treeNode31 = new TreeNode(3);
        TreeNode treeNode_2 = new TreeNode(-2);
        TreeNode treeNode1 = new TreeNode(1);

        treeNode10.left = treeNode5;
        treeNode10.right = treeNode_3;
        treeNode5.left = treeNode3;
        treeNode5.right = treeNode2;
        treeNode_3.right = treeNode11;
        treeNode3.left = treeNode31;
        treeNode3.right = treeNode_2;
        treeNode2.right = treeNode1;

        Solution solution = new Solution();
        int i = solution.pathSum(treeNode10, 8);
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