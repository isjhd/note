package com.isjhd.calcEquation;

import java.util.*;

//除法求值

//给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，
// 其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。
// 每个 Ai 或 Bi 是一个表示单个变量的字符串。

//另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，
// 请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。

//返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
// 如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。

//注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。

//注意：未在等式列表中出现的变量是未定义的，因此无法确定它们的答案。

//示例 1：
//输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0],
// queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
//输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
//解释：
//条件：a / b = 2.0, b / c = 3.0
//问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
//结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
//注意：x 是未定义的 => -1.0

//示例 2：
//输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0],
// queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
//输出：[3.75000,0.40000,5.00000,0.20000]

//示例 3：
//输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
//输出：[0.50000,2.00000,-1.00000,-1.00000]
public class Solution {
    public double[] calcEquation(List<List<String>> equations,
                                 double[] values,
                                 List<List<String>> queries) {

        int size = 0;
        HashMap<String, Integer> maps = new HashMap<>();
        for(int i = 0; i <  equations.size(); i++) {
            if(!maps.containsKey(equations.get(i).get(0))) {
                maps.put(equations.get(i).get(0), size++);
            }

            if(!maps.containsKey(equations.get(i).get(1))) {
                maps.put(equations.get(i).get(1), size++);
            }
        }

        List<Pair>[] pairs = new ArrayList[size];

        for(int i = 0; i < pairs.length; i++) {
            pairs[i] = new ArrayList<>();
        }

        for(int i = 0; i < equations.size(); i++) {
            int a = maps.get(equations.get(i).get(0));
            int b = maps.get(equations.get(i).get(1));
            pairs[a].add(new Pair(b, values[i]));
            pairs[b].add(new Pair(a, 1 / values[i]));
        }

        double[] doubles = new double[queries.size()];
        Arrays.fill(doubles, -1);

        for(int i = 0; i < doubles.length; i++) {
            if (maps.containsKey(queries.get(i).get(0)) && maps.containsKey(queries.get(i).get(1))) {
                int left = maps.get(queries.get(i).get(0));
                int right = maps.get(queries.get(i).get(1));

                if (left == right) {
                    doubles[i] = 1;
                } else {
                    Queue<Integer> temp = new LinkedList<>();
                    double[] doublesTemp = new double[size];
                    Arrays.fill(doublesTemp, -1);

                    temp.offer(left);
                    doublesTemp[left] = 1;

                    while (!temp.isEmpty() && doublesTemp[right] < 0) {
                        Integer poll = temp.poll();
                        for (Pair pair : pairs[poll]) {
                            if(doublesTemp[pair.dividend] < 0) {
                                temp.offer(pair.dividend);
                                doublesTemp[pair.dividend] = doublesTemp[poll] * pair.value;
                            }
                        }
                    }

                    doubles[i] = doublesTemp[right];
                }
            }


        }
        return doubles;
    }

    class Pair{
        int dividend;
        double value;

        public Pair(int dividend, double value) {
            this.dividend = dividend;
            this.value = value;
        }

    }

}



class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));
        equations.add(Arrays.asList("bc", "cd"));

        double[] values = {1.5, 2.5, 5.0};

        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("c", "b"));
        queries.add(Arrays.asList("bc", "cd"));
        queries.add(Arrays.asList("cd", "bc"));

        double[] doubles = solution.calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(doubles));

    }
}