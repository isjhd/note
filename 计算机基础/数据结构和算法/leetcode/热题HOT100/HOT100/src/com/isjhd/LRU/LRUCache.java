package com.isjhd.LRU;


import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// 思路：
// 利用双端队列机制，然后使用map存储数据
// 删除、添加单独做一个方法
public class LRUCache {

    class LRU {

        int key;
        int value;
        LRU next;
        LRU pre;

        public LRU(){

        }

        public LRU(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }

    Map<Integer, LRU> maps;
    LRU preLRU;
    LRU endLRU;
    int max;

    public LRUCache(int capacity) {

        maps = new HashMap<>(capacity);
        preLRU = new LRU();
        endLRU = new LRU();
        preLRU.next = endLRU;
        endLRU.pre = preLRU;

        max = capacity;

    }

    public int get(int key) {

        LRU lru = maps.get(key);
        if(lru != null) {
            removeIndex(lru);
            add(lru);
            return lru.value;
        }

        return -1;
    }

    public void put(int key, int value) {

        LRU lru = maps.get(key);

        if(lru != null) {
            removeIndex(lru);
            lru.value = value;
            add(lru);
            return;
        }

        if(maps.size() == max && max > 0) {
            removeIndex(endLRU.pre);
        }

        add(new LRU(key, value));

    }

    private void removeIndex(LRU lru) {
        lru.pre.next = lru.next;
        lru.next.pre = lru.pre;
        maps.remove(lru.key);
    }

    private void add(LRU lru) {

        lru.pre = preLRU;
        lru.next = preLRU.next;
        preLRU.next.pre = lru;
        preLRU.next = lru;
        maps.put(lru.key, lru);

    }


}

class Test {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        int i = lRUCache.get(1);// 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        int i1 = lRUCache.get(2);// 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        int i2 = lRUCache.get(1);// 返回 -1 (未找到)
        int i3 = lRUCache.get(3);// 返回 3
        int i4 = lRUCache.get(4);// 返回 4
        System.out.println(i + " " + i1 + " " + i2 + " " + i3 + " " + i4);
    }
}