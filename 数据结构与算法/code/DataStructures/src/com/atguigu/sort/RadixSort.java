package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {

    public static void main(String[] args) {
        //int arr[] = {53, 3, 542, 748, 14, 214};

        //创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000);//生成一个[0,8000000)数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        radixSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
    }

    //基数排序方法
    public static void radixSort(int[] arr) {

        //根据前面的推导过程，我们可以得到最终的基数排序代码

        //1. 得到数组中最大的数的位数
        int max = arr[0]; //假设第一数就是最大数
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();

        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        // 说明
        //1. 二维数组包含10个一维数组
        //2. 为了防止在放入数的时候，数据溢出，则每个一维数组（桶），大小定为arr.length
        //3. 基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的数据个数
        // 可以这里理解
        //比如：bucketElementCounts[o]，记录的就是bucket[o]桶的放入数据个数
        int[] bucketElementCounts = new int[10];

        //这里我们使用循环将代码处理
        for (int i = 0, n = 1; i < maxLength; i++, n*=10) {
            //(针对每个元素的对应位进行排序处理)，第一位是个位，第二次是十位，第三次是百位
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应位的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
            int index = 0;
            //遍历每一个桶，并将桶中的数据，放入到原数组
            for(int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中，有数据，我们才放入到原数组
                if(bucketElementCounts[k] != 0) {
                    //循环该桶，即第k个桶(即第k个一维数组)，放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //处理完后，需要将每个 bucketElementCounts[k] 置为0
                bucketElementCounts[k] = 0;
            }
            //System.out.println("第" + (i+1) + "轮，对个位的排序处理 arr = " + Arrays.toString(arr));
        }


        /*
        //第一轮(针对每个元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位的值
            int digitOfElement = arr[j] % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        int index = 0;
        //遍历每一个桶，并将桶中的数据，放入到原数组
        for(int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中，有数据，我们才放入到原数组
            if(bucketElementCounts[k] != 0) {
                //循环该桶，即第k个桶(即第k个一维数组)，放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            //处理完后，需要将每个 bucketElementCounts[k] 置为0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第一轮，对个位的排序处理 arr = " + Arrays.toString(arr));


        System.out.println("===================================================");
        //第二轮(针对每个元素的百位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位的值
            int digitOfElement = arr[j] / 10 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        index = 0;
        //遍历每一个桶，并将桶中的数据，放入到原数组
        for(int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中，有数据，我们才放入到原数组
            if(bucketElementCounts[k] != 0) {
                //循环该桶，即第k个桶(即第k个一维数组)，放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            //处理完后，需要将每个 bucketElementCounts[k] 置为0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第2轮，对个位的排序处理 arr = " + Arrays.toString(arr));



        System.out.println("===================================================");
        //第3轮(针对每个元素的百位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位的值
            int digitOfElement = arr[j] / 100 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        index = 0;
        //遍历每一个桶，并将桶中的数据，放入到原数组
        for(int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中，有数据，我们才放入到原数组
            if(bucketElementCounts[k] != 0) {
                //循环该桶，即第k个桶(即第k个一维数组)，放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            //处理完后，需要将每个 bucketElementCounts[k] 置为0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第3轮，对个位的排序处理 arr = " + Arrays.toString(arr));

         */

    }

}
