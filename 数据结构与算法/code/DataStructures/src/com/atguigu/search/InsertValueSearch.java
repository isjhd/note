package com.atguigu.search;

import java.util.Arrays;

public class InsertValueSearch {

    public static void main(String[] args) {

        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        int index = insertValSearch(arr, 0, arr.length - 1, 1);
        System.out.println("index= " + index);

        //System.out.println(Arrays.toString(arr));
    }

    //编写插值查找算法
    //说明：插值查找算法，也要求数组是有序的

    /**
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要查找的值
     * @return
     */
    public static int insertValSearch(int[] arr, int left, int right, int findVal) {

        //注意：findVal < arr[0] 和 findVal > arr[arr.length - 1] 必须需要
        //否则我们得到的 mid 可能越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        //求出mid，自适应
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {//说明应该向右边递归查找
            return insertValSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//说明应该向左边递归查找
            return insertValSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
