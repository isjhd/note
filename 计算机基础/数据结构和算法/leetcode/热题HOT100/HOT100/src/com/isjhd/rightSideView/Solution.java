package com.isjhd.rightSideView;


import java.util.ArrayList;
import java.util.List;

// 思路；
// 结果的size等于树的深度，因为每一层都会有一个节点暴露
// 从右侧开始的前序遍历
// 如果 == 结果的size，则add进结果里面去。
public class Solution {

    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> integers = new ArrayList<>();
        back(root, 0, integers);
        return integers;
    }

    private void back(TreeNode root, int k, List<Integer> integers) {

        if(root == null) {
            return;
        }

        if(integers.size() == k) {
            integers.add(root.val);
        }

        back(root.right, k + 1, integers);
        back(root.left, k + 1, integers);

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
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}