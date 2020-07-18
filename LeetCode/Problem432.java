package LeetCode;

import java.util.HashMap;

public class Problem432 {
    HashMap<String,Integer> hashMap;
    int max;
    int min;
    public Problem432() {
        hashMap = new HashMap<>();
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }

    public void inc(String key) {
        hashMap.put(key, hashMap.getOrDefault(key, 0) + 1);
    }

    public void dec(String key) {
        int count = hashMap.get(key);
        if (count == 1) {
            hashMap.remove(key);
        } else {
            hashMap.put(key, count - 1);
        }
    }

    public String getMaxKey() {

    }

    public String getMinKey() {

    }
}
