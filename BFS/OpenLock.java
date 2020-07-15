package BFS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.SortedSet;

public class OpenLock {
    public static int openLock(String[] deadends, String target) {
        int count = 0;
        HashSet<String> set = new HashSet<>(Arrays.asList(deadends));

        return count;
    }

    public static void main(String[] args) {
        System.out.println(openLock(new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"},"8888"));
    }
}
