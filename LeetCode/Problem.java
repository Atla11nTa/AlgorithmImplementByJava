package LeetCode;

import java.lang.reflect.Field;

public class Problem<T> {
    T testArr;

    public static void main(String[] args) throws NoSuchFieldException {
        Problem<int[]> p = new Problem<>();
        Field f = p.getClass().getDeclaredField("testArr");
        System.out.print(f.getType());
    }
}
