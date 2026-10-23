package com.isjhd.groupAnagrams;

import java.util.*;
import java.util.stream.Collectors;


// 思路：
// 使用排序来让相同的字母聚在一起
// map中key存排序后的字母，value存原字母
public class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> hashMap = new HashMap<>();

        for(int i = 0; i < strs.length; i++) {

            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String temp = Arrays.toString(chars);
            if(hashMap.containsKey(temp)) {
                List<String> strings = hashMap.get(temp);
                strings.add(strs[i]);
            } else {
                List<String> strings = new ArrayList<>();
                strings.add(strs[i]);
                hashMap.put(temp, strings);
            }

        }

        List<List<String>> lists = new ArrayList<>(hashMap.values());


        return lists;

    }

}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = solution.groupAnagrams(strs);
        for(List<String> list : lists) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }
}