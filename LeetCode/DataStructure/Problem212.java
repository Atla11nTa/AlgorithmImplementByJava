package LeetCode.DataStructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 题目: 208. 实现 Trie (前缀树)
 * 思路: 关于树节点的设计
 * Node{
 *     map: 存储这个节点的分支.
 *     word: 计数, 记录以该节点结束的单词个数.
 * }
 * 因为这个数都是小写字母, 所以就用一个长度26的数组记录分支, 若Node.map[index]!=null说明这个分支有记录, 否则就是没有.
 *                    Node
 *                   / | \
 *                  a  b  c ....
 *                 /   |   \
 *              Node  Node Node
 *                   ...
 *题目2: 212. 单词搜索 II
 * 思路: 先构建前缀树, 然后通过回溯匹配单词, 为了加快回溯过程, 通过前缀树进行剪枝, 若为了更高效的剪枝, 每完成一次匹配,就把前缀树的一个分支删除.
 */
class Trie {
    static class Node{
        Node[] map;
        int word = 0;
        int path = 0;
        Node() {
            map = new Node[26];
        }
    }
    Node root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] words = word.toCharArray();
        int index;
        Node cur = root;
        cur.path++;
        // 逐步匹配
        for (var ch : words) {
            index = ch - 'a';
            // 若这个分支为空,就申请一个新分支.
            if (cur.map[index] == null) {
                cur.map[index] = new Node();
            }
            cur = cur.map[index];
            cur.path++;
        }
        cur.word++;
    }

    public void delete(String word) {
        char[] words = word.toCharArray();
        int index;
        Node cur = root;
        cur.path--;
        for (var ch : words) {
            index = ch - 'a';
            cur.map[index].path--;
            if (cur.map[index].path == 0) {
                cur.map[index] = null;
            }
            cur = cur.map[index];
        }
        cur.word--;
    }


    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] words = word.toCharArray();
        Node cur = root;
        int index;
        for (var ch : words) {
            index = ch - 'a';
            // 匹配成功, 节点有这个分支
            if (cur.map[index] != null) {
                cur = cur.map[index];
            } else {
                return false;
            }
        }
        return cur.word > 0;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        char[] words = prefix.toCharArray();
        Node cur = root;
        int index;
        for (var ch : words) {
            index = ch - 'a';
            if (cur.map[index] != null) {
                cur = cur.map[index];
            } else {
                return false;
            }
        }
        return true;
    }
}

public class Problem212{

    int[][] nexts = new int[][]{
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    int row, col;
    Trie preTree;
    Set<String> wordSet;
    char[][] board;
    private void backTrack(int x, int y, String curStr, List<String> res,boolean[][] isVisited) {
        if (wordSet.contains(curStr)) {
            res.add(curStr);
            wordSet.remove(curStr);
            preTree.delete(curStr);
        }
        int nextX, nextY;
        for (var next : nexts) {
            nextX = x + next[0];
            nextY = y + next[1];
            if (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && !isVisited[nextX][nextY] && preTree.startsWith(curStr + board[nextX][nextY])) {
                isVisited[nextX][nextY] = true;
                backTrack(nextX, nextY, curStr + board[nextX][nextY], res, isVisited);
                isVisited[nextX][nextY] = false;
            }
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        // 先构建前缀树.
        this.preTree = new Trie();
        this.wordSet = new HashSet<>();
        for (var word : words) {
            preTree.insert(word);
            wordSet.add(word);
        }
        this.row = board.length;
        this.col = board[0].length;
        this.board = board;
        boolean[][] isVisited = new boolean[row][col];
        List<String> res = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (preTree.startsWith(String.valueOf(board[i][j]))) {
                    isVisited[i][j] = true;
                    backTrack(i, j, String.valueOf(board[i][j]), res, isVisited);
                    isVisited[i][j] = false;
                }
            }
        }
        return res;
    }
}
