package com.isjhd.medianFinder;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description:
 * @author: isjhd
 * @date: 2025/11/20 20:41
 */
// 思路
// 使用大小堆，PriorityQueue
// 设一个大顶堆和一个小顶堆
// 大顶堆存储左边的值；小顶堆存储右边的值。
// 先将值进行排序，然后存放至另一边。
public class MedianFinder {

    PriorityQueue<Integer> min = null;
    PriorityQueue<Integer> max = null;

    public MedianFinder() {
        min = new PriorityQueue<>();
        max = new PriorityQueue<>((o1, o2) -> (o2 - o1));
    }

    public void addNum(int num) {
        if(min.size() > max.size()) {
            max.add(min.poll());
        }
        max.add(num);
        min.add(max.poll());
    }

    public double findMedian() {
        return max.size() == min.size() ? (max.peek() + min.peek()) / 2.0 : min.peek();
    }

}

class Test {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-1);
        medianFinder.addNum(-2);
        medianFinder.addNum(-3);
        medianFinder.addNum(-4);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(5);
        medianFinder.addNum(6);
        medianFinder.addNum(7);
        medianFinder.addNum(8);
        System.out.println(medianFinder.findMedian());
    }
}