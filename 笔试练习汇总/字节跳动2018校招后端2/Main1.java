package 笔试练习汇总.字节跳动2018校招后端2;

import java.util.*;

/**
 * 题目：简单题，细心一点即可
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner((System.in));
        int n = in.nextInt();
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        List<Integer> list;
        int num;
        for (int i = 0; i < n; i++) {
            num = in.nextInt();
            list = hashMap.get(num);
            if (list == null) {
                list = new ArrayList<>();
                list.add(i);
                hashMap.put(num, list);
            } else {
                list.add(i);
            }
        }
        int m = in.nextInt();
        int count;
        int left;
        int right;
        int[] res = new int[m];
        int[][] matrix = new int[m][3];
        for (int i = 0; i < m; i++) {
            count = 0;
            matrix[i][0] = in.nextInt() - 1;
            matrix[i][1] = in.nextInt() - 1;
            matrix[i][2] = in.nextInt();
        }
        for (int i = 0; i < m; i++) {
            int k = matrix[i][2];
            list = hashMap.get(k);
            if (list == null) {
                res[i] = 0;
            }else {
                count = 0;
                left = matrix[i][0];
                right = matrix[i][1];
                Iterator<Integer> it = list.iterator();
                while (it.hasNext()) {
                    int index = it.next();
                    if (index >= left && index <= right) {
                        count++;
                    }
                }
                res[i] = count;
            }
        }
        for (int i = 0; i < res.length - 1; i++) {
            System.out.println(res[i]);
        }
        System.out.print(res[res.length - 1]);
    }
}
