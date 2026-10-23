package com.isjhd.trie;



// 思路：
// 想象成一个字典
// 一个节点存放下一个节点的值，不过下一个节点是存储在一个包含26个字母的数组里面

//注意：如果节点不为null，无需替换
class Test {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.startsWith("a"); // 返回 True
    }
}

public class Trie {

    Trie[] tries = new Trie[26];
    boolean b;

    public Trie() {

    }

    public void insert(String word) {

        Trie temp = this;
        for(char i : word.toCharArray()) {
            if(temp.tries[i - 'a'] == null) {
                Trie trie = new Trie();
                temp.tries[i - 'a'] = trie;
            }
            temp = temp.tries[i - 'a'];
        }

        temp.b = true;

    }

    public boolean search(String word) {

        Trie temp = this;
        for(char i : word.toCharArray()) {
            if(temp.tries[i - 'a'] == null) {
                return false;
            } else {
                temp = temp.tries[i - 'a'];
            }
        }

        return temp.b;
    }

    public boolean startsWith(String prefix) {

        Trie temp = this;
        for(char i : prefix.toCharArray()) {
            if(temp.tries[i - 'a'] == null) {
                return false;
            } else {
                temp = temp.tries[i - 'a'];
            }
        }

        return true;

    }


}
