package com.isjhd.topKFrequent;


import java.util.*;


// 思路
// 循环先找到最大值和最小值，最大值-最小值就是数组所有值的范围
// 再次循环，每找到一个值，给对应下标++，并在次期间找到出现频率最高的值，记录最高出现次数。
// 最后 while 里面for循环即可找到最大值、第二大值、第k大值
class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        int min = nums[0];
        int max = nums[0];
        for(int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }

        int[] temp_num = new int[max - min];
        int max_num = 0;
        for(int i = 0; i < nums.length; i++) {
            max_num = Math.max(max_num, ++temp_num[nums[i] - min]);
        }

        int[] result = new int[k];
        int i = 0;
        while(k > i) {

            for(int j = 0; j < temp_num.length; j++) {
                if(temp_num[j] == max_num) {
                    result[i++] = j + min;
                }
            }

            max_num--;
        }

        return result;
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = solution.topKFrequent(new int[]{5,2,5,3,5,3,1,1,3}, 2);
        System.out.println(Arrays.toString(ints));
    }
}
