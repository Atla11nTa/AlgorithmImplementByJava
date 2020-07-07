package Other;

import java.util.Random;

/**
 * 题目：利用5的随机数生成到7的随机数。
 */
public class ExpandRandom {
    //随机产生1-5之间的数
    public static int rand1To5() {
        return (int) (Math.random() * 5) + 1;
    }

    public static int rand1To7() {
        int num = 0;
        do {
            num = (rand1To5() - 1) * 5 + rand1To5() - 1;
        } while (num > 20);
        return num % 7 + 1;
    }

    public static void main(String[] args) {
        System.out.println(rand1To7());
    }
}
