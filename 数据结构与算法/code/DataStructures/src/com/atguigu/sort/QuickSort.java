package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {

    public static void main(String[] args) {
        // int[] arr = {-1, 0, -2};

        //创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000);//生成一个[0,8000000)数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        quickSort(arr, 0, arr.length - 1);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);

        //System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left; //左下标
        int r = right; //右下标
        //pivot 中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0; //临时变量，作为交换时使用
        //while循环的目的是让比 pivot 值小放在左边
        //比 pivot 值大放到右边
        while(l < r) {
            //在 pivot 的左边一直找，找到大于等于 pivot 值，才退出
            while(arr[l] < pivot) {
                l += 1;
            }

            //在 pivot 的右边一直找，找到小于等于 pivot 值，才退出
            while(arr[r] > pivot) {
                r -= 1;
            }

            //如果 l >= r 说明 pivot 的左右两的值，已经按照左边全部是小于等于pivot的值
            //小于等于 pivot 值，右边全部是大于等于pivot的值
            if(l >= r) {
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个 arr[l] == pivot 值 相等 r--， 前移
            if(arr[l] == pivot) {
                r -= 1;
            }

            //如果交换完后，发现这个 arr[r] == pivot 值 相等 l++， 后移
            if(arr[r] == pivot) {
                l += 1;
            }
        }

        // 如果 l == r，必须l++，r--，否则会出现栈溢出
        if(l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if(left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if(right > l) {
            quickSort(arr, l, right);
        }

    }

}