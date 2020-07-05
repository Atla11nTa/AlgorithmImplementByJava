package Tree;

/**
 * 题目：字典树结构
 * 题目描述：实现一个字典树结构，实现插入删除查询等基本功能。
 * 思路分析：
 * 节点结构： int pathCount; //记录有多少个单词共用这个节点
 *          int endCount; //记录有多少个单词以这个节点结尾
 *          TrieNode[] map; //哈希表存储后继节点。这里的字符限定为26个小写字母，所以用数组简单模拟存储。
 */
public class DictionaryTree {
    static class Trie {
        private TrieNode root;
        Trie() {
            root = new TrieNode();
        }

        //每个节点结构
        static class TrieNode{
            int pathCount; //记录有多少个单词共用这个节点
            int endCount; //记录有多少个单词以这个节点结尾
            TrieNode[] map; //每个节点的直接相连的下一个节点数组，通过字符进行索引。
            TrieNode(){
                pathCount = 0;
                endCount = 0;
                map = new TrieNode[26];
            }
        }

        //添加word，可重复添加
        public void insert(String word) {
            if(word == null || word.length() == 0)
                return;
            char[] strArr = word.toCharArray();
            TrieNode cur = root;
            cur.pathCount++;
            for (int i = 0; i < strArr.length; i++) {
                int index = strArr[i] - 65;
                if (cur.map[index] == null) {
                    cur.map[index] = new TrieNode();
                }
                cur = cur.map[index];
                cur.pathCount++;
            }
            cur.endCount++;
        }

        //删除word，如果word添加多次，仅删除一个
        public void delete(String word) {
            // 首先要存在于树中才可以删除
            if(search(word)){
                char[] strArr = word.toCharArray();
                TrieNode cur = root;
                cur.pathCount --;
                int index = 0;
                for (int i = 0; i < strArr.length; i++) {
                    index = strArr[i] - 'a';
                    if(cur.map[index].pathCount -- == 0){
                        cur.map[index] = null;
                        return;
                    }
                    cur = cur.map[index];
                }
                cur.endCount--;
            }

        }

        //查询word是否在字典树中
        public boolean search(String word) {
            if(word == null || word.length() == 0)
                return false;
            char[] strArr = word.toCharArray();
            TrieNode cur = root;
            int index = 0;
            for (int i = 0; i < strArr.length; i++) {
                index = strArr[i] - 'a';
                //不存在
                if (cur.map[index] == null) {
                    return false;
                }
                //继续索引
                cur = cur.map[index];
            }
            return cur.endCount != 0; //若endCount为0， 即使可以全部匹配，字典树中也是没有该word的。
        }

        //返回以字符串pre为前缀的单词数量
        public int prefixNumbers(String pre) {
            if(pre == null || pre.length() == 0){
                return -1;
            }
            char[] strArr = pre.toCharArray();
            int index = 0;
            TrieNode cur = root;
            for (int i = 0; i < strArr.length; i++) {
                index = strArr[i] - 'a';
                // 不存在该前缀
                if(cur.map[index] == null){
                    return 0;
                }
                cur = cur.map[index];
            }
            return cur.pathCount;
        }
    }
}
