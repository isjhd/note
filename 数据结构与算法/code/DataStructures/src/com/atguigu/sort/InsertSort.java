package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0,13,11,12};
        //创建要给80000个的随机的数组
//        int[] arr = new int[80000];
//        for (int i = 0; i < 80000; i++) {
//            arr[i] = (int)(Math.random() * 8000000);//生成一个[0,8000000)数
//        }

//        Date date1 = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date1Str = simpleDateFormat.format(date1);
//        System.out.println("排序前的时间是=" + date1Str);

        insertSort(arr);//调用插入排序算法

//        Date date2 = new Date();
//        String date2Str = simpleDateFormat.format(date2);
//        System.out.println("排序后的时间是=" + date2Str);

        System.out.println(Arrays.toString(arr));
    }

    //插入排序
    public static void insertSort(int[] arr) {

        //使用for循环来把代码简化
        for(int i = 1; i < arr.length; i++) {
            //定义待插入到数
            int insertVal = arr[i];
            int insertIndex = i - 1; //即arr[1]的前面这个数的下标

            //给insertVal 找到插入到位置
            //说明
            // 1. insertIndex >= 0 保证在给 insertVal 找插入位置，不越界
            // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
            // 3. 就需要将 arr[insertIndex] 后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex]; //arr[insertIndex]
                insertIndex--;
            }
            //当退出while循环时，说明插入的位置找到，insertIndex + 1
            //这里我们判断是否需要赋值
            if(insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
            //System.out.println("第" + i + "轮插入");
            //System.out.println(Arrays.toString(arr));
        }

        /*

        //使用逐步推导的方式来讲解，便于理解
        //第1轮{101, 34, 119, 1}; => {34, 101, 119, 1}

        //{101, 34, 119, 1}; => {34, 101, 119, 1}
        //定义待插入到数
        int insertVal = arr[1];
        int insertIndex = 1 - 1; //即arr[1]的前面这个数的下标

        //给insertVal 找到插入到位置
        //说明
        // 1. insertIndex >= 0 保证在给 insertVal 找插入位置，不越界
        // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
        // 3. 就需要将 arr[insertIndex] 后移
        while(insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex]; //arr[insertIndex]
            insertIndex--;
        }
        //当退出while循环时，说明插入的位置找到，insertIndex + 1
        arr[insertIndex + 1] = insertVal;
        System.out.println("第1轮插入");
        System.out.println(Arrays.toString(arr));

        //第2轮
        insertVal = arr[2];
        insertIndex = 2 - 1; //即arr[1]的前面这个数的下标

        while(insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex]; //arr[insertIndex]
            insertIndex--;
        }

        arr[insertIndex + 1] = insertVal;
        System.out.println("第2轮插入");
        System.out.println(Arrays.toString(arr));

        //第3轮
        insertVal = arr[3];
        insertIndex = 3 - 1; //即arr[1]的前面这个数的下标

        while(insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex]; //arr[insertIndex]
            insertIndex--;
        }

        arr[insertIndex + 1] = insertVal;
        System.out.println("第3轮插入");
        System.out.println(Arrays.toString(arr));


         */
    }

}
