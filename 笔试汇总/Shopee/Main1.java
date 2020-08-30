package 笔试汇总.Shopee;

import java.util.HashMap;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] chars = in.next().toCharArray();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '"') {
                continue;
            }
            hashMap.put(chars[i], hashMap.getOrDefault(chars[i], 0) + 1);
        }
        int res = 0;
        boolean flag = false;
        for (Integer count : hashMap.values()) {
            // 如果是偶数
            if (count % 2 == 0) {
                res += count;
            } else {
                res += count - 1;
                flag = true;
            }
        }
        if (flag) {
            res++;
        }
        System.out.print(res);
    }
}
