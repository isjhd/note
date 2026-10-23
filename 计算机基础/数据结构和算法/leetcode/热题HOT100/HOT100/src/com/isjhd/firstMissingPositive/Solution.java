package com.isjhd.firstMissingPositive;


// 思路：
// 按照班级座位坐好
// 每次循环必须保证每个在(1,n)的这个范围的数在他的下标里。
// 可以使用for循环遍历，然后while循环保证会回到他的座位。
// 只要不在他的座位就一直while，然后找第一个学号与座位编号不匹配的学生

// 注意：当前下标的值也需要和以当前下标的值为下标的值 两者需要不同.
public class Solution {

    public int firstMissingPositive(int[] nums) {

        for(int i = 0; i < nums.length; i++) {
            while(  nums[i] >= 1 && nums[i] <= nums.length &&
                    nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;

            }
        }

        int result = 1;
        while(result <= nums.length && nums[result - 1] == result) {
            result++;
        }
        return result;

    }

}


class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.firstMissingPositive(new int[]{2,1});
    }
}