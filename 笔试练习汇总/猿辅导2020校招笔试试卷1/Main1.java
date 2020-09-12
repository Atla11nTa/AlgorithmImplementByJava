package 笔试练习汇总.猿辅导2020校招笔试试卷1;

import java.math.BigInteger;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int biteCount = in.nextInt();
        int teacherNum = in.nextInt();
        if (biteCount == 1) {
            System.out.println(0);
        } else {
//            long res = 1;
            BigInteger res = new BigInteger("1");
            BigInteger mul = new BigInteger(String.valueOf(teacherNum - 1));
            for (int i = 0; i < biteCount - 2; i++) {
                res = res.multiply(mul);
            }
            mul = new BigInteger(String.valueOf(teacherNum - 2));
            res = res.multiply(mul).mod(new BigInteger("1000000007"));
            System.out.println(res);
        }
    }
}
