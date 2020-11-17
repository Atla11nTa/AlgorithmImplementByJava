package 笔试汇总.搜狗;

import java.util.*;

public class Main3 {
    static class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    // write code here
    HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
    HashSet<Integer> hashSet = new HashSet<>();
    int res = 0;
    public void dfs(int cur, List<Integer> path) {
        if (cur == -1) {
            int len = path.size();
            int num;
            for (int i = 0; i < len - 1; i++) {
                num = path.get(i);
                if (!hashSet.contains(num)) {
                    hashSet.add(num);
                    res = (res + num) % 100000007;
                }
            }
        } else {
            List<Integer> next = hashMap.get(cur);
            if (next == null) {
                return;
            }
            for (int num : next) {
                path.add(num);
                dfs(num, path);
                path.remove(path.size() - 1);
            }
        }
    }

    public Interval trim (int N, int M, Interval[] conn) {

        List<Integer> nextList;
        int start, end;
        for (int i = 0; i < M; i++) {
            start = conn[i].start;
            end = conn[i].end;
            nextList = hashMap.computeIfAbsent(start, k -> new ArrayList<>());
            nextList.add(end);
        }
        dfs(0, new ArrayList<>());
        return new Interval(hashSet.size(), res);
    }

    public static void main(String[] args) {
        int N = 3;
        int M = 4;
        Interval[] conn = new Interval[4];
        conn[0] = new Interval(0, 1);
        conn[1] = new Interval(0, 2);
        conn[2] = new Interval(2, -1);
        conn[3] = new Interval(2, 1);
        Main3 m = new Main3();
        m.trim(N, M, conn);
    }

}
