package com.isjhd.largestRectangleArea;

/**
 * @description:
 * @author: isjhd
 * @date: 2025/11/19 21:34
 */

// 思路
// 利用两个int数组，记录下每个下标左边和右边比当前下标的值更低的下标，(离得最近)
// 最后只要根据左右两边的最低值即可得出横坐标，
// 最后横纵坐标相乘，一个一个遍历，只要最大值即可

// 无需一个一个的向前或向后遍历
// 如果当前下标，左边的值更大，那就取出左边对应的最低值的下标。
// 为此需要将最左边和最右边的值分别设置为 -1 和 length
public class Solution {

    public int largestRectangleArea(int[] heights) {

        int[] lefts = new int[heights.length];
        int[] rights = new int[heights.length];

        lefts[0] = -1;
        for(int i = 1; i < heights.length; i++) {

            int temp = i - 1;
            while(temp >= 0 && heights[temp] >= heights[i]) {
                temp = lefts[temp];
            }

            lefts[i] = temp;
        }

        rights[heights.length - 1] = heights.length;
        for(int i = heights.length - 2; i >= 0; i--) {

            int temp = i + 1;
            while(temp < heights.length && heights[temp] >= heights[i]) {
                temp = rights[temp];
            }

            rights[i] = temp;

        }

        int result = 0;
        for(int i = 0; i < heights.length; i++) {
            result = Math.max(result, (rights[i] - lefts[i] - 1) * heights[i]);
        }

        return result;
    }

}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.largestRectangleArea(new int[]{2,1,5,6,2,3});
    }
}